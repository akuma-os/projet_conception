package fr.ul.acl.view;

import fr.ul.acl.model.Ressource;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;


public class RessourcePainter {
    public static void dessiner(Graphics2D g, List<Ressource> ressources, int tailleCase) {
        for (Ressource r : ressources) {
            BufferedImage img = SpriteLoader.get(r.getType().name().toLowerCase() + ".png");
            if (img != null) {
                g.drawImage(img, r.getX() * tailleCase, r.getY() * tailleCase,
                            tailleCase, tailleCase, null);
            }
        }
    }
}