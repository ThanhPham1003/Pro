package TCPClient;
import Object.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class TCPSender {

    private HumanBeing person;
    private HumanBeing a = new HumanBeing();
    private String command,key,temp;
    private int id;
    private double ispeed;
    private Mood m;
    private InetSocketAddress is;
    public TCPSender(InetSocketAddress is) {this.is = is;};
    public void Sender(String command) throws IOException {
        SocketChannel ssChannel = SocketChannel.open(is);
        ObjectOutputStream oos = new ObjectOutputStream(ssChannel.socket().getOutputStream());
        if(command.equals("insert_key")||command.equals("replace_if_greater")||command.equals("replace_if_lower"))
        {

            System.out.println("Please enter the person.");
            person = a.creatPerson();
            oos.writeObject(command);
            oos.writeObject(person);
        }
        else if(command.equals("man"))
        {

            System.out.println("Please enter the command: ");
            temp = ExceptionHandler.getString();
            oos.writeObject(command);
            oos.writeObject(temp);
        }
        else if(command.equals("update_id"))
        {
            System.out.println("Please enter the id:");
            id = ExceptionHandler.getInt();
            command = command+ " " + String.valueOf(id);
            person = a.creatPerson();
            oos.writeObject(command);
            oos.writeObject(person);
        }
        else if(command.equals("remove_key")||command.equals("remove_lower_key"))
        {

            System.out.println("Enter the key: ");
            key = ExceptionHandler.getString();
            oos.writeObject(command);
            oos.writeObject(key);
        }
        else if(command.equals("count_by_impact_speed"))
        {

            System.out.println("Enter the impact speed: ");
            ispeed = ExceptionHandler.getDouble(-1e18,1e18);
            oos.writeObject(command);
            oos.writeObject(ispeed);
        }
        else if(command.equals("filter_by_mood"))
        {

            System.out.println("Enter the mood: ");
            m=ExceptionHandler.creatMood();
            oos.writeObject(command);
            oos.writeObject(m);
        }
        else
        {
            oos.writeObject(command);
            oos.writeObject("Nothing.");
        }
        oos.close();
    }
}
