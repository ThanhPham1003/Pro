package Server;
import Object.*;
import Threads.Manager;
import Threads.ReadThread;
import User.ClientAnswer;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ConsoleReader {
    private InetSocketAddress is;
    private static Selector selector;
    private static ServerSocketChannel serverSocket;
    private int port;
    private ServerSocket ss;
    private CollectionManager collectionManager = new CollectionManager();
    private Commander cm = new Commander(collectionManager);
    public ConsoleReader(int port) throws IOException {
        this.port=port;
    }
    public void work() throws IOException, ClassNotFoundException, InterruptedException, SQLException {
        DataBase db = new DataBase();
        ResultSet rs = db.executeQuery("SELECT * FROM HumanBeing;");
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
        System.out.println("Server is opening...");
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.configureBlocking(false);
        serverSocket.bind(new InetSocketAddress(port));


        while (true) {
            try {
                Selector selector = accept();
                SocketChannel channel = null;
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                for (SelectionKey key : selectedKeys) {
                    if (key.isReadable()) {
                        Manager.fixedTheadPool.execute(new ReadThread((SocketChannel) key.channel(),selector,cm));
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static Selector accept() throws IOException {
        selector = Selector.open();
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            for (SelectionKey key : selectedKeys) {
                if (key.isAcceptable()) {
                    key.cancel();
                    SocketChannel client = serverSocket.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                    return selector;
                }
            }
        }
    }
}

        /*while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                System.out.println("waiting for client");

                ClientAnswer ca = (ClientAnswer) ois.readObject();
                String a = cm.interactiveModes(ca);
                ServerSender send = new ServerSender(s, a);
                new Thread(send).start();
                Thread.sleep(1000);
            }catch (SocketException | EOFException e)
            {
                System.out.println("No signal received from client");
                System.exit(0);
            }
        }
    }
}*/