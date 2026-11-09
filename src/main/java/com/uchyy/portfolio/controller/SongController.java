
package com.uchyy.portfolio.controller;

import com.uchyy.portfolio.model.Song;
import com.uchyy.portfolio.service.SongService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    // Public: Get published songs, with optional filtering
    @GetMapping
    public List<Song> getPublishedSongs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre) {

        return songService.getPublishedSongs(title, genre);
    }

    // Public: Get one published song
    @GetMapping("/{id}")
    public Song getPublishedSongById(@PathVariable String id) {
        return songService.getPublishedSongById(id);
    }

    // Admin: Get all songs, including unpublished
    @GetMapping("/admin")
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    // Admin: Create a song
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Song createSong(@Valid @RequestBody Song song) {
        return songService.createSong(song);
    }

    // Admin: Update a song
    @PutMapping("/{id}")
    public Song updateSong(@PathVariable String id,@Valid @RequestBody Song song) {
        return songService.updateSong(id, song);
    }

    // Admin: Delete a song
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSong(@PathVariable String id) {
        songService.deleteSong(id);
    }
}
