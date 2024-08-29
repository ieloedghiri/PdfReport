package com.example.demo.model;

public class TranscriptionRequest {
    private String intervenantName;
    private String audioUrl;

    // Getters and Setters
    public String getIntervenantName() {
        return intervenantName;
    }

    public void setIntervenantName(String intervenantName) {
        this.intervenantName = intervenantName;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
