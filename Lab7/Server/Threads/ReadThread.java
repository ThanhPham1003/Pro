package Threads;

import User.ClientAnswer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ReadThread implements Runnable{
    private SocketChannel channel;
    private Selector selector;
    public ReadThread(SocketChannel channel, Selector selector)
    {
        this.channel = channel;
        this.selector = selector;
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
            ClientAnswer clientAnswer = (ClientAnswer) inputStream.readObject();
            Manager.forkJoinPool.execute(new DispatchThread(channel,selector,clientAnswer,buffer));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Data transfer error.");
        }
    }

}
