package fr.ul.acl.model;

public class MonstreDifficile extends Monstre {

    public MonstreDifficile(int x, int y, Zone zoneDeplacement) {
        super(x, y, 6, "difficile", zoneDeplacement,
              new Zone(x - 4, y - 4, 9, 9));
    }
}