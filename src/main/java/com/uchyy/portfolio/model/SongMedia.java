package com.uchyy.portfolio.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public class SongMedia {

    private String id;

    @NotNull(message = "Media type is required.")
    private MediaType mediaType;

    @NotBlank(message = "Media label is required.")
    @Size(max = 100, message = "Media label cannot exceed 100 characters.")
    private String label;

    @NotBlank(message = "Audio URL is required.")
    @URL(message = "Please provide a valid audio URL.")
    private String audioUrl;

    @Size(max = 20, message = "Duration cannot exceed 20 characters.")
    private String duration;

    public SongMedia(String id, MediaType mediaType, String label, String audioUrl, String duration) {
        this.id = id;
        this.mediaType = mediaType;
        this.label = label;
        this.audioUrl = audioUrl;
        this.duration = duration;
    }

    public SongMedia() {
    }


    public String getAudioUrl() {
        return audioUrl;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public String getLabel() {
        return label;
    }

    public String getId() {
        return id;
    }

    public String getDuration() {
        return duration;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
