package com.example.musicappspo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.Locale;

public class MusicController {

    @FXML
    private TextField searchField;

    @FXML
    private ListView<Song> songsListView;

    private static final String API_KEY = "c6b447c8e3b30b5506084c115b937f5c";
    private static final String API_BASE_URL = "http://ws.audioscrobbler.com/2.0/";

    @FXML
    public void initialize() {
        songsListView.setCellFactory(param -> new ListCell<Song>() {
            @Override
            protected void updateItem(Song song, boolean empty) {
                super.updateItem(song, empty);
                if (empty || song == null) {
                    setText(null);
                } else {
                    setText(song.getTitle() + " - " + song.getArtist() + " (" + song.getListeners() + " listeners)");
                }
            }
        });
    }

    @FXML
    private void handleSearch() {
        String query = searchField.getText().trim();
        if (!query.isEmpty()) {
            searchSongs(query);
        }
    }

    private void searchSongs(String query) {
        try {
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
            String urlString = API_BASE_URL + "?method=track.search&track=" + encodedQuery + "&api_key=" + API_KEY + "&format=json&limit=50";
            JSONObject jsonResponse = makeApiRequest(urlString);

            JSONObject results = jsonResponse.getJSONObject("results");
            JSONObject trackMatches = results.getJSONObject("trackmatches");
            JSONArray tracks = trackMatches.getJSONArray("track");

            songsListView.getItems().clear();
            //loop
            for (int i = 0; i < tracks.length(); i++) {
                JSONObject track = tracks.getJSONObject(i);
                String name = track.getString("name");
                String artist = track.getString("artist");
                int listeners = track.getInt("listeners");
                String formattedListeners = NumberFormat.getNumberInstance(Locale.US).format(listeners);

                Song song = new Song(name, artist, formattedListeners);
                songsListView.getItems().add(song);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

