package server;

class Main {

    static final int PORT = 5000;

    public static void main(String[] args)
    {
        new Server(PORT).start();
    }
}
