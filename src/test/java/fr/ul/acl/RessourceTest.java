package fr.ul.acl;

import static org.junit.Assert.assertEquals;

import fr.ul.acl.model.Ressource;
import fr.ul.acl.model.TypeRessource;
import org.junit.Test;

public class RessourceTest {

    @Test
    public void ressourceGardeTypeEtPosition() {
        Ressource r = new Ressource(TypeRessource.POTION, 3, 4);
        assertEquals(TypeRessource.POTION, r.getType());
        assertEquals(3, r.getX());
        assertEquals(4, r.getY());
    }
}
