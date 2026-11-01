package fr.ul.acl.view;

import fr.ul.acl.model.Decor;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

/** Dessine le décor : sprite à sa taille d'origine, centré en bas de la case. */
public class DecorPainter {
    private static final double TAILLE_REFERENCE = 32.0;

    public static void dessiner(Graphics2D g, List<Decor> decors, int tailleCase) {
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                           RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        double echelle = tailleCase / TAILLE_REFERENCE;
        for (Decor d : decors) {
            BufferedImage img = SpriteLoader.get(d.getType().name().toLowerCase() + ".png");
            if (img == null) continue;
            int w = (int) Math.round(img.getWidth() * echelle);
            int h = (int) Math.round(img.getHeight() * echelle);
            int px = d.getX() * tailleCase + (tailleCase - w) / 2;
            int py = d.getY() * tailleCase + (tailleCase - h);
            g.drawImage(img, px, py, w, h, null);
        }
    }
}
