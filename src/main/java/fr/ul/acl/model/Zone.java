package fr.ul.acl.model;

import java.awt.Rectangle;

public class Zone {

    private final Rectangle limites;

    public Zone(int x, int y, int largeur, int hauteur) {
        this.limites = new Rectangle(x, y, largeur, hauteur);
    }

    public boolean contient(int x, int y) {
        return limites.contains(x, y);
    }

    public void deplacer(int dx, int dy) {
        limites.translate(dx, dy);
    }
}

