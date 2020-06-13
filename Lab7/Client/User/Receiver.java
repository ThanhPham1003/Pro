package User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class Receiver implements Runnable, Serializable {
    private InetSocketAddress is;
    private SocketChannel ssChannel;
    private String fromServer;
    public Receiver(SocketChannel ssChannel){
        this.ssChannel = ssChannel;
    }
    /**
     * Run to receive data from server.
     */
    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(ssChannel.socket().getInputStream());
            fromServer = (String)ois.readObject();
            //System.out.println(fromServer);
        }catch (IOException e)
        {
            System.out.println("Data tranfer error.");
        }catch (ClassNotFoundException e)
        {
            System.out.println("Class doesn't exist.");
        }
    }

   public String getFromServer() {
    return fromServer;
  }
}
