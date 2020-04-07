import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;


public class UDO {
    public static void send() throws IOException {
        String a ="thanh";
        double b = 125.5;
        DatagramSocket ds = new DatagramSocket();

        InetAddress ip = InetAddress.getLocalHost();
        byte buf1[] = a.getBytes();
        DatagramPacket Dpsend1 = new DatagramPacket(buf1,buf1.length,ip,1234);
        ds.send(Dpsend1);
        byte buf2[] = ByteBuffer.allocate(8).putDouble(b).array();
        DatagramPacket Dpsend2 = new DatagramPacket(buf2,buf2.length,ip,1234);
        ds.send(Dpsend2);

    }
    public static void receive() throws IOException {
        DatagramSocket ds = new DatagramSocket(1234);
        byte[] receive = new byte[65535];

        DatagramPacket DpReceive1 = null;

            // Step 2 : create a DatgramPacket to receive the data.
            DpReceive1 = new DatagramPacket(receive, receive.length);

            // Step 3 : revieve the data in byte buffer.
            ds.receive(DpReceive1);

            System.out.println("String:-" + data(receive));

            // Exit the server if the client sends "bye"
            // Clear the buffer after every message.
            receive = new byte[65535];
         DatagramPacket DpReceive2 = null;
            DpReceive2 = new DatagramPacket(receive,receive.length);
            ds.receive(DpReceive2);
            System.out.println("Double: " + toDouble(receive));


    }
    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
    public static double toDouble(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getDouble();
    }

    public static void main(String[] args) throws IOException {
        send();
        receive();
    }

}
