package hw1.client;

import hw1.common.Processable;
import hw1.common.User;
import hw1.server.Server;

public class Client implements Processable {

    private Server server;
    private ClientWindow clientWindow;
    private User user;
    private boolean flagWorkingClient;
    private String[] textFields = new String[4];
    public Client() {
        flagWorkingClient = false;
    }
    public void setServer(Server server) {
        this.server = server;
    }
    public void setClientWindow(ClientWindow clientWindow) {
        this.clientWindow = clientWindow;
    }
    @Override
    public void connect() {
        if (identificateUserOnServer()) {
            flagWorkingClient = true;
            server.addNewClient(this);
            refreshChat(server.getChat(5));
            //"Произведено подключение к серверу чата.\n"
        } else {
            //"Сервер не отвечает.\n"
        }
    }
    @Override
    public void disconnect() {
        server.addChatMsg(user.getLogin() + " покинул чат.\n");
        flagWorkingClient = false;

        server.dellClientForRefresh(this);
    }
    private boolean identificateUserOnServer() {
        if (server.checkUser(user))
            return true;
        return false;
    }
    public String getName() {
        return user.getLogin();
    }
    public void refreshChat(String txt) {
        clientWindow.update(txt);
        clientWindow.render();
    }
    public void setUser(String[] args) {
        this.user = new User(args);
    }
    public boolean getFlagWorking() {
        return flagWorkingClient;
    }
    public boolean sendMsgToServer(String msg) {
        if (!server.checkActiveServer())
            return false;
        server.addChatMsg(user.getLogin() + ": " + msg);
        return true;
    }
}
