package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Model extends JPanel implements ActionListener{
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private int points = 0;

    public Model() {
        initGame();
    }

    public void initGame() {
        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();

    }
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inGame) {
                    checkApple();
                    checkCollision();
                    move();
                }
                repaint();
            }

    public void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[z - 1];
            y[z] = y[z - 1];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }
        if (rightDirection) {
            x[0] += DOT_SIZE;
        }
        if (upDirection) {
            y[0] -= DOT_SIZE;
        }
        if (downDirection) {
            y[0] += DOT_SIZE;
        }
        repaint();
    }

    void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            points++;
            locateApple();
        }
    }

    void checkCollision() {
        for (int z = dots; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT || y[0] < 0 || x[0] >= B_WIDTH || x[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {
        int r = (int) (Math.random() * RAND_POS);
        apple_x = r * DOT_SIZE;
        r = (int) (Math.random() * RAND_POS);
        apple_y = r * DOT_SIZE;
    }

    // Tillgångsmetoder (getters) och sättarmetoder (setters)
    public boolean isInGame() {
        return inGame;
    }

    public void setDirection(String direction) {
        switch (direction) {
            case "LEFT":
                if (!rightDirection) {
                    leftDirection = true;
                    upDirection = false;
                    downDirection = false;
                }
                break;
            case "RIGHT":
                if (!leftDirection) {
                    rightDirection = true;
                    upDirection = false;
                    downDirection = false;
                }
                break;
            case "UP":
                if (!downDirection) {
                    upDirection = true;
                    rightDirection = false;
                    leftDirection = false;
                }
                break;
            case "DOWN":
                if (!upDirection) {
                    downDirection = true;
                    rightDirection = false;
                    leftDirection = false;
                }
                break;
        }
    }

    public int getDots() {
        return dots;
    }

    public int getAppleX() {
        return apple_x;
    }

    public int getAppleY() {
        return apple_y;
    }

    public int[] getXPositions() {
        return x;
    }

    public int[] getYPositions() {
        return y;
    }

    public int getPoints() {
        return points;
    }
}
