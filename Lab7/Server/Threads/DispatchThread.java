package Threads;

import Server.CollectionManager;
import Server.Commander;
import User.ClientAnswer;

import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class DispatchThread implements Runnable{
    private ClientAnswer clientAnswer;
    private ByteBuffer buffer;
    private SocketChannel channel;
    private CollectionManager collectionManager = new CollectionManager();
    private Commander cm = new Commander(collectionManager);
    Selector selector;
    public DispatchThread(SocketChannel channel, Selector selector, ClientAnswer clientAnswer,ByteBuffer buffer)
    {
        this.buffer=buffer;
        this.channel=channel;
        this.clientAnswer=clientAnswer;
        this.selector= selector;
    }

    @Override
    public void run() {
        try{
            String answer = cm.interactiveModes(clientAnswer);
            Manager.forkJoinPool.execute(new SendThread(channel,selector,answer,buffer));
        }catch(Exception e)
        {
            System.out.println("Request processing error");
        }
    }
}
