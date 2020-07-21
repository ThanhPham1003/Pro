package Thread;

import Server.*;
import Client.*;

import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class DispatchThread implements Runnable{
    private Client client;
    private ByteBuffer buffer;
    private SocketChannel channel;
    private Selector selector;
    private Commander cm;
    public DispatchThread(SocketChannel channel, Selector selector, Client client, ByteBuffer buffer, Commander cm) {
        this.client=client;
        this.buffer=buffer;
        this.channel=channel;
        this.selector=selector;
        this.cm=cm;
    }

    @Override
    public void run() {
        String answer = cm.interactiveModes(client);
        Manager.forkJoinPool.execute(new SendThread(channel, selector, answer, buffer));
    }
}
