package lesson1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {

    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;

    JButton btnStart;
    public SettingsWindow(GameWindow gameWindow){
        btnStart = new JButton("Start new game");
        setLocationRelativeTo(gameWindow);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gameWindow.startNewGame(0, 3, 3, 3);
                setVisible(false);
            }
        });

        add(btnStart);
    }
}
