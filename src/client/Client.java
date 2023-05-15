package client;

import chess.Board;

import java.io.*;
import java.net.Socket;

class Client extends Thread
{

    Socket socket;
    BufferedReader reader;
    OutputStreamWriter writer;

    Board board;

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
            // TODO: add try again option
            //e.printStackTrace();
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void close()
    {
        try
        {
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

    public void clickSquare(int x, int y)
    {
        // convert to a4 notation
        char file = (char) (x + 97);
        int rank = 8 - y;

        System.out.println("Clicked: " + file + rank);
    }
}
