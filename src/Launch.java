import java.util.ArrayList;
import java.util.Scanner;

public class Launch {

	private static Dispenser disp = new Dispenser();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int again = 1;
		do {
			availableDrinks();
			int idDrink = selectDrink();
			menuInsertPiece(Drink.values()[idDrink]);
			giveDrink(idDrink);
			giveChange();
			again = restart();
			
		} while(again == 1);
		scanner.close();
		System.out.println("Au revoir !");
	}

	public static void availableDrinks() {
		Drink[] drinks = Drink.values();
		System.out.println("Liste des boissons disponible:");
		for (Drink d : drinks) {
			System.out.println(d.ordinal() + ") " + d + ": " + d.printPrice());
		}
		System.out.println();
	}
	
	public static int restart() {
		System.out.println();
		System.out.println("Voulez-vous choisir une autre boisson?");
		int again = 1;
		do {
			System.out.println("1) Oui");
			System.out.println("2) Non");
			again = Integer.parseInt(scanner.nextLine());
		}while(again!=1 && again!=2);
		return again;
	}

	public static void menuInsertPiece(Drink chosenDrink) {
		Piece[] pieces = Piece.values();
		int answer = -1;
		System.out.println();
		System.out.println("Vous pouvez insérer l'une des pièces suivante: ");
		for(Piece p : pieces) {
			System.out.println(p.ordinal() + ") "+ p);
		}
		System.out.println();
		do {
			System.out.println("Vous n'avez pas insérer assez d'argent");
			do {
				System.out.println("Veuillez sélectionner une pièce existante");
				answer = Integer.parseInt(scanner.nextLine());
			} while(answer < 0 || answer >= pieces.length);
			disp.insertPiece(pieces[answer]);
		} while(chosenDrink.getPrice() > disp.sumPieces());
	}

	public static int selectDrink() {
		int max = Drink.values().length;
		int answer = -1;
		do {
			System.out.println("Veuillez sélectionner une boisson existante");
			answer = Integer.parseInt(scanner.nextLine());
		} while (answer < 0 || answer >= max);
		return answer;
	}

	public static void giveChange() {
		ArrayList<Piece> change = disp.giveChange();
		if (!change.isEmpty()) {
			System.out.println("Voici le rendu de votre monnaie:");
			System.out.println(change);
		}
	}

	public static void giveDrink(int idDrink) {
		Drink d = disp.giveDrink(idDrink);
		System.out.println("Voici votre boisson: " + d);
	}

}
