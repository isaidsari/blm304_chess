package client;

import chess.Board;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

class Client extends Thread
{

    Socket socket;
    BufferedReader reader;
    OutputStreamWriter writer;

    Color color;
    Board board;

    Runnable hook;

    Client(String host, int port)
    {
        this.board = new Board();
        try
        {
            socket = new Socket(host, port);

            // socket.setTcpNoDelay(true);
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new OutputStreamWriter(socket.getOutputStream());
            this.start();
        }// catch server is not running specifically
        catch (IOException e)
        {
            System.out.println("Could not connect to server.");
            // pop up dialog box
            JOptionPane.showMessageDialog(null, "Could not connect to server.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        System.out.println("Client started. Client: " + socket.getLocalSocketAddress() + " --> Server: " + socket.getRemoteSocketAddress());
        try {
            String command;

            command = reader.readLine();
            System.out.println(command);
            command = reader.readLine();
            this.color = (command.split(":")[1].equals("white")) ? Color.WHITE : Color.BLACK;
            System.out.println(command + " | " + color);
            JOptionPane.showMessageDialog(null, "You are playing as " + (command.split(":")[1]), "Game Found", JOptionPane.INFORMATION_MESSAGE);

            command = reader.readLine();
            System.out.println(command);

            while (true)
            {
                command = reader.readLine();
                System.out.println(command);
                if (command.startsWith("move:")) parseMove(command.substring("move:".length()));

                if (command.equals("exit")) {
                    System.out.println("opponent exited");
                    JOptionPane.showMessageDialog(null, "Opponent exited", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void close()
    {
        try
        {
            writer.write("exit\n");
            if (reader != null)
                reader.close();
            if (writer != null)
                writer.close();
            if (socket != null)
                socket.close();

            System.out.println("Client closed");
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
    }

    public void move(Point from, Point to)
    {
        if (board.pieceAt(from) == null)
            return;

        if (board.pieceAt(from).color != color)
            return;

        if (!board.move(from, to))
            return;

        try {
            writer.write("move:"+convertToFileRank(from)+convertToFileRank(to)+"\n");
            System.out.println("move:"+convertToFileRank(from)+convertToFileRank(to));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        hook.run();
    }

    void parseMove(String move)
    {
        System.out.println("parse move : " + move);
        if (move.length() != 4) {
            //throw new IllegalArgumentException("Invalid notation format");
        }

        int fromX = Character.toLowerCase(move.charAt(0)) - 'a';
        int fromY = 8 - Character.getNumericValue(move.charAt(1));
        int toX = Character.toLowerCase(move.charAt(2)) - 'a';
        int toY = 8 - Character.getNumericValue(move.charAt(3));

        Point from, to;
        from = new Point(fromX, fromY);
        to = new Point(toX, toY);

        board.move(from, to);
        hook.run();
    }

    String convertToFileRank(Point point)
    {
        return (char) (point.x + 97) + String.valueOf(8 - point.y);
    }

}
