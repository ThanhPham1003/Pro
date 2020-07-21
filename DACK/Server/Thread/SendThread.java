package Thread;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class SendThread implements Runnable{
    private String answer;
    ByteBuffer buffer;
    SocketChannel channel;
    Selector selector;

    public SendThread(SocketChannel channel, Selector selector, String answer, ByteBuffer buffer) {
        this.answer=answer;
        this.buffer=buffer;
        this.channel=channel;
        this.selector=selector;
    }

    @Override
    public void run() {
        try {
            buffer.clear();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(answer);
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
                System.out.println("Send result to client.");
            }
            Thread.sleep(1000);
            channel.socket().close();
            channel.close();
            selector.close();
        } catch (IOException | InterruptedException e) {
            System.out.println("Write Data error: " +e.getMessage());
        }
    }
        /*try{
            System.out.println("Server send object.");
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(result);
            oos.flush();
        } catch (IOException e) {
            System.out.println("Write data error.");
        }
    }*/
}
