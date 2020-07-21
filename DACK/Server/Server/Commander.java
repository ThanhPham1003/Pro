package Server;

import Client.Client;

public class Commander {
    private Command manager;
    public Commander(Command manager) {
        this.manager = manager;
    }
    public String interactiveModes(Client client)
    {
        switch (client.getCommand())
        {
            case "Login":
                return manager.Login(client);
            case "Register":
                return manager.Register(client);
            default:
                return "Missing command.";
        }
    }
}
