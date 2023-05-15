package server;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class MainFrame extends JFrame {

    private JList games;
    private JList clients;

    public MainFrame(List<Game> games, List<Client> clients) {
        initComponents();


        // create a new thread to update the lists
        new Thread(() -> {
            while (true) {
                // update the games list
                DefaultListModel<String> gamesModel = new DefaultListModel<>();
                for (Game game : games) {
                    gamesModel.addElement(game.white.client.socket.getInetAddress().toString()+":"+ game.white.client.socket.getPort() + " vs " + game.black.client.socket.getInetAddress().toString()+":"+ game.black.client.socket.getPort());
                }
                this.games.setModel(gamesModel);

                // update the clients list
                DefaultListModel<String> clientsModel = new DefaultListModel<>();
                for (Client client : clients) {
                    clientsModel.addElement(client.socket.getInetAddress().toString() + ":" + client.socket.getPort());
                }
                this.clients.setModel(clientsModel);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        });
        setTitle("Chess Server");
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Games");
        label.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(label);

        games = new JList();
        games.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(games);

        label = new JLabel("Clients");
        label.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(label);

        clients = new JList();
        clients.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(clients);




        add(panel);

    }
}