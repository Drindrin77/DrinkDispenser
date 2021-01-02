public class Launch {

	private static Dispenser disp = new Dispenser();
	
	public static void main(String[] args) {
		
		availableDrinks();
		
		disp.insertPiece(Piece.C50);
		disp.insertPiece(Piece.C50);
		disp.insertPiece(Piece.E1);
		disp.insertPiece(Piece.E1);
		disp.insertPiece(Piece.E1);
		disp.insertPiece(Piece.E1);
		System.out.println(disp.giveDrink(1));
	}
	
	public static void availableDrinks() {
		Drink[] drinks = Drink.values();
		System.out.println("Liste des boissons disponible:");
		for(Drink d : drinks) {
			System.out.println(d.ordinal() + ") "+ d + ": " + d.printPrice());
		}
	}
	
	public static void menu() {
		System.out.println("Choisissez une action:");
		System.out.println("1) Insérez une pièce");
		System.out.println("2) Choisir une boisson");
	}
	
	public static void giveChange() {
		System.out.println("Voici le rendu de votre monnaie:");
		System.out.println(disp.giveChange());

	}

}
