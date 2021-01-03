import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Dispenser {

	private HashMap<Piece, Integer> insertedPieces;
	private int change;

	public Dispenser() {
		insertedPieces = new HashMap<Piece, Integer>();
	}

	public void insertPiece(Piece piece) {
		if (insertedPieces.containsKey(piece)) {
			insertedPieces.put(piece, insertedPieces.get(piece) + 1);
		} else {
			insertedPieces.put(piece, 1);
		}
	}

	public Drink giveDrink(int id) {
		Drink chosenDrink = getDrinkByID(id);
		if (chosenDrink != null) {
			change = sumPieces() - chosenDrink.getPrice();
			return chosenDrink;
		} else {
			return null;
		}
	}

	private Drink getDrinkByID(int id) {
		Drink drink = Drink.values()[id];
		if (drink.getPrice() <= sumPieces()) {
			return drink;
		}
		return null;
	}

	public ArrayList<Piece> giveChange() {
		ArrayList<Piece> changePieces = new ArrayList<Piece>();
		while (change != 0) {
			Piece piece = mostExpensivePieceByChange(change);
			if (piece != null) {
				changePieces.add(piece);
				change -= piece.moneyValue();
			}
		}

		insertedPieces.clear();
		return changePieces;
	}

	private Piece mostExpensivePieceByChange(int change) {

		List<Piece> sortedMoneyPiece = Arrays.asList(Piece.values());

		Collections.sort(sortedMoneyPiece, new Comparator<Piece>() {
			@Override
			public int compare(Piece p1, Piece p2) {
				if (p1.moneyValue() >= p2.moneyValue()) {
					return -1;
				} else {
					return 1;
				}
			}
		});

		for (int i = 0; i < sortedMoneyPiece.size(); i++) {

			if (sortedMoneyPiece.get(i).moneyValue() <= change) {
				return sortedMoneyPiece.get(i);
			}
		}
		return null;
	}

	private int sumPieces() {
		int sum = 0;
		for (Entry<Piece, Integer> entry : insertedPieces.entrySet()) {
			sum += entry.getValue() * entry.getKey().moneyValue();
		}
		return sum;
	}
}
