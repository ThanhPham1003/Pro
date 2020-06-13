package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSender implements Runnable, Serializable {
    private Socket ss;
    private String str;
    public ServerSender(Socket ss,String str)
    {
        this.ss=ss;
        this.str=str;
    }

    @Override
    public void run() {
        try {
            System.out.println("Sever send object");
            //Socket s = ss.accept();
            ObjectOutputStream oos = new ObjectOutputStream(ss.getOutputStream());
            oos.writeObject(str);
            oos.flush();

        } catch (IOException e) {
            System.out.println("Data transfer error" + e.getMessage());
        }
    }
}
