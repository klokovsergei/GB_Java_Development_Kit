package hw1.server;

import hw1.client.Client;
import hw1.client.ClientWindow;
import hw1.common.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Server implements Checkable {
    private Drawable serverWindow;
    private boolean flagWorkingServer, usingGUI;
    public static final String serverIP = "1.1.1.1";
    public static final String serverPort = "1234";
    private IOInterface db;
    private static Set<User> users = new HashSet<>();
    private List<Client> clientList = new ArrayList<>();
    static {
        users.add(new User("user1", "11111111"));
        users.add(new User("user2", "22222222"));
    }
    public Server() {
        flagWorkingServer = false;
        usingGUI = false;
    }
    public void setServerWindow(Drawable serverWindow) {
        this.serverWindow = serverWindow;
        usingGUI = true;
    }
    public void setDB(IOInterface db) {
        this.db = db;
    }
    public boolean checkUser(User user) {
        for (var u : users) {
            if (user.equals(u)) {
                System.out.println("пользователь " + u.getLogin() + " есть на сервере.");
                return true;
            }
        }
        return false;
    }
    public boolean checkActiveServer() {
        return flagWorkingServer;
    }
    public void setUsingGUI() {
        usingGUI = false;
    }
    public void startServer() {
        flagWorkingServer = true;
        if (db instanceof Processable)
            ((Processable) db).connect();
    }
    public void stopServer() {
        flagWorkingServer = false;
        if (db instanceof Processable)
            ((Processable) db).disconnect();
    }
    public void addChatMsg(String txt) {
        if (flagWorkingServer) {
            serverWindow.update(txt);
            if (!txt.matches("(.*)\\s"))
                db.append(txt + System.lineSeparator());
            else
                db.append(txt);
            clientList.stream().forEach(x -> x.refreshChat(txt));
            if (!txt.matches("(.*)\\s")) {
                clientList.stream().forEach(x -> x.refreshChat(System.lineSeparator()));
                serverWindow.update(System.lineSeparator());
            }
        }
    }
    public String getChat(int i) {
        return db.readFewLastLine(i);
    }
    public String getChat() {
        return db.read();
    }
    public void addNewClient(Client client) {
        clientList.add(client);
        addChatMsg("К чату подключился " + client.getName());
    }
    public void dellClientForRefresh (Client client) {
        clientList.remove(client);
    }
}

