//Relie les commandes du moteur à la logique du jeu et gère son état.
package fr.ul.acl.model;


import engine.Cmd;
import engine.Game;

public class LabyrintheGame implements Game {

    private Heros heros;
    private Labyrinthe labyrinthe;
    private Rooms rooms;
    private ManageRooms manageRooms;
    
    public LabyrintheGame() {
    	rooms = new Rooms();
    	manageRooms = new ManageRooms(rooms);
        labyrinthe = rooms.getSalle(0);
        heros = new Heros(1, 1); // doit être une case sans mur
        
    
    }
    
    public void ChangeRoom(int index) {
    	if (index >= 0 && index < rooms.getNombreSalles()) {
    		labyrinthe = rooms.getSalle(index);
    		
    		heros= new Heros(1,1);
    	}
    }
    
    private void verifyDoor() {
    	int x = heros.getX();
    	int y = heros.getY();
    	
    	int typeCase = labyrinthe.getTypeCase(x, y);
    	switch(typeCase) {
    	case Labyrinthe.PORTE_1 :
    		labyrinthe = manageRooms.changerSalle(1);
    		heros = new Heros(6,1);
    		break;
    		
    	case Labyrinthe.PORTE_2 :
    		labyrinthe = manageRooms.changerSalle(0);
    		heros = new Heros(6,5);
    		break;
    		
    	case Labyrinthe.PORTE_3 :
    		labyrinthe = manageRooms.changerSalle(2);
    		heros = new Heros(1,3);
    		break;
    	case Labyrinthe.PORTE_4 :
    		labyrinthe = manageRooms.changerSalle(1);
    		heros = new Heros(10,3);
    		break;
    	case Labyrinthe.PORTE_5 :
    		labyrinthe = manageRooms.changerSalle(3);
    		heros = new Heros(6,5);
    		break;
    	case Labyrinthe.PORTE_6 :
    		labyrinthe = manageRooms.changerSalle(2);
    		heros = new Heros(6,1);
    		break;
    	case Labyrinthe.PORTE_7 :
    		labyrinthe = manageRooms.changerSalle(0);
    		heros = new Heros(10,3);
    		break;
    	case Labyrinthe.PORTE_8 :
    		labyrinthe = manageRooms.changerSalle(3);
    		heros = new Heros(1,3);
    		break;
    		
    	}
    	
    }
    

    @Override
    public void evolve(Cmd userCmd) {
        switch (userCmd) {
            case UP:    heros.deplacer(0, -1, labyrinthe); break;
            case DOWN:  heros.deplacer(0, 1, labyrinthe);  break;
            case LEFT:  heros.deplacer(-1, 0, labyrinthe); break;
            case RIGHT: heros.deplacer(1, 0, labyrinthe);  break;
            case IDLE:
            default:    break;
        }
        verifyDoor();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public Heros getHeros() { 
    	return heros; 
    	}
    public Labyrinthe getLabyrinthe() { 
    	return labyrinthe; 
    	}
}