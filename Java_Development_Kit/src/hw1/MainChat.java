package hw1;

import hw1.client.Client;
import hw1.client.ClientWindow;
import hw1.common.Drawable;
import hw1.server.BufferDataBase;
import hw1.server.Server;
import hw1.server.ServerWindow;

public class MainChat {
    public static void main(String[] args) {
        Server server = new Server();
        server.setDB(new BufferDataBase());
        ServerWindow serverWindow = new ServerWindow();
        server.setServerWindow(serverWindow);
        serverWindow.setServerController(server);


        Client client1 = new Client();
        ClientWindow clientWindow1 = new ClientWindow();
        client1.setServer(server);
        client1.setClientWindow(clientWindow1);
        clientWindow1.setClient(client1);

        Client client2 = new Client();
        ClientWindow clientWindow2 = new ClientWindow();
        client2.setServer(server);
        client2.setClientWindow(clientWindow2);
        clientWindow2.setClient(client2);
    }
}
