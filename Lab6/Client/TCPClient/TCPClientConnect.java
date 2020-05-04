package TCPClient;
import java.io.IOException;

import java.net.InetSocketAddress;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TCPClientConnect {

    public static void main(String[] args) {
       while (true) {
            InetSocketAddress is = new InetSocketAddress(1111);
            TCPSender s = new TCPSender(is);
            TCPReceiver r = new TCPReceiver(is);

            try {
                String command;
                Scanner commandReader = new Scanner(System.in);

                System.out.println("Choose one of the following commands: ");
                command = commandReader.nextLine().trim();
                if (command.equals("exit")) {
                    System.exit(1);
                } else {
                    s.Sender(command);
                }
                new Thread(r).start();
               Thread.sleep(1000);
            } catch (IOException e) {
                System.out.println("Couldn't connect to Server. ");
            } catch (InputMismatchException e) {
                System.out.println("The command entered is incorrect. Enter \"help\" for more information.");
           } catch (InterruptedException e) {
                System.out.println(" Error in processing interrupt: " + e.getMessage());
            }
        }
    }
}

