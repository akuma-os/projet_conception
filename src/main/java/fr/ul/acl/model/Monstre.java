package fr.ul.acl.model;

import java.util.Random;

public abstract class Monstre {

    private int positionX;
    private int positionY;
    private int pointsDeVie;
    private String difficulte;
    private Zone zoneDeplacement;
    private Zone zoneDetection;

    private final Random random = new Random();

    
    public Monstre(int x, int y, int pointsDeVie,
                   String difficulte, Zone zoneDeplacement,
                   Zone zoneDetection) {
        this.positionX = x;
        this.positionY = y;
        this.pointsDeVie = pointsDeVie;
        this.difficulte = difficulte;
        this.zoneDeplacement = zoneDeplacement;
        this.zoneDetection = zoneDetection;
    }

    
    public int getX() {
        return positionX;
    }

    public int getY() {
        return positionY;
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    public String getDifficulte() {
        return difficulte;
    }

    
    private boolean deplacer(int dx, int dy,
                         Labyrinthe labyrinthe, Heros heros) {

        int nouvelX = positionX + dx;
        int nouvelY = positionY + dy;

        if (zoneDeplacement.contient(nouvelX, nouvelY)
                && labyrinthe.deplacementPossible(nouvelX, nouvelY)
                && !labyrinthe.estOccupeeParMonstre(nouvelX, nouvelY)
                && !(nouvelX == heros.getX() && nouvelY == heros.getY())) {

            positionX = nouvelX;
            positionY = nouvelY;

            zoneDetection.deplacer(dx, dy);

            return true;
        }

        return false;
    }

    public void seDeplacerAleatoirement(Labyrinthe labyrinthe, Heros heros) {
        int direction = random.nextInt(5);

        switch (direction) {
            case 1:
                deplacer(0, -1, labyrinthe, heros);
                break;
            case 2:
                deplacer(0, 1, labyrinthe, heros);
                break;
            case 3:
                deplacer(-1, 0, labyrinthe, heros);
                break;
            case 4:
                deplacer(1, 0, labyrinthe, heros);
                break;
            default:
                break;
        }
    }

    public void recevoirDegats(int degats) {
        pointsDeVie = Math.max(0, pointsDeVie - degats);
    }

    
    public boolean estElimine() {
        return pointsDeVie == 0;
    }

    public boolean detecterHeros(Heros heros) {
        return zoneDetection.contient(heros.getX(), heros.getY());
    }

    public void poursuivreHeros(Heros heros, Labyrinthe labyrinthe) {
        int dx = Integer.compare(heros.getX(), positionX);
        int dy = Integer.compare(heros.getY(), positionY);

        if (dx != 0 && deplacer(dx, 0, labyrinthe, heros)) {
            return;
        }

        if (dy != 0) {
            deplacer(0, dy, labyrinthe, heros);
        }
    }

    public void agir(Heros heros, Labyrinthe labyrinthe) {
        if (!estElimine()) {
            if (detecterHeros(heros)) {
                poursuivreHeros(heros, labyrinthe);
            } else {
                seDeplacerAleatoirement(labyrinthe, heros);
            }
        }
    }
}