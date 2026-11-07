
package com.uchyy.portfolio.controller;

import com.uchyy.portfolio.model.Song;
import com.uchyy.portfolio.service.SongService;
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

    // Get all songs or filter by title and genre
    @GetMapping
    public List<Song> getSongs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre) {

        return songService.getSongs(title, genre);
    }

    // Get one song by ID
    @GetMapping("/{id}")
    public Song getSongById(@PathVariable String id) {
        return songService.getSongById(id);
    }

    // Update an existing song
    @PutMapping("/{id}")
    public Song updateSong(@PathVariable String id,
            @RequestBody Song song) {

        return songService.updateSong(id, song);
    }

    // Create a new song
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Song createSong(@RequestBody Song song) {
        return songService.createSong(song);
    }

    // Delete a song
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSong(@PathVariable String id) {
        songService.deleteSong(id);
    }


}
