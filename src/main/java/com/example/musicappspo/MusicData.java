// File: src/main/java/com/example/musicapp/model/MusicData.java

package com.example.musicappspo;

public class MusicData {
    private String name;
    private String artist;
    private String album;

    public MusicData(String name, String artist, String album) {
        this.name = name;
        this.artist = artist;
        this.album = album;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
    public String getAlbum() { return album; }
    public void setAlbum(String album) { this.album = album; }

    @Override
    public String toString() {
        return name + " by " + artist + " (Album: " + album + ")";
    }
}