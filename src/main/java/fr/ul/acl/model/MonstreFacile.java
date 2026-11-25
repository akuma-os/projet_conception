package fr.ul.acl.model;

public class MonstreFacile extends Monstre {

    public MonstreFacile(int x, int y, Zone zoneDeplacement) {
        super(x, y, 2, "facile", zoneDeplacement,
              new Zone(x - 2, y - 2, 5, 5));
    }
}