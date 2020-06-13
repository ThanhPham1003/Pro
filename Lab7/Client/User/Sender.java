package User;
import Object.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class Sender implements Serializable {
    private HumanBeing person;
    private HumanBeing a = new HumanBeing();
    private String command,key,temp;
    private int id;
    private double ispeed;
    private Mood m;
    private SocketChannel ssChannel;
    public Sender(){};
    public Sender(SocketChannel ssChannel) {this.ssChannel = ssChannel;};
    public void Send(String command,ClientAnswer clientAnswer) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(ssChannel.socket().getOutputStream());
        if(command.equals("insert_key")||command.equals("replace_if_greater")||command.equals("replace_if_lower"))
        {
            System.out.println("Please enter the person.");
            person = a.creatPerson(clientAnswer);
           ClientAnswer c = new ClientAnswer(command,person,clientAnswer.getUsername(),clientAnswer.getPassword());
           oos.writeObject(c);
        }
        else if(command.equals("remove_key")||command.equals("remove_lower_key"))
        {
            System.out.println("Enter the key: ");
            key = ExceptionHandler.getString();
            ClientAnswer c = new ClientAnswer(command,key,clientAnswer.getUsername(),clientAnswer.getPassword());
            oos.writeObject(c);
        }
        else if(command.equals("count_by_impact_speed"))
        {

            System.out.println("Enter the impact speed: ");
            ispeed = ExceptionHandler.getDouble(-1e18,1e18);
            ClientAnswer c = new ClientAnswer(command,ispeed,clientAnswer.getUsername(),clientAnswer.getPassword());
            oos.writeObject(c);
        }
        else if(command.equals("filter_by_mood"))
        {

            System.out.println("Enter the mood: ");
            m= ExceptionHandler.creatMood();
            ClientAnswer c = new ClientAnswer(command,m,clientAnswer.getUsername(),clientAnswer.getPassword());
            oos.writeObject(c);
        }
        else
        {
            ClientAnswer c = new ClientAnswer(command,clientAnswer.getUsername(),clientAnswer.getPassword());
            oos.writeObject(c);
        }
    }
    public void SendCA(ClientAnswer clientAnswer) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(ssChannel.socket().getOutputStream());
        oos.writeObject(clientAnswer);
    }
}
