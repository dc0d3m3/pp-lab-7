package zad3.demo;
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
        searchButton.setOnAction(e -> searchFiles());

        resultArea = new TextArea();
        resultArea.setPrefHeight(400);

        HBox hbox = new HBox(10, directoryPathField, browseButton);
        VBox vbox = new VBox(10, hbox, searchField, searchButton, resultArea);

        Scene scene = new Scene(vbox, 600, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void searchFiles() {
        resultArea.clear();  // Clears the text area before displaying new results
        if (directoryPathField.getText().isEmpty()) {
            resultArea.setText("Please provide a directory path.");
            return;
        }

        File directory = new File(directoryPathField.getText());
        if (!directory.isDirectory()) {
            resultArea.setText("The provided path is not a directory.");
            return;
        }

        File[] files = directory.listFiles(); // List all files in the directory
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) { // Check if the file matches the search criteria
                    resultArea.appendText(file.getName() + "\n"); // Append each file name to the TextArea
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
