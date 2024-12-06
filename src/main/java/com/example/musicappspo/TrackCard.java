package com.example.musicappspo;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TrackCard extends VBox {
    private ImageView coverImageView;
    private Label titleLabel;
    private Label artistLabel;
    private Label listenersLabel;

    public TrackCard() {
        setAlignment(Pos.CENTER);
        setSpacing(5);
        getStyleClass().add("track-card");

        coverImageView = new ImageView();
        coverImageView.setFitWidth(150);
        coverImageView.setFitHeight(150);
        coverImageView.getStyleClass().add("cover-image");

        titleLabel = new Label();
        titleLabel.getStyleClass().add("track-title");

        artistLabel = new Label();
        artistLabel.getStyleClass().add("track-artist");

        listenersLabel = new Label();
        listenersLabel.getStyleClass().add("track-listeners");

        getChildren().addAll(coverImageView, titleLabel, artistLabel, listenersLabel);
    }

    public void setData(String title, String artist, String listeners, String imageUrl) {
        titleLabel.setText(title);
        artistLabel.setText(artist);
        listenersLabel.setText(listeners + " listeners");
        coverImageView.setImage(new Image(imageUrl, true));
    }
}

