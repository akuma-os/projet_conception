
package fr.ul.acl.model;

public class Porte {

    private final int x;
    private final int y;

    private boolean accessible;
    private boolean ouverte;

    public Porte(int x, int y) {

        if (x < 0 || y < 0) {
            throw new IllegalArgumentException(
                    "La position de la porte est invalide."
            );
        }

        this.x = x;
        this.y = y;

        // Au début, la porte est cachée et fermée
        this.accessible = false;
        this.ouverte = false;
    }

    // Rendre la porte accessible après la mission
    public void rendreAccessible() {
        accessible = true;
    }

    // Ouvrir la porte si elle est accessible et si le héros possède la clé
    public boolean ouvrir(boolean possedeCle) {

        if (!accessible || !possedeCle) {
            return false;
        }

        ouverte = true;
        return true;
    }

    // Vérifier si la porte est accessible
    public boolean estAccessible() {
        return accessible;
    }

    // Vérifier si la porte est ouverte
    public boolean estOuverte() {
        return ouverte;
    }

    // Position de la porte
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
