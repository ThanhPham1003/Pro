package Threads;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class SendThread implements Runnable{
    private ByteBuffer buffer;
    private SocketChannel channel;
    private Selector selector;
    private String result;
    public SendThread(SocketChannel channel, Selector selector, String result, ByteBuffer buffer)
    {
        this.buffer=buffer;
        this.channel=channel;
        this.selector=selector;
        this.result = result;
    }

    @Override
    public void run() {
        try{
            buffer.clear();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(result);
            out.flush();
            byte[] bytes = bos.toByteArray();
            buffer = ByteBuffer.wrap(bytes);

            channel = null;
            while (channel == null) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                for (SelectionKey key : selectedKeys) {
                    if (key.isWritable()) {
                        channel = (SocketChannel) key.channel();
                        while (buffer.hasRemaining()) {
                            channel.write(buffer);
                        }
                        break;
                    }
                }
            }
            Thread.sleep(1000);
            channel.socket().close();
            channel.close();
            selector.close();
        } catch (IOException | InterruptedException e) {
            System.out.println("Data transfer error");
        }
    }
}
