package Game;

import javax.swing.*;
import java.awt.*;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class View extends JPanel {
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private Image ball;
    private Image apple;
    private Image head;
    private Model model;

    public View(Model model) {
        this.model = model;
        initBoard();
        setFocusable(true);
        requestFocusInWindow();
    }

    private void initBoard() {
        addKeyListener(new TAdapter(model));
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
    }

    private void loadImages() {
        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (model.isInGame()) {
            g.drawImage(apple, model.getAppleX(), model.getAppleY(), this);

            for (int z = 0; z < model.getDots(); z++) {
                if (z == 0) {
                    g.drawImage(head, model.getXPositions()[z], model.getYPositions()[z], this);
                } else {
                    g.drawImage(ball, model.getXPositions()[z], model.getYPositions()[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
            repaint();
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over - Points: " + model.getPoints();
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private class TAdapter extends KeyAdapter {
        private Model model;

        public TAdapter(Model model) {
            this.model = model;
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
}
