package com.example.exercise4;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegistrationApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        //--- Components --- //
        // Labels and TextFields
        Label nameLabel = new Label("Student Name:");
        TextField nameInput = new TextField();
        Label courseLabel = new Label("Course Code:");
        ComboBox<String> courseInput = new ComboBox<>(FXCollections.observableArrayList("CMP 168", "CMP 269", "CMP 410",
                "CMP 428", "MAT 313"));
        courseInput.setEditable(true); // So a student can enroll in a course not on the list
        Label statusMsg = new Label("No Updates");

        // Register Button
        Button button = new Button("Register");

        // Handle edit to courseInput


        // Button Action implementation using a Lambda
        button.setOnAction(e -> statusMsg.setText("Registration Successful for " + nameInput.getText() + " in " + courseInput.getValue()));
        // ----------------- //

        // Adding components to GridPane
        grid.add(nameLabel, 5, 2);
        grid.add(nameInput, 5, 3);
        grid.add(courseLabel, 5, 4);
        grid.add(courseInput, 5, 5);
        grid.add(button, 5, 6);
        grid.add(statusMsg, 5, 8);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setTitle("Lehman Course Registration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
