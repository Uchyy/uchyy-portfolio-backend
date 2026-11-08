package com.uchyy.portfolio.model;

public class SongMedia {

    private String id;
    private MediaType mediaType;
    private String label;
    private String audioUrl;
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
