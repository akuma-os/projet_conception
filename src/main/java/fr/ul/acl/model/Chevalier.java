package fr.ul.acl.model;

public class Chevalier extends Monstre {

    public Chevalier(int x, int y, Zone zoneDeplacement) {
        super(x, y, 10, "boss", zoneDeplacement,
              new Zone(x - 5, y - 5, 11, 11));
    }
}