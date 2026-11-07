package com.uchyy.portfolio.repository;

import com.uchyy.portfolio.data.SampleSong;
import com.uchyy.portfolio.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {

    private final List<Song> songs = new ArrayList<>(SampleSong.getSongs());

    public List<Song> findAll () {
        return songs;
    }

    public Optional<Song> findById (String id) {
        return songs.stream().filter(enquiry -> enquiry.getId().equals(id)).findFirst();
    }


    public Song save(Song song) {

        for (int i = 0; i < songs.size(); i++) {

            if (songs.get(i).getId().equals(song.getId())) {
                songs.set(i, song);
                return song;
            }
        }

        songs.add(song);
        return song;
    }


    public boolean deleteById(String id) {
        return songs.removeIf(song -> song.getId().equals(id));
    }
}
