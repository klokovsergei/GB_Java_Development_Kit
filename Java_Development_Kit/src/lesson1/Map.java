package lesson1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Random;
import java.util.stream.Collectors;

public class Map extends JPanel {
    private int panelWidth, panelHeight, cellHeigth, cellWidth;
    private static final int DOT_PADDING = 5;

    //поля относящиеся к игровому процессу
    private static final Random RANDOM = new Random();
    private final int HUMAN_DOT = 1;
    private final int AI_DOT = 2;
    private final int EMPTY_DOT = 0;
    private int fieldSizeY, fieldSizeX;
    private int[][] field;

    private int gameOverType;
    private static final int STATE_DRAW = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;

    private static final String MSG_WIN_HUMAN = "Победил игрок!";
    private static final String MSG_WIN_AI = "Победил компьютер!";
    private static final String MSG_WIN_DRAW = "Ничья!";

    private boolean isGameOver;
    private boolean isInitialized;

    Map() {
        setBackground(Color.BLACK);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
        isInitialized = false;
    }
    private void update(MouseEvent e) {
        if (isGameOver || !isInitialized) return;

        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                System.out.print(field[i][j]);
            }
        }
        System.out.println();

        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeigth;
        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) return;
        field[cellX][cellY] = HUMAN_DOT;

        repaint();
        if (checkEndGame(HUMAN_DOT, STATE_WIN_HUMAN)) return;
        aiTurn();
        repaint();
        if (checkEndGame(AI_DOT, STATE_WIN_AI)) return;
    }
    private boolean checkEndGame(int dot, int gameOverType) {
        if (checkWin(dot)) {
            this.gameOverType = gameOverType;
            isGameOver = true;
            repaint();
            return true;
        }
        if (isMapFull()) {
            this.gameOverType = STATE_DRAW;
            isGameOver = true;
            repaint();
            return true;
        }
        return false;
    }

    void startNewGame(int mode, int fSzX, int fSzY, int wLen) {
        fieldSizeY = fSzY;
        fieldSizeX = fSzX;
        System.out.printf("Mode: %d; \nSize: x=%d, y=%d;\nWin Length: %d \n", mode, fSzX, fSzY, wLen);
        initMap();
        isGameOver = false;
        isInitialized = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g){
        if (!isInitialized) return;
        panelWidth = getWidth();
        panelHeight = getHeight();
        cellHeigth = panelHeight / 3;
        cellWidth = panelWidth / 3;

        g.setColor(Color.YELLOW);
        for (int h = 1; h < 3; h++) {
            int y = h * cellHeigth;
            g.drawLine(0, y, panelWidth, y);
        }
        for (int w = 1; w < 3; w++) {
            int x = w * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == EMPTY_DOT) continue;

                if (field[y][x] == HUMAN_DOT) {
                    g.setColor(Color.BLUE);
                    g.fillOval(y * cellWidth + DOT_PADDING,
                            x * cellHeigth + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeigth - DOT_PADDING * 2);

                } else if (field[y][x] == AI_DOT) {
                    g.setColor(Color.RED);
                    g.fillOval(y * cellWidth + DOT_PADDING,
                            x * cellHeigth + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeigth - DOT_PADDING * 2);

                } else {
                    throw new RuntimeException("Unexpected value " + field[y][x] +
                            " in cell: x=" + x + " y=" + y);
                }
            }
        }
        if (isGameOver) showMessageGameOver(g);
    }

    private void showMessageGameOver(Graphics g) {

        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                System.out.print(field[i][j]);
            }
        }

        g.setColor(Color.GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Time new roman", Font.BOLD, 42));
        switch (gameOverType) {
            case STATE_DRAW -> g.drawString(MSG_WIN_DRAW, 180, getHeight() / 2);
            case STATE_WIN_AI -> g.drawString(MSG_WIN_AI, 20, getHeight() / 2);
            case STATE_WIN_HUMAN -> g.drawString(MSG_WIN_HUMAN, 70, getHeight() / 2);
            default -> throw new RuntimeException("Unexpected gameOver state: " + gameOverType);
        }
    }

    /**
     *  Tic Tac Toe game logic
     * */
    private void initMap() {
        field = new int[fieldSizeY][fieldSizeX];
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }
    private boolean isValidCell(int y, int x) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }
    private boolean isEmptyCell(int y, int x) {
        return field[y][x] == EMPTY_DOT;
    }
    private void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(y, x));
        field[y][x] = AI_DOT;
    }
    private boolean checkWin(int c) {
        for (int y = 0; y < fieldSizeY; y++)
            if (field[y][0]==c && field[y][1]==c && field[y][2]==c)
                return true;

        for (int x = 0; x < fieldSizeX; x++)
            if (field[0][x]==c && field[1][x]==c && field[2][x]==c)
                return true;

        if (field[0][0]==c && field[1][1]==c && field[2][2]==c) return true;
        if (field[2][0]==c && field[1][1]==c && field[0][2]==c) return true;

        return false;
    }
    private boolean isMapFull() {
        for (int i = 0; i < fieldSizeY; i++)
            for (int j = 0; j < fieldSizeX; j++)
                if (field[i][j] == EMPTY_DOT) return false;
        return true;
    }
}
