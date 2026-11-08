package com.uchyy.portfolio.repository;

import com.google.cloud.firestore.Firestore;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.uchyy.portfolio.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class SongRepository {

    private final Firestore firestore;

    public SongRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    private static final String COLLECTION = "songs";

    public List<Song> findAll() throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION).get();

        QuerySnapshot snapshot = future.get();

        return snapshot.getDocuments()
                .stream()
                .map(document -> document.toObject(Song.class))
                .filter(Song::isPublished)
                .toList();
    }

    public Optional<Song> findById(String id) throws ExecutionException, InterruptedException {

        DocumentSnapshot document = firestore
                .collection(COLLECTION)
                .document(id)
                .get()
                .get();

        if (!document.exists()) {
            return Optional.empty();
        }

        return Optional.ofNullable(document.toObject(Song.class));
    }

    public Song save(Song song) throws ExecutionException, InterruptedException {

        firestore.collection(COLLECTION)
                .document(song.getId())
                .set(song)
                .get();

        return song;
    }

    public boolean deleteById(String id) throws ExecutionException, InterruptedException {

        var documentReference = firestore.collection(COLLECTION).document(id);

        DocumentSnapshot document = documentReference.get().get();

        if (!document.exists()) {
            return false;
        }

        documentReference.delete().get();

        return true;
    }
}