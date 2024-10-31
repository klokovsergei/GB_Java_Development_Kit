package lesson1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
//    private static final int WINDOW_POSX = 800;
//    private static final int WINDOW_POSY = 300;

    private JButton btnStart = new JButton("New Game");
    private JButton btnExit = new JButton("Exit");
    private Map map;
    private SettingsWindow settings;
    GameWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocation(WINDOW_POSX, WINDOW_POSY);
        setLocationRelativeTo(null); //создать по середине экрана
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("TicTacToe");
        setResizable(false);

        map = new Map();
        settings = new SettingsWindow(this);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                settings.setVisible(true);
            }
        });
        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);
        panBottom.add(btnExit);
        add(panBottom, BorderLayout.SOUTH);
        add(map);
        setVisible(true);
    }

    void startNewGame(int mode, int fSzX, int fSzY, int wLen) {
        map.startNewGame(mode, fSzX, fSzY, wLen);
    }
}
