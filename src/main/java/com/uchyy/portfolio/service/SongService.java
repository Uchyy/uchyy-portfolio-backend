package com.uchyy.portfolio.service;

import com.uchyy.portfolio.model.Song;
import com.uchyy.portfolio.repository.SongRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    // Get all songs
    public List<Song> getAllSongs() {
        try {
            return songRepository.findAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw databaseError(e);
        } catch (ExecutionException e) {
            throw databaseError(e);
        }
    }

    // Get only published songs for the public portfolio
    public List<Song> getPublishedSongs(String title, String genre) {
        return getAllSongs().stream()
                .filter(Song::isPublished)
                .filter(song -> title == null || title.isBlank() || song.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(song -> genre == null || genre.isBlank() || song.getGenre().stream()
                .anyMatch(g -> g.equalsIgnoreCase(genre)))
                .toList();
    }

    // Get one published song for its public detail page
    public Song getPublishedSongById(String id) {
        Song song = getSongById(id);

        if (!song.isPublished()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found: " + id);
        }

        return song;
    }

    /*Get songs with optional title and genre filtering
    public List<Song> getSongs(String title, String genre) {
        try {
            return songRepository.findAll()
                    .stream()
                    .filter(song -> title == null || title.isBlank() || song.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .filter(song -> genre == null || genre.isBlank() || song.getGenre().stream()
                    .anyMatch(g -> g.equalsIgnoreCase(genre)))
                    .toList();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw databaseError(e);
        } catch (ExecutionException e) {
            throw databaseError(e);
        }
    } */

    // Get song by ID
    public Song getSongById(String id) {
        try {
            return songRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found: " + id));

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw databaseError(e);
        } catch (ExecutionException e) {
            throw databaseError(e);
        }
    }

    // Update an existing song
    public Song updateSong(String id, Song updatedSong) {
        try {
            Song existingSong = songRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Song not found: " + id));

            updatedSong.setId(existingSong.getId());
            updatedSong.setCreatedAt(existingSong.getCreatedAt());

            return songRepository.save(updatedSong);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw databaseError(e);
        } catch (ExecutionException e) {
            throw databaseError(e);
        }
    }

    // Create a new song
    public Song createSong(Song song) {
        try {
            song.setId(UUID.randomUUID().toString());
            song.setCreatedAt(Instant.now().toString());

            return songRepository.save(song);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw databaseError(e);
        } catch (ExecutionException e) {
            throw databaseError(e);
        }
    }

    // Delete a song
    public void deleteSong(String id) {
        try {
            boolean deleted = songRepository.deleteById(id);

            if (!deleted) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Song not found: " + id);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw databaseError(e);
        } catch (ExecutionException e) {
            throw databaseError(e);
        }
    }

    // Handle database failures
    private ResponseStatusException databaseError(Exception e) {
        return new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "A database error occurred",
                e
        );
    }
}