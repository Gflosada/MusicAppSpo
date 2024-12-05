package com.example.musicappspo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MusicController {

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> songsListView;

    @FXML
    private ListView<String> albumsListView;

    private static final String API_KEY = "c6b447c8e3b30b5506084c115b937f5c";
    private static final String API_BASE_URL = "http://ws.audioscrobbler.com/2.0/";

    @FXML
    private void handleSearch() {
        String query = searchField.getText().trim();
        if (!query.isEmpty()) {
            searchSongs(query);
            searchAlbums(query);
        }
    }

    private void searchSongs(String query) {
        try {
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
            String urlString = API_BASE_URL + "?method=track.search&track=" + encodedQuery + "&api_key=" + API_KEY + "&format=json";
            JSONObject jsonResponse = makeApiRequest(urlString);

            JSONObject results = jsonResponse.getJSONObject("results");
            JSONObject trackMatches = results.getJSONObject("trackmatches");
            JSONArray tracks = trackMatches.getJSONArray("track");

            songsListView.getItems().clear();
            for (int i = 0; i < tracks.length(); i++) {
                JSONObject track = tracks.getJSONObject(i);
                String name = track.getString("name");
                String artist = track.getString("artist");
                songsListView.getItems().add(name + " by " + artist);
            }
        } catch (Exception e) {
            e.printStackTrace();
            songsListView.getItems().clear();
            songsListView.getItems().add("Error: " + e.getMessage());
        }
    }

    private void searchAlbums(String query) {
        try {
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
            String urlString = API_BASE_URL + "?method=album.search&album=" + encodedQuery + "&api_key=" + API_KEY + "&format=json";
            JSONObject jsonResponse = makeApiRequest(urlString);

            JSONObject results = jsonResponse.getJSONObject("results");
            JSONObject albumMatches = results.getJSONObject("albummatches");
            JSONArray albums = albumMatches.getJSONArray("album");

            albumsListView.getItems().clear();
            for (int i = 0; i < albums.length(); i++) {
                JSONObject album = albums.getJSONObject(i);
                String name = album.getString("name");
                String artist = album.getString("artist");
                albumsListView.getItems().add(name + " by " + artist);
            }
        } catch (Exception e) {
            e.printStackTrace();
            albumsListView.getItems().clear();
            albumsListView.getItems().add("Error: " + e.getMessage());
        }
    }

    private JSONObject makeApiRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return new JSONObject(response.toString());
    }
}

