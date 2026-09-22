package main.java.fr.ul.acl.model;
public class Pacman {
	private int x;
	private int y;
	public Pacman(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public void up() {
		this.y++;
	}
	public void down() {
		this.y--;
	}
	public void right() {
		this.x++;
	}
	public void left() {
		this.x--;
	}
	
}