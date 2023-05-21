package client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static final String IP = "13.51.45.69";
    static final int PORT = 5000;

    public static void main(String[] args) throws IOException {

        Client client = new Client("localhost", PORT);

       new MainFrame(client).setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Code to be executed when the JVM is shutting down
            System.out.println("Shutting down");
            // Perform cleanup or resource release here
            client.close();
            //Runtime.getRuntime().gc();
        }));

        /*
        final String PATH_TO_STOCKFISH = "stockfish_15.1_win_x64_avx2\\stockfish-windows-2022-x86-64-avx2.exe";
        ProcessBuilder processBuilder = new ProcessBuilder(PATH_TO_STOCKFISH);
        Process process = processBuilder.start();

        OutputStreamWriter writer = new OutputStreamWriter(process.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        writer.write("uci\n");
        writer.flush();

        String response;
        while ((response = reader.readLine()) != null) {
            // Process the engine response here
            System.out.println(response);
        }
        */

    }
}