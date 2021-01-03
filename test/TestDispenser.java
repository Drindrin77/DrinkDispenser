import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDispenser {

    Dispenser dispenser;

    @BeforeEach
    public void before() {
        dispenser = new Dispenser();
    }

    @Test
    public void testDispenserCreation() {
        assertNotNull(dispenser);
    }

    @Test
    public void testInsertNullPiece() {
        dispenser.insertPiece(null);
        dispenser.giveDrink(0);
        dispenser.giveChange();
    }

    @Test
    public void testWrongDrinkId() {
        dispenser.giveDrink(-1);
    }

    @Test
    public void testAskDrinkWithoutMoney() {
        assertNull(dispenser.giveDrink(0));
        assertNull(dispenser.giveDrink(1));
        assertTrue(dispenser.giveChange().isEmpty());
    }

    @Test
    public void testAskDrinkWithoutEnoughMoney() {
        dispenser.insertPiece(Piece.C50);

        assertNull(dispenser.giveDrink(0));
        assertTrue(dispenser.giveChange().isEmpty());

    }

    @Test
    public void testAskDrinkWithPerfectSum() {
        //acheter un thé avec 1€
        dispenser.insertPiece(Piece.E1);
        assertEquals(Drink.Tea, dispenser.giveDrink(0));
        assertTrue(dispenser.giveChange().isEmpty());

        //acheter un cafe avec 1.50€
        dispenser.insertPiece(Piece.E1);
        dispenser.insertPiece(Piece.C50);
        assertEquals(Drink.Coffee, dispenser.giveDrink(1));
        assertTrue(dispenser.giveChange().isEmpty());

        //ne pas pouvoir acheter de boisson supplémentaire sans réinssérer de piece
        assertNull(dispenser.giveDrink(0));
    }

    @Test
    public void testAskDrinkWithTooMuchMoney() {
        //acheter un tea avec 2€
        dispenser.insertPiece(Piece.E1);
        dispenser.insertPiece(Piece.E1);
        assertEquals(Drink.Tea, dispenser.giveDrink(0));
        assertArrayEquals(new Piece[]{Piece.E1}, dispenser.giveChange().toArray());

        //acheter un cafe avec 2€
        dispenser.insertPiece(Piece.E1);
        dispenser.insertPiece(Piece.E1);
        assertEquals(Drink.Coffee, dispenser.giveDrink(1));
        assertArrayEquals(new Piece[]{Piece.C50}, dispenser.giveChange().toArray());

        //acheter un cafe avec 3€
        dispenser.insertPiece(Piece.E1);
        dispenser.insertPiece(Piece.E1);
        dispenser.insertPiece(Piece.E1);
        assertEquals(Drink.Coffee, dispenser.giveDrink(1));
        assertArrayEquals(new Piece[]{Piece.E1 , Piece.C50}, dispenser.giveChange().toArray());
    }
}
