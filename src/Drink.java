import java.text.NumberFormat;
import java.util.Locale;

public enum Drink {

    Tea(100),
    Coffee(150);

    private int price;

    Drink(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String printPrice() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        return nf.format(this.price/100.0);
    }

}
