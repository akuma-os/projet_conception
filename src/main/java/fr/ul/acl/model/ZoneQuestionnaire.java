
package fr.ul.acl.model;

public class ZoneQuestionnaire {

    private final int x;
    private final int y;
    private boolean accessible;

    public ZoneQuestionnaire(int x, int y) {

        if (x < 0 || y < 0) {
            throw new IllegalArgumentException(
                    "La position de la zone est invalide."
            );
        }

        this.x = x;
        this.y = y;
        this.accessible = false;
    }

    // Rendre la zone accessible après la mission
    public void rendreAccessible() {
        accessible = true;
    }

    // Vérifier si le héros se trouve dans la zone
    public boolean contientHeros(int heroX, int heroY) {
        return accessible && heroX == x && heroY == y;
    }

    public boolean estAccessible() {
        return accessible;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
