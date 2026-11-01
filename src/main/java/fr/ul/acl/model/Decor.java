package fr.ul.acl.model;

/** Élément de décor placé dans une chambre (position en cases de grille). */
public class Decor {
    private final TypeDecor type;
    private final int x, y;

    public Decor(TypeDecor type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public TypeDecor getType() { return type; }
    public int getX() { return x; }
    public int getY() { return y; }
}
