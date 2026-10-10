package fr.ul.acl.start;

import engine.GameEngineGraphical;
import fr.ul.acl.controller.LabyrintheController;
import fr.ul.acl.model.LabyrintheGame;
import fr.ul.acl.view.LabyrinthePainter;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        LabyrintheGame game = new LabyrintheGame();
        LabyrinthePainter painter = new LabyrinthePainter(game);
        LabyrintheController controller = new LabyrintheController();

        GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
        engine.run();
    }
}