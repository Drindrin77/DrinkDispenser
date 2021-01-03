import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPiece {

    @Test
    public void testPieceValue(){
        assertEquals(100, Piece.E1.moneyValue());
        assertEquals(50, Piece.C50.moneyValue());
    }
}
