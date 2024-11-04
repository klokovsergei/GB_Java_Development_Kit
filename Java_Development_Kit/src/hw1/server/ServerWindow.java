package hw1.server;


import hw1.client.ClientWindow;
import hw1.client.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServerWindow extends JFrame {
    private static final int WINDOW_HEIGHT_STOP = 150;
    private static final int WINDOW_WIDTH_STOP = 300;
    private static final int WINDOW_HEIGHT_START = 450;
    private static final int WINDOW_WIDTH_START = 450;
    private static final String NAME_WINDOW = "Chat server";
    private static final String BTN_START = "Start";
    private static final String BTN_STOP = "Stop";

    private static Set<User> users = new HashSet<>();
    static {
        users.add(new User("user1", "11111111"));
        users.add(new User("user2", "22222222"));
    }
    public static final String serverIP = "1.1.1.1";
    public static final String serverPort = "1234";
    StringBuilder messages = new StringBuilder();

    private JButton btnStart;
    private JButton btnStop;
    private JPanel panBotton, panChat;
    private JTextArea chatArea;
    private boolean flagWorkingServer, changedWidow;

    //текст чата
    private StringBuilder chat = new StringBuilder();

    public ServerWindow() {
        flagWorkingServer = false;
        changedWidow = false;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(10, 10);
        setSize(WINDOW_WIDTH_STOP, WINDOW_HEIGHT_STOP);
        setTitle(NAME_WINDOW);
        setResizable(false);

        panBotton = new JPanel(new GridLayout(1, 2));
        panBotton.add(createButtonStart());
        panBotton.add(createButtonStop());
        add(panBotton);

        setVisible(true);
    }
    private Component createButtonStart() {
        btnStart = new JButton(BTN_START);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!flagWorkingServer) {
                    flagWorkingServer = true;
                    startNewServer();
                    repaint();
                }
            }
        });
        return btnStart;
    }
    private Component createButtonStop() {
        btnStop = new JButton(BTN_STOP);
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (flagWorkingServer) {
                    stopServer();
                    flagWorkingServer = false;
                }
            }
        });
        return btnStop;
    }
    private List<ClientWindow> clientWindowsList = new ArrayList<>();
    void startNewServer() {
        if (!changedWidow){
            this.revalidate();
            this.repaint();
            setSize(WINDOW_WIDTH_START, WINDOW_HEIGHT_START);

            this.add(panBotton, BorderLayout.SOUTH);
            this.add(createPanelChat(), BorderLayout.CENTER);
            btnStart.setEnabled(false);
            this.repaint();
            changedWidow = true;
        }
        btnStop.setEnabled(true);
        btnStart.setEnabled(false);
        addChatMsg("Сервер запущен.");
        //изменить размер, добавить панель с сообщениями
    }
    private Component createPanelChat() {
        this.revalidate();
        this.repaint();
        panChat = new JPanel();

        Font font = new Font("Verdana", Font.PLAIN, 28);

        chatArea = new JTextArea(chat.toString());
        chatArea.setBackground(Color.WHITE);
        chatArea.setEditable(false);

        panChat.add(chatArea);

        this.add(panChat, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
        return new JScrollPane(panChat);
    }
    void stopServer() {
        btnStop.setEnabled(false);
        btnStart.setEnabled(true);
        addChatMsg("Сервер остановлен.");

        //сохранить все сообщения в файл
        //изменить размер окна (в первоначальное)
    }
    public boolean checkUser(User user) {
        for (var u : users) {
            if (user.equals(u))
                return true;
        }
        return false;
    }
    public void addChatMsg(String txt) {
        if (flagWorkingServer)
            chat.append(txt + System.lineSeparator());
        chatArea.setText(chat.toString());
        chatArea.revalidate();
        chatArea.repaint();
        clientWindowsList.stream().forEach(x -> x.refreshChat());
    }
    public boolean checkActiveServer() {
        return flagWorkingServer;
    }
    public String getChat() {
        return chat.toString();
    }
    public void addNewClient(ClientWindow client) {
        clientWindowsList.add(client);
        System.out.println(clientWindowsList);
    }
    public void dellClientForRefresh (ClientWindow client) {
        clientWindowsList.remove(client);
        System.out.println(clientWindowsList);
    }
}
