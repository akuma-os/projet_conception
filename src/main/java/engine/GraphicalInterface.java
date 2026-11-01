package engine;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GraphicalInterface {

    private DrawingPanel panel;

    public GraphicalInterface(GamePainter gamePainter,
                              GameController gameController) {

        JFrame f = new JFrame("Labyrinthe");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panel = new DrawingPanel(gamePainter);
        f.setContentPane(this.panel);

        this.panel.addKeyListener(gameController);

        // Maximiser la fenêtre
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);

        // Donner le focus au panel pour recevoir les touches
        this.panel.setFocusable(true);
        this.panel.requestFocusInWindow();
    }

    public void paint() {
        this.panel.drawGame();
    }
}
