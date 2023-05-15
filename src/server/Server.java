package server;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

class Server extends Thread
{

    ServerSocket serverSocket;

    // clients
    List<Client> clients;
    List<Game> games;

    Server(int port)
    {
        clients = new ArrayList<>();
        games = new ArrayList<>();

        try
        {
            serverSocket = new ServerSocket(port);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
        System.out.print("Server started\n");
    }

    Server()
    {
        this(5000);
        new MainFrame(games, clients).setVisible(true);
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                clients.add(new Client(serverSocket.accept()));
                if (clients.size() >= 2)
                {
                    // create a new game
                    Game game = new Game(new Player(clients.get(0)), new Player(clients.get(1)));
                    games.add(game);
                    // remove the clients from the list
                    clients.remove(0);
                    clients.remove(0);
                }

            }
            catch (Exception e)
            {
                System.out.println("Error: " + e);
            }
        }
    }

}
