package fr.ul.acl.model;

public class ManageRooms {

	private Rooms rooms;
	
	public ManageRooms(Rooms rooms) {
		this.rooms = rooms;
	}
	
	public Labyrinthe changerSalle(int index) {
		if (index >= 0 && index < rooms.getNombreSalles()) {
			return rooms.getSalle(index);
		}
		return null;
	}
}
