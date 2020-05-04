package TCPServer;

import Commands.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

public class TCPServerSender implements Runnable {
    private String str;
    private ServerSocket ss;
    private HashMap<String, AbstractCommand> availableCommands = new HashMap<>();
    public TCPServerSender(String str,ServerSocket ss ) throws IOException {
        this.str =str;
        this.ss =ss;

    };
    public HashMap<String, AbstractCommand> getCommand()
    {
        return availableCommands;
    }
    /**
    *Run to send data to client.
     */
    @Override
    public void run() {
        try {
            Socket s = ss.accept();
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(str);
            oos.flush();

        } catch (IOException e) {
            System.out.println("Data tranfer error" + e.getMessage());
        }
    }
}
