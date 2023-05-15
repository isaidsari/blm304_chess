package client;

public class Main {
    public static void main(String[] args) {

        Client client = new Client("localhost", 5000);

        new MainFrame(client).setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Code to be executed when the JVM is shutting down
            System.out.println("Shutting down");
            // Perform cleanup or resource release here
            client.close();
            //Runtime.getRuntime().gc();
        }));
    }
}