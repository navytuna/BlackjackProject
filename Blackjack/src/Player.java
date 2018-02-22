import java.util.Scanner;
import java.lang.StringBuilder;

public class Player extends Hand{
	private int Cash;
	private Scanner sc = new Scanner(System.in);
	private boolean shouldBreak;
	
	public Player() {
		Cash = 1500;
		shouldBreak = false;
	}
	
	public void playerTurn(Hand Dealer, Hand hand) {
		hand.drawCard();
		Dealer.drawCard();
		hand.drawCard();
		Dealer.drawCard();
		System.out.print("\nDealer's shown card:");
		System.out.print("\n" + Dealer.toString());
		System.out.print("\n\nYour hand");
		showHand(hand);
		System.out.print("\n" + sTotalMoney());
		hand.bet();
		menu(hand);
	}
	
	public String sTotalMoney() {
		return "$" + Cash;
	}
	
	public int getCash() {
		return Cash;
	}
	
	private void menu(Hand hand) {
		while(true) {
			System.out.println("Input your command");
			String in = sc.nextLine();
			if("Show Hand".equals(in) || "show hand".equals(in) || "show Hand".equals(in) || "Show hand".equals(in)) {
				showHand(hand, hand.findNextEmpty());
			}else if("hit".equals(in) || "Hit".equals(in)) {
				hit(hand);
			}else if("Double Down".equals(in) || "double down".equals(in) || "double Down".equals(in) || "Double down".equals(in)) {
				hand.drawCard();
				hand.setBet(hand.getBet() * 2);
				shouldBreak = true;
			}else if("Stand".equals(in) || "stand".equals(in)) {
				shouldBreak = true;
			}else {
				System.out.println("Error: That input could not be parsed by the Scanner");
			}
			endOfRound(hand);
			if(shouldBreak) {break;}
		}
	}
	
	public void showHand(Hand hand) {
		hand.setRunningTotal();
		System.out.print("\n" + hand.toString() + "\n" + hand.toString(1));
		System.out.print("\n" + hand.getRunningTotal() + " is the hand's current total");
	}
	
	public void showHand(Hand hand, int length) {
		hand.setRunningTotal();
		StringBuilder out = new StringBuilder("");
		for(int cnt = 0; cnt < length; cnt++) {
			out.append(hand.toString(cnt));
		}
		System.out.println(out);
		System.out.println(" " + hand.getRunningTotal() + " is the hand's current total");
	}
	
	private void endOfRound(Hand hand) {
		if(21 < hand.getRunningTotal() && !(checkAce(hand))) {
			System.out.println("Player Busts!");
			shouldBreak = true;
		}else if(21 < hand.getRunningTotal() && checkAce(hand)) {
			hand.hand[findAce(hand)].setNumber(1);
		}
		if(21 == hand.getRunningTotal()) {
			System.out.println("Player Blackjacks!");
			hand.setBet(hand.getBet() + (int)(hand.getBet() * .5));
			shouldBreak = true;
		}
	}
	
	public void moneyLose(Hand hand) {
		Cash -= hand.getBet();
	}
	
	public void moneyGain(Hand hand) {
		Cash += hand.getBet();
	}

	private void hit(Hand hand) {
		hand.drawCard();
		System.out.println("\nThis is the card that was drawn: " + hand.toString(hand.findNextEmpty() - 1));
	}
	
	public boolean checkAce(Hand hand) {
		for(int cnt = 0; cnt < hand.findNextEmpty(); cnt++) {
			if(hand.hand[cnt].getNumber() == 11) {
				return true;
			}
		}
		return false;
	}
	
	public int findAce(Hand hand) {
		for(int cnt = 0; cnt < hand.findNextEmpty(); cnt++) {
			if(hand.hand[cnt].getNumber() == 11) {
				return cnt;
			}
		}
		return 0;
	}
}