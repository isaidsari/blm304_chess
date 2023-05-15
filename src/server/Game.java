package server;

import chess.Chess;

import java.io.IOException;


class Game {

    Player white;
    Player black;

    Chess chess;

    Game(Player white, Player black) {
        this.white = white;
        this.black = black;

        this.chess = new Chess();

        new Thread(() -> handlePlayer(white)).start();
        new Thread(() -> handlePlayer(black)).start();
    }

    void handlePlayer(Player player) {
        try {

            while (true) {
                String message = player.client.reader.readLine();

                if (message == null) {
                    // Connection closed
                    break;
                }

                // Process the received message
                System.out.println("Received message from client: " + message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
