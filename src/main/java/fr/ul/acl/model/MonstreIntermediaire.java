package fr.ul.acl.model;

public class MonstreIntermediaire extends Monstre {

    public MonstreIntermediaire(int x, int y, Zone zoneDeplacement) {
        super(x, y, 4, "intermediaire", zoneDeplacement,
              new Zone(x - 3, y - 3, 7, 7));
    }
}