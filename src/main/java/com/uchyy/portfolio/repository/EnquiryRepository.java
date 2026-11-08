
package com.uchyy.portfolio.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.uchyy.portfolio.model.Enquiry;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class EnquiryRepository {

    private static final String COLLECTION = "enquiries";

    private final Firestore firestore;

    public EnquiryRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public List<Enquiry> findAll() throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION).get();

        QuerySnapshot snapshot = future.get();

        return snapshot.getDocuments().stream()
                .map(document -> document.toObject(Enquiry.class))
                .toList();
    }

    public Optional<Enquiry> findById(String id) throws ExecutionException, InterruptedException {

        DocumentSnapshot document = firestore.collection(COLLECTION)
                .document(id)
                .get()
                .get();

        if (!document.exists()) {
            return Optional.empty();
        }

        return Optional.ofNullable(document.toObject(Enquiry.class));
    }

    public Enquiry save(Enquiry enquiry) throws ExecutionException, InterruptedException {

        firestore.collection(COLLECTION)
                .document(enquiry.getId())
                .set(enquiry)
                .get();

        return enquiry;
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
