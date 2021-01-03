import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestDrink {

    @Test
    public void testPriceValue(){
        assertEquals(150, Drink.Coffee.getPrice());
        assertEquals(100, Drink.Tea.getPrice());
    }
}
