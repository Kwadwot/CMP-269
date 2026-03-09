import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;

public class DesktopClient extends Application {

    private static String serverAddress; // local host or server's IP address
    private static int port;
    private static String username;

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    private TextArea chatArea;
    private TextField inputField;
    private Button sendButton;

    @Override
    public void start(Stage primaryStage) {

        // --- Connection Dialog --- //
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Connect to Server");
        dialog.setHeaderText("Enter connection details:");

        ButtonType connectButtonType = new ButtonType("Connect", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(connectButtonType, ButtonType.CANCEL);

        TextField ipField = new TextField("127.0.0.1");
        ipField.setPromptText("e.g. 127.0.0.1");

        TextField portField = new TextField("59001");
        portField.setPromptText("e.g. 59001");

        TextField usernameField = new TextField();
        usernameField.setPromptText("e.g. Alice");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Server IP:"), 0, 0);
        grid.add(ipField, 1, 0);
        grid.add(new Label("Port:"), 0, 1);
        grid.add(portField, 1, 1);
        grid.add(new Label("Username:"), 0, 2);
        grid.add(usernameField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        // Disable Connect button until all fields are filled
        Node connectButton = dialog.getDialogPane().lookupButton(connectButtonType);
        connectButton.setDisable(true);

        // Makes sure the user can't attempt to connect without valid inputs
        Runnable validateFields = () -> {
            boolean disable = ipField.getText().isBlank()
                    || portField.getText().isBlank()
                    || usernameField.getText().isBlank();
            connectButton.setDisable(disable);
        };

        ipField.textProperty().addListener((obs, oldVal, newVal) -> validateFields.run());
        portField.textProperty().addListener((obs, oldVal, newVal) -> validateFields.run());
        usernameField.textProperty().addListener((obs, oldVal, newVal) -> validateFields.run());

        dialog.setOnShown(e -> usernameField.requestFocus());

        Optional<ButtonType> result = dialog.showAndWait();

        // Exit if user canceled
        if (result.isEmpty() || result.get() != connectButtonType) {
            System.exit(0);
        }

        // Parse and store connection details
        serverAddress = ipField.getText().trim();
        username = usernameField.getText().trim();

        try {
            port = Integer.parseInt(portField.getText().trim());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid port number.").showAndWait();
            System.exit(1);
        }

        System.out.printf("Connecting to %s:%d as '%s'%n", serverAddress, port, username);

        // --- Server Connection --- //
        try {
            // Connect to the server
            socket = new Socket(serverAddress, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread serverListenerThread = getListenerThread();
            serverListenerThread.start();

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Could not connect to the server.").show();
            System.exit(1);
        }

        // --- Chat Area --- //
        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);
        chatArea.setPrefHeight(350);

        // --- Input Area --- //
        inputField = new TextField();
        inputField.setPromptText("Start chatting...");

        sendButton = new Button("Send");

        HBox inputBox = new HBox(10, inputField, sendButton);
        inputBox.setPadding(new Insets(10));
        HBox.setHgrow(inputField, Priority.ALWAYS);

        // --- Layout --- //
        BorderPane root = new BorderPane();
        root.setCenter(chatArea);
        root.setBottom(inputBox);

        // --- Actions --- //
        inputField.setOnAction(e -> sendMessage()); // Send on pressing Enter key
        sendButton.setOnAction(e -> sendMessage());


        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Desktop Chat Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        cleanup();
    }

    private Thread getListenerThread() {
        Thread serverListenerThread = new Thread(() -> {
            try {
                String message;
                while ((message = reader.readLine()) != null) {
                    final String finalMessage = message;
                    Platform.runLater(() -> chatArea.appendText(finalMessage + "\n"));
                }
            } catch (IOException e) {
                Platform.runLater(() -> chatArea.appendText(username + " disconnected from the server."));
            }

        });
        serverListenerThread.setDaemon(true);
        return serverListenerThread;
    }

    private void sendMessage() {
        String message = inputField.getText();

        if (!message.isBlank()) {
            chatArea.appendText("Me: " + message + "\n");

            writer.println(message);

            inputField.clear();
        }
    }

    private void cleanup() {
        try {
            if (writer != null) writer.close();
            if (reader != null) reader.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            System.out.println("Error while closing the connection to the server: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
