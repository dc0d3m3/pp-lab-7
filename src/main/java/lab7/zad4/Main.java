package lab7.zad4;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends Application {

    private TextField directoryPathField;
    private TextField searchField;
    private TextArea resultArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Browser and Search");

        directoryPathField = new TextField();
        directoryPathField.setPromptText("Enter directory path");

        searchField = new TextField();
        searchField.setPromptText("Enter search phrase");

        Button browseButton = new Button("Browse");
        browseButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            var selectedDirectory = directoryChooser.showDialog(primaryStage);
            if (selectedDirectory != null) {
                directoryPathField.setText(selectedDirectory.getAbsolutePath());
            }
        });

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            System.out.println("Searching for: " + searchField.getText()); // Debugowanie wartości frazy wyszukiwania
            searchInDirectory();
        });

        resultArea = new TextArea();
        resultArea.setPrefHeight(400);

        HBox hbox = new HBox(10, directoryPathField, browseButton);
        VBox vbox = new VBox(10, hbox, searchField, searchButton, resultArea);

        Scene scene = new Scene(vbox, 600, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void searchInDirectory() {
        resultArea.clear();
        if (directoryPathField.getText().isEmpty()) {
            resultArea.setText("Please provide a directory path.");
            return;
        }

        File directory = new File(directoryPathField.getText());
        if (!directory.isDirectory()) {
            resultArea.setText("The provided path is not a directory.");
            return;
        }

        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            resultArea.setText("No files found in the directory.");
            return;
        }
        for (File file : files) {
            if (file.isFile() && containsPhrase(file, searchField.getText())) {
                resultArea.appendText(file.getName() + "\n");
            }
        }
    }

    private boolean containsPhrase(File file, String searchPhrase) {
        try {
            // Przykład użycia określonego kodowania znaków
            return Files.lines(Paths.get(file.getAbsolutePath()), java.nio.charset.StandardCharsets.ISO_8859_1)
                    .peek(line -> System.out.println("Reading line: " + line)) // Do debugowania
                    .anyMatch(line -> line.contains(searchPhrase));
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getAbsolutePath()); // Informacja o błędzie
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
