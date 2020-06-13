package Server;
import Object.*;
import User.ClientAnswer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsoleReader {
    private InetSocketAddress is;
    private ServerSocket ss;
    private CollectionManager collectionManager = new CollectionManager();
    private Commander cm = new Commander(collectionManager);
    public ConsoleReader(ServerSocket ss) throws IOException {
        this.ss=ss;
    }
    public void work() throws IOException, ClassNotFoundException, InterruptedException, SQLException {
        DataBase db = new DataBase();
        ResultSet rs = db.executeQuery("SELECT * FROM HumanBeing");
        while (rs.next())
        {
            try {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double x = rs.getDouble("x");
                double y = rs.getDouble("y");
                Coordinates coordinates = new Coordinates(x, y);
                long impactSpeed = rs.getLong("impactSpeed");
                boolean realHero = rs.getBoolean("realHero");
                boolean hasToothpick = rs.getBoolean("hasToothpick");
                WeaponType weaponType = WeaponType.valueOf(rs.getString("weaponType"));
                Mood mood = Mood.valueOf(rs.getString("Mood"));
                String carName = rs.getString("carName");
                boolean carCool = rs.getBoolean("carCool");
                Car car = new Car(carName, carCool);
                String owner = rs.getString("owner");
                HumanBeing person = new HumanBeing(id, name, coordinates, impactSpeed, realHero, hasToothpick, weaponType, mood, car, owner);
                collectionManager.getPeople().put(String.valueOf(person.hashCode()), person);
            }catch (IllegalArgumentException x)
            {
                System.out.println("Error with Enum.");
            }
        }
        Socket s = ss.accept();

        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                System.out.println("waiting for client");

                ClientAnswer ca = (ClientAnswer) ois.readObject();
                String a = cm.interactiveModes(ca);
                ServerSender send = new ServerSender(s, a);
                new Thread(send).start();
                Thread.sleep(1000);
            }catch (SocketException e)
            {
                System.out.println("Error connection between Client and Server: " +e.getMessage());
            }
        }
    }
}
