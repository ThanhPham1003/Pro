package Server;
import User.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class ServerSide implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, SQLException {
        //InetSocketAddress is = new InetSocketAddress(1900);
        ServerSocket ss = new ServerSocket(1911);
        ConsoleReader c= new ConsoleReader(ss);
        c.work();
    }
}
