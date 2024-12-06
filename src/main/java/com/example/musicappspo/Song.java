package com.example.musicappspo;

public class Song {
    private String title;
    private String artist;
    private String listeners;

    public Song(String title, String artist, String listeners) {
        this.title = title;
        this.artist = artist;
        this.listeners = listeners;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getListeners() {
        return listeners;
    }
}

