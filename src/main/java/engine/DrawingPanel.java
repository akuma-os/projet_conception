package engine;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.BiConsumer;

public class DrawingPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private GamePainter painter;

    private BufferedImage nextImage;
    private BufferedImage currentImage;

    private int width;
    private int height;

    public DrawingPanel(GamePainter painter) {
        super();

        this.painter = painter;

        this.width = painter.getWidth();
        this.height = painter.getHeight();

        setPreferredSize(new Dimension(width, height));
        setFocusable(true);

        nextImage = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_RGB);

        currentImage = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void drawGame() {

        int newWidth = getWidth();
        int newHeight = getHeight();

        if (newWidth <= 0 || newHeight <= 0) {
            return;
        }

        // Adapter les images à la taille réelle du panneau
        if (nextImage.getWidth() != newWidth
                || nextImage.getHeight() != newHeight) {

            width = newWidth;
            height = newHeight;

            nextImage = new BufferedImage(
                    width, height, BufferedImage.TYPE_INT_RGB);

            currentImage = new BufferedImage(
                    width, height, BufferedImage.TYPE_INT_RGB);
        }

        painter.draw(nextImage);

        BufferedImage temp = currentImage;
        currentImage = nextImage;
        nextImage = temp;

        Graphics2D g = nextImage.createGraphics();
        g.setColor(getBackground());
        g.fillRect(0, 0, width, height);
        g.dispose();

        repaint();
    }
    
    
    public void setOnClick(BiConsumer<Integer, Integer> action) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	System.out.println("1. clic panneau " + e.getX() + "," + e.getY());
            	requestFocusInWindow();          // garde le clavier actif
                action.accept(e.getX(), e.getY());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (currentImage != null) {
            g.drawImage(currentImage, 0, 0, null);
        }
    }
}
