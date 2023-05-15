package client;

import java.io.*;
import java.net.Socket;

class Client extends Thread
{

    Socket socket;
    BufferedReader reader;
    OutputStreamWriter writer;

    Client(String host, int port)
    {
        try
        {
            socket = new Socket(host, port);
            socket.setTcpNoDelay(true);
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new OutputStreamWriter(socket.getOutputStream());
            this.start();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void run()
    {
        System.out.println("Client: " + socket.getLocalSocketAddress());
        System.out.println("Server: " + socket.getRemoteSocketAddress());
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void close()
    {
        try
        {
            reader.close();
            writer.close();
            socket.close();
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
