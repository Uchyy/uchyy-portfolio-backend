
package com.uchyy.portfolio.service;

import com.uchyy.portfolio.model.Song;
import com.uchyy.portfolio.repository.SongRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    // Get all songs
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    // Get songs with optional title and genre filtering
    public List<Song> getSongs(String title, String genre) {

        return songRepository.findAll()
                .stream()
                .filter(song -> title == null || title.isBlank() || song.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(song -> genre == null || genre.isBlank() || song.getGenre().stream().anyMatch(g -> g.equalsIgnoreCase(genre)))
                .toList();
    }

    // Get song by ID
    public Song getSongById(String id) {

        return songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found: " + id ));
    }

    // Update an existing song
    public Song updateSong(String id, Song updatedSong) {

        Song existingSong = songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found: " + id ));

        // Preserve the original ID and creation date
        updatedSong.setId(existingSong.getId());
        updatedSong.setCreatedAt(existingSong.getCreatedAt());

        return songRepository.save(updatedSong);
    }


    public Song createSong(Song song) {
        song.setId(java.util.UUID.randomUUID().toString());
        song.setCreatedAt(java.time.Instant.now().toString());
        return songRepository.save(song);
    }

    public void deleteSong(String id) {
        songRepository.deleteById(id);
    }
}
