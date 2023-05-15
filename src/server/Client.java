package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

class Client
{
    static final int BUFFER_SIZE = 1024;
    Socket socket;
    BufferedReader reader;
    OutputStreamWriter writer;

    Client(Socket socket)
    {
        this.socket = socket;
        try
        {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new OutputStreamWriter(socket.getOutputStream());
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
        System.out.println("Client: " + socket.getRemoteSocketAddress());
    }

    void send(String message)
    {
        try
        {
            writer.write(message + "\n");
            writer.flush();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
    }

    String receive()
    {
        try
        {
            char[] buffer = new char[BUFFER_SIZE];
            int length = reader.read(buffer);
            return new String(buffer, 0, length);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
        return "";
    }

}
