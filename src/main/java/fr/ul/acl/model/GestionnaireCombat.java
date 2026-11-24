package fr.ul.acl.model;

public class GestionnaireCombat {

    public int calculerDegats(Heros heros, Monstre monstre) {
        return 1;
    }

    public boolean estAPortee(Heros heros, Monstre monstre) {
        int distance = Math.abs(heros.getX() - monstre.getX())
                     + Math.abs(heros.getY() - monstre.getY());

        return distance == 1;
    }

    public void attaquer(Heros heros, Monstre monstre) {
        if (!monstre.estElimine() && estAPortee(heros, monstre)) {
            monstre.recevoirDegats(calculerDegats(heros, monstre));
        }
    }

    public boolean verifierElimination(Monstre monstre) {
        return monstre.estElimine();
    }
}