package Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter implements ActionListener {
    private Model model;
    private View view;

    public Controller() {
        model = new Model();
        view = new View(model);

        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        startGame();

    }





    public void startGame() {
        model.initGame();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.isInGame()) {
            model.checkApple();
            model.checkCollision();
            model.move();
            view.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT)) {
            model.setDirection("LEFT");
        }

        if ((key == KeyEvent.VK_RIGHT)) {
            model.setDirection("RIGHT");
        }

        if ((key == KeyEvent.VK_UP)) {
            model.setDirection("UP");
        }

        if ((key == KeyEvent.VK_DOWN)) {
            model.setDirection("DOWN");
        }
    }
}
