package TCPClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class TCPReceiver implements Runnable{
    private InetSocketAddress is;

    public TCPReceiver(InetSocketAddress is){
        this.is = is;
    }

    /**
     * Run to receive data from server.
     */
    @Override
    public void run() {
        try {
            String fromServer;
            SocketChannel ssChannel = SocketChannel.open(is);
            ObjectInputStream ois = new ObjectInputStream(ssChannel.socket().getInputStream());
            fromServer = (String) ois.readObject();
            System.out.println(fromServer);

        }catch (IOException e)
        {
            System.out.println("Data tranfer error.");
         }catch (ClassNotFoundException e)
        {
            System.out.println("Class doesn't exist.");
        }
    }
}
