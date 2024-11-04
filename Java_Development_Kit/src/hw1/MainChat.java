package hw1;

import hw1.client.ClientWindow;
import hw1.server.ServerWindow;

import java.util.Scanner;

public class MainChat {
    public static void main(String[] args) {
        ServerWindow server = new ServerWindow();
        ClientWindow client1 = new ClientWindow(server);
        ClientWindow client2 = new ClientWindow(server);

    }
}
