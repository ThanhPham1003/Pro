package Server;
import Client.*;

import java.io.*;
import java.util.ArrayList;

public class Command {
    public Command(){};
    public String Login(Client client)
    {
        if(client.getUsername().isEmpty())
        {
            return "Account was null.";
        }
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader("F:\\ChatServer\\account.txt"));
            String temp;
            while ((temp = br.readLine())!=null)
            {
                if(client.getUsername().equals(temp))
                {
                    String temp2 = br.readLine();
                    if(client.getPassword().equals(temp2))
                    {
                        return "Connected. You can chat now.";
                    }
                    else return "Password wrong.";
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println("Can't read file. File might does not exist.");
        } catch (IOException e) {
            System.err.println("Can't readline.");
        }
        return "Account does not exist.";
    }
    public String Register(Client client)
    {
        if(client.getUsername().isEmpty())
        {
            return "Account was null.";
        }
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader("F:\\ChatServer\\account.txt"));
            String temp = null;
            while ((temp = br.readLine())!= null)
            {
                if(client.getUsername().equals(temp))
                {
                    return "Account already exists. ";
                }
            }
            BufferedWriter bw = null;
            FileWriter fw = null;
            File file = new File("F:\\ChatServer\\account.txt");
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(client.getUsername() + "\n" + client.getPassword() + "\n");
        } catch (FileNotFoundException e) {
            System.err.println("Can't read file. File might does not exist.");
        } catch (IOException e) {
            System.err.println("Can't readline or write to file.");
        }
        return "Connected. Account created.";
    }
}
