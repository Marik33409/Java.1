package Lesson_8;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartNewGameWindow extends JFrame {

    private final GameWindow gameWindow;

    private static final int WIN_HEIGHT = 230;
    private static final int WIN_WIDTH = 350;
    private static final int MIN_WIN_LEN = 3;
    private static final int MAX_WIN_LEN = 10;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final String STR_WIN_LEN = "Winnig Len: ";
    private static final String STR_FIELD_SIZE = "Field Size: ";

    private JRadioButton jrbHunVsAi = new JRadioButton("Human vs Ai", true);
    private JRadioButton jrbHunVsHum = new JRadioButton("Human vs Human");
    private ButtonGroup gameMode = new ButtonGroup();

    private JSlider slFieldSize;
    private JSlider slWinLen;

    public StartNewGameWindow(GameWindow gameWindow) throws HeadlessException {
        this.gameWindow = gameWindow;
        setTitle("New game params");
        setSize(WIN_WIDTH, WIN_HEIGHT);
        Rectangle gameWindowBounds = gameWindow.getBounds();
        int posX = (int) (gameWindowBounds.getCenterX() - WIN_WIDTH / 2);
        int posY = (int) (gameWindowBounds.getCenterY() - WIN_HEIGHT / 2);
        setLocation(posX, posY);

        setLayout(new GridLayout(10, 1));
        addGameControlMode();
        GameControlFieldWinLen();

        JButton btnStartGame = new JButton("Start a game");
        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStartGameClick();
            }
        });
        add(btnStartGame);

    }

    void btnStartGameClick() {
        int gameMode;
        if (jrbHunVsAi.isSelected()) {
            gameMode = Map.MODE_H_V_A;
        } else {
            gameMode = Map.MODE_H_V_H;
        }

        int fieldSize = slFieldSize.getValue();
        int winLen = slWinLen.getValue();

        gameWindow.startNewGame(gameMode, fieldSize, fieldSize, winLen);
        setVisible(false);

    }


    private void addGameControlMode() {
        add(new JLabel("Choose gaming mode:"));
        gameMode.add(jrbHunVsAi);
        gameMode.add(jrbHunVsHum);
        add(jrbHunVsAi);
        add(jrbHunVsHum);
    }

    private void GameControlFieldWinLen() {
        add(new JLabel("Choose field size: "));
        JLabel lblFieldSize = new JLabel(STR_FIELD_SIZE + MIN_FIELD_SIZE);
        add(lblFieldSize);

        slFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_WIN_LEN);
        slFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentFieldSize = slFieldSize.getValue();
                lblFieldSize.setText(STR_FIELD_SIZE + currentFieldSize);
                slWinLen.setMaximum(currentFieldSize);
            }
        });
        add(slFieldSize);

        add(new JLabel("Choose winning lenght: "));
        JLabel lblWinLen = new JLabel(STR_WIN_LEN + MIN_WIN_LEN);
        add(lblWinLen);

        slWinLen = new JSlider(MIN_WIN_LEN, MAX_WIN_LEN, MIN_WIN_LEN);
        slWinLen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lblWinLen.setText(STR_WIN_LEN + slWinLen.getValue());
            }
        });

        add(slWinLen);
    }


}
