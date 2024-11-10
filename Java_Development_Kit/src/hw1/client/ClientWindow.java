package hw1.client;

import hw1.common.Drawable;
import hw1.common.User;
import hw1.server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientWindow extends JFrame implements Drawable {
    private Client client;
    private static final int WINDOW_HEIGHT = 550;
    private static final int WINDOW_WIDTH = 500;
    private static final String NAME_WINDOW = "Chat client";
    private static final String BTN_START = "Start";
    private static final String BTN_EXIT = "Exit";

    private JButton btnExit, btnLogin, btnSend;
    private JTextField serverIP, serverPort, login, textForSend;
    private JPasswordField password;
    private JPanel panIdentification, panSendText, panExit;
    private JLabel userName;
    private JTextArea chatArea;
    private String[] textFields = new String[4];

    public ClientWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle(NAME_WINDOW);
        setResizable(false);

        add(createPanelIdentification(), BorderLayout.NORTH);
        add(createChatArea());
        setVisible(true);
    }
    public void setClient(Client client) {
        this.client = client;
    }
    private Component createPanelIdentification() {
        panIdentification = new JPanel(new GridLayout(2, 3));

        panIdentification.add(createTextFieldServerIP());
        panIdentification.add(createTextFieldServerPort());
        panIdentification.add(createButtonExit());

        panIdentification.add(createTextFieldLogin());
        panIdentification.add(createTextFieldPassword());
        panIdentification.add(createButtonLogin());

        return panIdentification;
    }
    private void createPanelSend() {
        this.revalidate();
        this.repaint();
        panSendText = new JPanel();
        panSendText.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));


        textForSend = new JTextField();
        textForSend.setColumns(29);

        panSendText.add(textForSend);
        panSendText.add(createBtnSend());


        this.remove(panIdentification);
        this.add(panSendText, BorderLayout.SOUTH);
        this.repaint();
        this.revalidate();
    }
    private Component createBtnSend() {
        btnSend = new JButton("Отправить");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (client.getFlagWorking()) {
                    if (client.sendMsgToServer(textForSend.getText()))
                        textForSend.setText("");
                    else {
                        chatArea.append("Сервер не отвечает.\n");
                    }
                } else {
                    chatArea.append("Приложение клиента деактивировано.\n");
                }
            }
        });

        return btnSend;
    }
    private Component createChatArea() {

        Font font = new Font("Verdana", Font.PLAIN, 28);
        chatArea = new JTextArea();
        chatArea.setBackground(Color.WHITE);
        chatArea.setEditable(false);
        this.add(chatArea, BorderLayout.CENTER);
        return chatArea;
    }
    private void createPanelExit() {
        this.revalidate();
        this.repaint();
        panExit = new JPanel();

        Font font = new Font("Verdana", Font.PLAIN, 28);

        userName = new JLabel(textFields[2]);
        userName.setBounds(5, 5, (WINDOW_WIDTH - 100), 30);
        userName.setFont(font);
        userName.setVerticalAlignment(JLabel.CENTER);
        userName.setHorizontalAlignment(JLabel.CENTER);
        userName.setText(textFields[2]);

        panExit.add(userName);
        panExit.add(btnExit);


        this.add(panExit, BorderLayout.NORTH);
        this.repaint();
        this.revalidate();
    }
    private Component createTextFieldServerIP() {
        serverIP = new JTextField("Server IP", 15);
        serverIP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                textFields[0] = serverIP.getText();
            }
        });
        return serverIP;
    }
    private Component createTextFieldServerPort() {
        serverPort = new JTextField("Server Port", 5);
        serverPort.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                textFields[1] = serverPort.getText();
            }
        });
        return serverPort;
    }
    private Component createTextFieldLogin() {
        login = new JTextField("Login", 15);
        login.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (login.getText().length() >= 1)
                    login.setBackground(Color.WHITE);
                textFields[2] = login.getText();
            }
        });
        return login;
    }
    private Component createTextFieldPassword() {
        password = new JPasswordField("Password", 20);
        password.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JPasswordField field = (JPasswordField) e.getSource();
                char[] passwordText = field.getPassword();

                if (passwordText.length < 8) {
                    password.setBackground(Color.RED);
                } else if (passwordText.length >= 8) {
                    password.setBackground(Color.WHITE);
                    textFields[3] = password.getText();
                }
            }
        });

        return password;
    }
    private Component createButtonLogin() {
        btnLogin = new JButton(BTN_START);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (checkTextFields()) {
                     client.setUser(textFields);
                     client.connect();
                     if (client.getFlagWorking()) {
                         chatArea.setText("Произведено подключение к серверу чата.\n");
                         createPanelSend();
                         createPanelExit();
                         repaint();
                     } else {
                         chatArea.append("Сервер не отвечает.\n");
                     }
                }
            }
        });
        return btnLogin;
    }
    private Component createButtonExit() {
        btnExit = new JButton(BTN_EXIT);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                client.disconnect();
            }
        });
        return btnExit;
    }

    public String getName() {
        return textFields[2];
    }

    @Override
    public void render() {
        chatArea.repaint();
    }

    @Override
    public void update(String msg) {
        chatArea.append(msg);
    }
    private boolean checkTextFields() {
        boolean flag = true;
        if (textFields[0] == null) {
            serverIP.setBackground(Color.RED);
            flag = false;
        }
        if (textFields[1] == null) {
            serverPort.setBackground(Color.RED);
            flag = false;
        }
        if (textFields[2] == null) {
            login.setBackground(Color.RED);
            flag = false;
        }
        return flag;
    }
}
