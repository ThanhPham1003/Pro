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
        if(args.length<1)
        {
            System.out.println("Please enter the port at the command prompt.");
            System.exit(0);
        }
        int port = 0;
        try
        {
            port= Integer.parseInt(args[0]);
        }catch (NumberFormatException e)
        {
            System.out.println("Port should be a number.");
            System.exit(0);
        }
        ConsoleReader c= new ConsoleReader(port);
        c.work();
    }
}
