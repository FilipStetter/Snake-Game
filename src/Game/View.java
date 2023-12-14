package Game;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private Image ball;
    private Image apple;
    private Image head;
    Model model = new Model();

    public View() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new Board.TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        model.initGame();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        if (model.isInGame()) {

            g.drawImage(apple, model.getApple_x(), model.getApple_y(), this);

            for (int z = 0; z < model.getDots(); z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            model.gameOver(g);
        }
    }
}
