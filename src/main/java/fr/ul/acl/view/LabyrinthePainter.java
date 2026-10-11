package fr.ul.acl.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import engine.GamePainter;
import fr.ul.acl.model.Heros;
import fr.ul.acl.model.Labyrinthe;
import fr.ul.acl.model.LabyrintheGame;

/**
 * Dessine le labyrinthe (fond noir, murs gris) et le heros (carre blanc).
 * Le painter ne modifie jamais le jeu : il lit seulement son etat.
 */
public class LabyrinthePainter implements GamePainter {

	/** taille d'une case en pixels */
	public static final int TAILLE_CASE = 30;

	private static final Color COULEUR_FOND = Color.BLACK;
	private static final Color COULEUR_MUR = Color.GRAY;
	private static final Color COULEUR_HEROS = Color.WHITE;

	/** le jeu a afficher */
	private final LabyrintheGame game;

	/**
	 * @param game le jeu dont on lit l'etat pour dessiner
	 */
	public LabyrinthePainter(LabyrintheGame game) {
		this.game = game;
	}

	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		Labyrinthe labyrinthe = game.getLabyrinthe();
		Heros heros = game.getHeros();

		// 1. fond noir sur toute l'image
		crayon.setColor(COULEUR_FOND);
		crayon.fillRect(0, 0, getWidth(), getHeight());

		// 2. murs gris (une case = un carre)
		crayon.setColor(COULEUR_MUR);
		for (int y = 0; y < labyrinthe.getHauteur(); y++) {
			for (int x = 0; x < labyrinthe.getLargeur(); x++) {
				if (labyrinthe.estMur(x, y)) {
					crayon.fillRect(x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
				}
			}
		}

		// 3. heros : carre blanc, legerement plus petit que la case
		int marge = 4;
		crayon.setColor(COULEUR_HEROS);
		crayon.fillRect(heros.getX() * TAILLE_CASE + marge,
				heros.getY() * TAILLE_CASE + marge,
				TAILLE_CASE - 2 * marge,
				TAILLE_CASE - 2 * marge);

		crayon.dispose();
	}

	@Override
	public int getWidth() {
		return game.getLabyrinthe().getLargeur() * TAILLE_CASE;
	}

	@Override
	public int getHeight() {
		return game.getLabyrinthe().getHauteur() * TAILLE_CASE;
	}
}