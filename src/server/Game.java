package server;

import chess.Chess;

import java.io.IOException;
import java.util.List;

class Game extends Thread {

    static int id;
    Player white;
    Player black;

    Chess chess;

    List<Game> list;

    Game(Player white, Player black, List<Game> list) {
        this.white = white;
        this.black = black;

        this.chess = new Chess();

        white.client.send("game_id:"+id);
        black.client.send("game_id:"+id);

        white.client.send("color:white");
        black.client.send("color:black");

        white.client.send("start");
        black.client.send("start");

        id++;
    }

    @Override
    public void run() {
        System.out.println("game started");
        String whiteMove, blackMove;
        try {
            while (true) {
                whiteMove = white.client.reader.readLine();

                if (whiteMove.equals("exit")) {
                    System.out.println("white player exited");
                    black.client.send("exit");
                    break;
                }
                System.out.println("White player's move: " + whiteMove);

                // Send white player's move to black player
                black.client.writer.write(whiteMove + "\n");
                black.client.writer.write("your_turn\n");
                black.client.writer.flush();

                // Wait for black player's move
                blackMove = black.client.reader.readLine();
                if (blackMove.equals("exit")) {
                    System.out.println("black player exited");
                    white.client.send("exit");
                    break;
                }
                System.out.println("Black player's move: " + blackMove);

                // Send black player's move to white player
                white.client.writer.write(blackMove + "\n");
                white.client.writer.write("your_turn\n");
                white.client.writer.flush();
            }

            // remove the game from the list
            list.remove(this);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
