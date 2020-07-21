package Client;
import java.io.*;
import java.net.Socket;

public class Sender implements Serializable {
    private Socket s;
    public Sender(Socket s)
    {
        this.s=s;
    }
    public void sendClient(Client client) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(client);
        oos.flush();
        oos.close();
        byte[] bytes = bos.toByteArray();
        BufferedOutputStream outputStream = new BufferedOutputStream(s.getOutputStream());
        outputStream.write(bytes);
        outputStream.flush();
    }
}
