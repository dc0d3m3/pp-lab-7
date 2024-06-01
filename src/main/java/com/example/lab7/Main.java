package com.example.lab7;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

public class Main extends Application {

    private TextField directoryPathField;
    private TextField searchField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Browser and Search");

        // TextField for directory path
        directoryPathField = new TextField();
        directoryPathField.setPromptText("Enter directory path");

        // TextField for search
        searchField = new TextField();
        searchField.setPromptText("Enter search phrase");

        // Button to browse directory
        Button browseButton = new Button("Browse");
        browseButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            var selectedDirectory = directoryChooser.showDialog(primaryStage);
            if (selectedDirectory != null) {
                directoryPathField.setText(selectedDirectory.getAbsolutePath());
            }
        });

        // Layout for directory path and browse button
        HBox hbox = new HBox(10, directoryPathField, browseButton);

        // Button to initiate search (functionality to be implemented later)
        Button searchButton = new Button("Search");
        // TODO: Add event handler for searchButton here

        // Main layout including all components
        VBox vbox = new VBox(10, hbox, searchField, searchButton);
        vbox.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Setting the scene
        Scene scene = new Scene(vbox, 600, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}