package Server;

import java.io.IOException;

public class ServerSide {
    public static void main(String[] args) throws IOException {
        int port = 1003;
        ConsoleReader c = new ConsoleReader(port);
        c.work();
    }
}
