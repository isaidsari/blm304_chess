package client;

public class Main {
    public static void main(String[] args) {
        Client client = new Client("localhost", 5000);
        System.out.print("Client started\n");
        new MainFrame(client).setVisible(true);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                // Code to be executed when the JVM is shutting down
                System.out.println("Shutting down...");
                // Perform cleanup or resource release here

                client.close();
            }
        });
    }
}