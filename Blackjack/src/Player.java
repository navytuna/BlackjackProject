import java.util.Scanner;

public class Player extends Hand{
	private int Cash;
	
	public Player() {
		Cash = 1500;
	}
	
	public void bet(Hand hand){
		System.out.println("\nHow much do you want to bet?");
		System.out.println("You currently have up to " + sTotalMoney() + " to bet.");
		Scanner sc = new Scanner(System.in);
		int bet = sc.nextInt();
		hand.setBet(bet);
		sc.close();
	}
	
	public void playerTurn(){
		Hand hand = new Hand();
		hand.drawCard();
		hand.drawCard();
		System.out.print("\n" + hand.toString() + " " + hand.toString(1));
		bet(hand);
		
	}
	
	public String sTotalMoney() {
		return "$" + Cash;
	}
	
	public int getCash() {
		return Cash;
	}
}
