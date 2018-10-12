package Lesson_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel {

    public static final int MODE_H_V_A = 0;
    public static final int MODE_H_V_H = 1;

    public static final char DOT_EMPTY = '*';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    char current_dot;

    char[][] field;
    int fieldSizeX;
    int fieldSizeY;

    int x_shift;
    int y_shift;
    int x_cell_quarter;
    int y_cell_quarter;

    int winLen;
    int mode;

    int cellHeight;
    int cellWidth;


    boolean isInitialized = false;

    public Map() {
        setBackground(Color.ORANGE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });

    }

    void update(MouseEvent e) {
        int x = e.getX() / cellWidth;
        int y = e.getY() / cellHeight;

        if (!isInitialized || field[x][y] != DOT_EMPTY) {
            return;
        }

        switch (mode) {
            case (MODE_H_V_A):
                field[x][y] = DOT_X;
                repaint();
                checkTurn(DOT_X);
                turnAI();
                repaint();
                checkTurn(DOT_O);
                break;
            case (MODE_H_V_H):
                field[x][y] = current_dot;
                repaint();
                checkTurn(current_dot);
                current_dot = (current_dot == DOT_X) ? DOT_O : DOT_X;
                break;
        }
    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLen) {
        this.mode = mode;
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLen = winLen;
        createMatrix(fieldSizeX, fieldSizeY);

        current_dot = DOT_X;
        isInitialized = true;
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    void render(Graphics g) {

        if (!isInitialized) {
            return;
        }

        int panelWidth = getWidth();
        int panelHeigth = getHeight();

        cellHeight = panelHeigth / fieldSizeY;
        cellWidth = panelWidth / fieldSizeX;
        x_cell_quarter = cellWidth / 4;
        y_cell_quarter = cellHeight / 4;

        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }


        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeigth);
        }

        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                x_shift = i * cellWidth;
                y_shift = j * cellHeight;
                switch (field[i][j]) {
                    case DOT_X:
                        g.drawLine(x_shift + 3 * x_cell_quarter, y_shift + y_cell_quarter,
                                x_shift + x_cell_quarter, y_shift + 3 * y_cell_quarter);
                        g.drawLine(x_shift + x_cell_quarter, y_shift + y_cell_quarter,
                                x_shift + 3 * x_cell_quarter, y_shift + 3 * y_cell_quarter);
                        break;
                    case DOT_O:
                        g.drawOval(x_shift + x_cell_quarter, y_shift + y_cell_quarter,
                                2 * x_cell_quarter, 2 * y_cell_quarter);
                        break;
                }
            }
        }

    }

    void createMatrix(int x, int y) {
        field = new char[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                field[i][j] = DOT_EMPTY;
            }
        }
    }


    boolean checkLine(char sym, char[] line) {
        boolean sliceFound = false;

//        for (int l = 0; l < line.length ; l++) {
//            System.out.print(line[l] + " ");
//        }
//        System.out.println();

        for (int x = 0; x <= line.length - winLen; x++) {
//            System.out.print(line[x] + " ");
            sliceFound = true;
            for (int j = 0; j < winLen; j++) {
                if (line[x + j] != sym) {
                    sliceFound = false;
                    break;
                }
            }
            if (sliceFound) {
//                System.out.println("We found slice");
                return true;
            }
        }
        return false;
    }

    boolean checkWin(char sym) {

        char line[];

        //Check horizontal lines
        line = new char[fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                line[x] = field[x][y];
            }
            if (checkLine(sym, line)) return true;
        }

        //Check vertical lines
        line = new char[fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                line[y] = field[x][y];
            }
            if (checkLine(sym, line)) return true;
        }

        // Common variables for diagonale check
        int line_size;
        int x_initial;
        int y_initial;
        int x_line_start;
        int y_line_start;
        int x;
        int y;

        //Check diagonale lines from south-west angle. E.g. 11 lines for 5x5 pool
        x_initial = 1 - fieldSizeX;
        y_initial = fieldSizeY - 1;

        for (int l = 0; l <= (2 * (fieldSizeX - 1)); l++) { //Generate starting coordinates for each line and creating it
            x_line_start = x_initial + l;
            y_line_start = y_initial - l;
            if (x_line_start < 0) x_line_start = 0; //Cut off x coordinates beyond game pool
            if (y_line_start < 0) y_line_start = 0; //Cut off y coordinates beyond game pool;

            line_size = fieldSizeX - x_line_start - y_line_start;
            line = new char[line_size];

            for (int i = 0; i < line_size; i++) { //Generate sequence coordinates for each line and filling it
                x = x_line_start + i;
                y = y_line_start + i;
                line[i] = field[x][y];
            }
            if (checkLine(sym, line)) return true;
        }

        //Check diagonale lines from south-east angle.
        x_initial = 2 * (fieldSizeX - 1);
        y_initial = fieldSizeY - 1;

        for (int l = 0; l <= (2 * (fieldSizeX - 1)); l++) { //Generate starting coordinates for each line and creating it
            x_line_start = x_initial - l;
            y_line_start = y_initial - l;
            if (x_line_start > fieldSizeX - 1) x_line_start = fieldSizeX - 1;   //Cut off x coordinates beyond game pool
            if (y_line_start < 0) y_line_start = 0;                 //Cut off y coordinates beyond game pool;

//            System.out.printf("x_line_start=%d, y_line_start=%d, l=%d ", x_line_start, y_line_start, l);
            line_size = 1 + x_line_start - y_line_start;
            line = new char[line_size];

            for (int i = 0; i < line_size; i++) { //Generate sequence coordinates for each line and filling it
                x = x_line_start - i;
                y = y_line_start + i;
//                System.out.printf("%d:%d ", x, y);
                line[i] = field[x][y];
            }
//            System.out.println();
            if (checkLine(sym, line)) return true;
        }
        return false;
    }

    void turnAI() {
        int x, y;
        boolean wrong_turn = true;
        Random rn = new Random();

        do {
            y = rn.nextInt(fieldSizeX);
            x = rn.nextInt(fieldSizeY);
            if (field[x][y] == DOT_EMPTY) {
                field[x][y] = DOT_O;
                wrong_turn = false;
            }
        } while (wrong_turn);
    }

    boolean checkDraw() {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (field[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    boolean checkTurn(char sym) {

        if (checkWin(sym)) {
            if (sym == DOT_X) {
                switch (mode) {
                    case (MODE_H_V_A):
                        showGameOver("You're win!");
                        break;
                    case (MODE_H_V_H):
                        showGameOver("Gamer with X win!");
                        break;
                }
            } else {
                switch (mode) {
                    case (MODE_H_V_A):
                        showGameOver("AI win!");
                        break;
                    case (MODE_H_V_H):
                        showGameOver("Gamer with O win!");
                        break;
                }
            }
            return true;
        } else if (checkDraw()) {
            showGameOver("The draw!");
            return true;
        }
        return false;
    }

    void showGameOver(String message) {
//        System.out.println(message);
        JPanel dialogPanel = new JPanel();
        JLabel messageLabel = new JLabel(message);
        dialogPanel.add(messageLabel);

        int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Game over!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }
}