import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleClient {

    private static final String SERVER_ADDRESS = "127.0.0.1"; // local host or server's IP address
    private static final int PORT = 59001;

    private static class incomingMessageHandler extends Thread {

        private BufferedReader reader;

        public incomingMessageHandler(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            String message;

            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println(message);
                }

            } catch (IOException e) {
                System.out.println("Error reading messages from server: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        System.out.printf("Connecting to the chat server at %s:%d ...\n", SERVER_ADDRESS, PORT);

        Socket socket = null;
        PrintWriter writer = null;
        BufferedReader reader = null;

        try {
            // Connect to the server
            socket = new Socket(SERVER_ADDRESS, PORT);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

//            System.out.println("Connected to the server at " + SERVER_ADDRESS + ":" + PORT);

            // Handle incoming messages from server in a separate thread
            Thread incomingMessageHandler = new incomingMessageHandler(reader);
            incomingMessageHandler.start();

            // Main thread handles input and sending messages to the server
            System.out.println("Connected to the chat server.");
            System.out.println("Type your message:");
            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNextLine()) {
                String message = scanner.nextLine();
                writer.println(message);
            }


        } catch (IOException e) {
            System.out.println("Could not connect to the server: " + e.getMessage());

        } finally{
            if (socket != null) {

                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Could not close the socket: " + e.getMessage());
                }
            }
        }
    }
}
