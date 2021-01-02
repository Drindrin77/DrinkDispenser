

import java.text.NumberFormat;
import java.util.Locale;

public enum Piece {
	C50(50),
	E1(100);
	
	private int price;
	
	private Piece(int price) {
		this.price = price;
	}
	
	public int moneyValue() {
		return this.price;
	}
	
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);
		return nf.format(this.price/100.0);
	}
	
}
