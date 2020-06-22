package User;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UserInteraction {
   /* private Scanner scanner;
    private String username;
    private String password;
    private ClientAnswer clientAnswer;
    private InetSocketAddress is ;
    public UserInteraction(InetSocketAddress is){
        this.is=is;
    };
    public void logined() throws IOException {
        scanner = new Scanner(System.in);
            System.out.println("Enter your Username(Email): ");
            username = scanner.nextLine().trim();
            System.out.println("Enter your Password: ");
            password = scanner.nextLine().trim();
            clientAnswer = new ClientAnswer("LOGIN", username, password);
            Sender s = new Sender(is);
            s.SendCA(clientAnswer);
        }
    public void register() throws IOException {

        scanner = new Scanner(System.in);
            System.out.println("Enter your Email: ");
            username = scanner.nextLine().trim();
            System.out.println("Enter your Password: ");
            password = scanner.nextLine().trim();

            clientAnswer = new ClientAnswer("REGISTER", username, password);
            Sender s = new Sender(is);
            s.SendCA(clientAnswer);
    }

    public ClientAnswer getClientAnswer() {
        return clientAnswer;
    }*/
}
