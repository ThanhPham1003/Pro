package Thread;

import Server.Commander;
import Client.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ReadThread implements Runnable{
    private SocketChannel channel;
    private Selector selector;
    private Commander cm;
    // RouteCollection collection;

    public ReadThread(SocketChannel channel, Selector selector, Commander cm) {
        this.channel = channel;
        this.selector = selector;
        this.cm=cm;
    }

    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(16384);
        try{
            channel.read(buffer);
            buffer.flip();
            channel.register(selector, SelectionKey.OP_WRITE);
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes);
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            Client client = (Client) inputStream.readObject();
            System.out.println("Read from client.");
            Manager.forkJoinPool.execute(new DispatchThread(channel,selector,client,buffer,cm));
        } catch (IOException | ClassNotFoundException e) {
            //System.out.println("Read Data error:" + e.getMessage());
        }
        /*try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            System.out.println("waiting for client");
            ClientAnswer ca = (ClientAnswer) ois.readObject();
            Manager.forkJoinPool.execute(new DispatchThread(ca,s));
        }catch (IOException| ClassNotFoundException e)
        {
            System.out.println("Read error.");
        }*/
    }

}
