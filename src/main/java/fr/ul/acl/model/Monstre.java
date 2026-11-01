package fr.ul.acl.model;

import java.util.Random;

public class Monstre {
    private int x;
    private int y;
    
    // Limites de la chambre dans laquelle le monstre est confiné
    private final int xMin, xMax, yMin, yMax; 
    private final Random random;

    public Monstre(int x, int y, int xMin, int xMax, int yMin, int yMax) {
        this.x = x;
        this.y = y;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.random = new Random();
    }

    public int getX() { 
        return x; 
    }

    public int getY() { 
        return y; 
    }

    /**
     * Gère le déplacement aléatoire du monstre d'une case.
     * Utilise la méthode deplacementPossible de votre classe Labyrinthe.
     */
    public void deplacerAleatoirement(Labyrinthe labyrinthe) {
        // 0: Immobile, 1: Haut, 2: Bas, 3: Gauche, 4: Droite
        int direction = random.nextInt(5); 
        int nextX = this.x;
        int nextY = this.y;

        switch (direction) {
            case 1: nextY--; break; // Haut
            case 2: nextY++; break; // Bas
            case 3: nextX--; break; // Gauche
            case 4: nextX++; break; // Droite
            default: break;         // Reste sur place
        }

        // Critère d'acceptation 1 : Le monstre reste dans les limites de sa chambre
        if (nextX >= xMin && nextX <= xMax && nextY >= yMin && nextY <= yMax) {
            
            // Critère d'acceptation 2 : Respecte la grille et les murs (votre méthode de déplacement)
            if (labyrinthe.deplacementPossible(nextX, nextY)) { 
                this.x = nextX;
                this.y = nextY;
            }
        }
    }
}