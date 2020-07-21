package Server;
import Thread.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class ConsoleReader {
    private InetSocketAddress is;
    private static Selector selector;
    private static ServerSocketChannel serverSocket;
    private int port;
    private ServerSocket ss;
    private Command command = new Command();
    private Commander cm = new Commander(command);
    public ConsoleReader(int port) throws IOException {
        this.port=port;
    }
    public void work() throws IOException {
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
                        System.out.println("Can Read.");
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

