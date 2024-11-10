package hw1.server;


import hw1.client.ClientWindow;
import hw1.common.Checkable;
import hw1.common.Drawable;
import hw1.common.Processable;
import hw1.common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServerWindow extends JFrame implements Drawable, Processable {
    private Server serverController;
    private static final int WINDOW_HEIGHT_STOP = 150;
    private static final int WINDOW_WIDTH_STOP = 300;
    private static final int WINDOW_HEIGHT_START = 450;
    private static final int WINDOW_WIDTH_START = 450;
    private static final String NAME_WINDOW = "Chat server";
    private static final String BTN_START = "Start";
    private static final String BTN_STOP = "Stop";
    private JButton btnStart, btnStop;
    private JPanel panBotton;
    private JTextArea chatArea;
    private boolean changedWidow;

    public ServerWindow() {
        changedWidow = false;
        settings();

        panBotton = new JPanel(new GridLayout(1, 2));
        panBotton.add(createButtonStart());
        panBotton.add(createButtonStop());
        add(panBotton);

        setVisible(true);
    }
    public void setServerController(Server serverController) {
        this.serverController = serverController;
    }
    private void settings() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocation(10, 10);
        setSize(WINDOW_WIDTH_STOP, WINDOW_HEIGHT_STOP);
        setTitle(NAME_WINDOW);
        setResizable(false);
    }
    private Component createButtonStart() {
        btnStart = new JButton(BTN_START);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!serverController.checkActiveServer()) {
                    connect();
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
                if (serverController.checkActiveServer()) {
                    disconnect();
                }
            }
        });
        return btnStop;
    }
    private void startNewServer() {
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
        serverController.addChatMsg("Сервер запущен.");
    }
    private Component createPanelChat() {
        this.revalidate();
        this.repaint();

        chatArea = new JTextArea(serverController.getChat());
        chatArea.setBackground(Color.WHITE);
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Verdana", Font.PLAIN, 12));
        this.repaint();
        this.revalidate();
        return new JScrollPane(chatArea);
    }
    @Override
    public void connect() {
        serverController.startServer();
        startNewServer();

        render();
    }
    @Override
    public void disconnect() {
        btnStop.setEnabled(false);
        btnStart.setEnabled(true);
        serverController.addChatMsg("Сервер остановлен.");
        serverController.stopServer();
    }
    @Override
    public void update(String msg) {
        chatArea.append(msg);
        render();
    }
    public void render() {
        chatArea.revalidate();
        chatArea.repaint();
    }
    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING){
            serverController.setUsingGUI();
        }
    }
}
