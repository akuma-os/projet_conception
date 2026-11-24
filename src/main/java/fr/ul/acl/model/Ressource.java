package fr.ul.acl.model;


public class Ressource {
    private final TypeRessource type;
    private final int x, y; // en cases de grille

    public Ressource(TypeRessource type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    
    
    public TypeRessource getType() { 
    	return type; 
    	}
    public int getX() { 
    	return x; 
    	}
    public int getY() { 
    	return y; 
    	}
}

/*
Ressource [0] [0] = CLE
Ressource [0] [1] = CRISTAL_BLEU
Ressource [0] [2] = CRISTAL_JAUNE
Ressource [1] [0] = POTION
Ressource [1] [1] = CRISTAL_VIOLET
Ressource [2] [0] = ARME
Ressource [2] [1] = CRISTAL_ROUGE
Ressource [3] [0] = CLE
Ressource [3] [1] = POTION
Ressource [3] [2] = ARME
Ressource [3] [3] = CRISTAL_VIOLET
Ressource [3] [4] = CRISTAL_BLEU
Ressource [3] [5] = CRISTAL_JAUNE
Ressource [3] [6] = CRISTAL_ROUGE
*/