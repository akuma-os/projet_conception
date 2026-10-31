package fr.ul.acl.model;


public class Ressource {
    private final TypeRessource type;
    private final int x, y; // en cases de grille

    public Ressource(TypeRessource type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public TypeRessource getType() { return type; }
    public int getX() { return x; }
    public int getY() { return y; }
}