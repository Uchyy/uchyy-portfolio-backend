
package com.uchyy.portfolio.model;

import java.util.List;

public class Song {

    private String id;
    private String title;
    private String label;
    private String description;
    private List<String> genre;
    private SongStatus status;
    private String artwork;
    private String story;
    private List<String> diary;
    private String lyrics;
    private List<SongMedia> media;
    private String externalLink;
    private String createdAt;
    private String updatedAt;
    private boolean published;

    public Song() {
    }

    public Song(
            String id,
            String title,
            String label,
            String description,
            List<String> genre,
            SongStatus status,
            String artwork,
            String story,
            List<String> diary,
            String lyrics,
            List<SongMedia> media,
            String externalLink,
            String createdAt,
            String updatedAt,
            boolean published
    ) {
        this.id = id;
        this.title = title;
        this.label = label;
        this.description = description;
        this.genre = genre;
        this.status = status;
        this.artwork = artwork;
        this.story = story;
        this.diary = diary;
        this.lyrics = lyrics;
        this.media = media;
        this.externalLink = externalLink;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.published = published;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public SongStatus getStatus() {
        return status;
    }

    public void setStatus(SongStatus status) {
        this.status = status;
    }

    public String getArtwork() {
        return artwork;
    }

    public void setArtwork(String artwork) {
        this.artwork = artwork;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public List<String> getDiary() {
        return diary;
    }

    public void setDiary(List<String> diary) {
        this.diary = diary;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public List<SongMedia> getMedia() {
        return media;
    }

    public void setMedia(List<SongMedia> media) {
        this.media = media;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {return  updatedAt;}

    public void setUpdatedAt(String updatedAt) {this.updatedAt = updatedAt;}

    public boolean isPublished() {return published; }

    public void setPublished(boolean published) { this.published = published; }
}
