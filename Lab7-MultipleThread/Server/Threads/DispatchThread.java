package Threads;

import Server.CollectionManager;
import Server.Commander;
import User.ClientAnswer;

import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class DispatchThread implements Runnable{
    private ClientAnswer clientAnswer;
    private ByteBuffer buffer;
    private SocketChannel channel;
    private Selector selector;
    private Commander cm;
    public DispatchThread(SocketChannel channel, Selector selector, ClientAnswer clientAnswer, ByteBuffer buffer, Commander cm) {
        this.clientAnswer=clientAnswer;
        this.buffer=buffer;
        this.channel=channel;
        this.selector=selector;
        this.cm=cm;
    }

    @Override
    public void run() {
        String answer = cm.interactiveModes(clientAnswer);
        Manager.forkJoinPool.execute(new SendThread(channel, selector, answer, buffer));
    }
}
