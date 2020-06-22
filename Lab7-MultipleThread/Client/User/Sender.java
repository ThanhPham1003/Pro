package User;
import Object.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class Sender implements Serializable {
    private HumanBeing person;
    private HumanBeing a = new HumanBeing();
    private String command,key,temp;
    private int id;
    private double ispeed;
    private Mood m;
    private Socket s;
    public Sender(){};
    public Sender(Socket s) {this.s = s;};
    public void Send(String command,ClientAnswer clientAnswer) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        if(command.equals("insert_key")||command.equals("replace_if_greater")||command.equals("replace_if_lower"))
        {
            System.out.println("Please enter the person.");
            person = a.creatPerson(clientAnswer);
           ClientAnswer c = new ClientAnswer(command,person,clientAnswer.getUsername(),clientAnswer.getPassword());
           oos.writeObject(c);
           oos.flush();
           oos.close();
           byte[] bytes = bos.toByteArray();
            BufferedOutputStream outputStream = new BufferedOutputStream(s.getOutputStream());
            outputStream.write(bytes);
            outputStream.flush();
        }
        else if(command.equals("remove_key")||command.equals("remove_lower_key"))
        {
            System.out.println("Enter the key: ");
            key = ExceptionHandler.getString();
            ClientAnswer c = new ClientAnswer(command,key,clientAnswer.getUsername(),clientAnswer.getPassword());
            oos.writeObject(c);
            oos.flush();
            oos.close();
            byte[] bytes = bos.toByteArray();
            BufferedOutputStream outputStream = new BufferedOutputStream(s.getOutputStream());
            outputStream.write(bytes);
            outputStream.flush();
        }
        else if(command.equals("count_by_impact_speed"))
        {

            System.out.println("Enter the impact speed: ");
            ispeed = ExceptionHandler.getDouble(-1e18,1e18);
            ClientAnswer c = new ClientAnswer(command,ispeed,clientAnswer.getUsername(),clientAnswer.getPassword());
            oos.writeObject(c);
            oos.flush();
            oos.close();
            byte[] bytes = bos.toByteArray();
            BufferedOutputStream outputStream = new BufferedOutputStream(s.getOutputStream());
            outputStream.write(bytes);
            outputStream.flush();
        }
        else if(command.equals("filter_by_mood"))
        {

            System.out.println("Enter the mood: ");
            m= ExceptionHandler.creatMood();
            ClientAnswer c = new ClientAnswer(command,m,clientAnswer.getUsername(),clientAnswer.getPassword());
            oos.writeObject(c);
            oos.flush();
            oos.close();
            byte[] bytes = bos.toByteArray();
            BufferedOutputStream outputStream = new BufferedOutputStream(s.getOutputStream());
            outputStream.write(bytes);
            outputStream.flush();
        }
        else
        {
            ClientAnswer c = new ClientAnswer(command,clientAnswer.getUsername(),clientAnswer.getPassword());
            oos.writeObject(c);
            oos.flush();
            oos.close();
            byte[] bytes = bos.toByteArray();
            BufferedOutputStream outputStream = new BufferedOutputStream(s.getOutputStream());
            outputStream.write(bytes);
            outputStream.flush();
        }
    }
    public void SendCA(ClientAnswer clientAnswer) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(clientAnswer);
        oos.flush();
        oos.close();
        byte[] bytes = bos.toByteArray();
        BufferedOutputStream outputStream = new BufferedOutputStream(s.getOutputStream());
        outputStream.write(bytes);
        outputStream.flush();
    }
}
