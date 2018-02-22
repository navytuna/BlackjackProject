import java.util.Scanner;
import java.lang.StringBuilder;

public class Player extends Hand{
	private int Cash = 1500;
	private Scanner sc = new Scanner(System.in);
	private boolean shouldBreak = false;
	public Player() {}
	
	public void playerTurn(Hand Dealer, Hand hand) {
		hand.drawCard();
		Dealer.drawCard();
		hand.drawCard();
		Dealer.drawCard();
		System.out.print("\nDealer's shown card:");
		System.out.print("\n" + Dealer.toString());
		System.out.print("\n\nYour hand");
		showHand(hand);
		System.out.print("\nYou have $" + getCash() + " to bet");
		hand.bet();
		menu(hand);
	}
	
	public int getCash() {return Cash;}
	
	private void menu(Hand hand) {
		while(true) {
			System.out.println("Input your command");
			String in = sc.nextLine();
			if("Show Hand".equals(in) || "show hand".equals(in) || "show Hand".equals(in) || "Show hand".equals(in)) {showHand(hand, hand.findNextEmpty());}
			else if("hit".equals(in) || "Hit".equals(in)) {hit(hand);}
			else if(("Double Down".equals(in) || "double down".equals(in) || "double Down".equals(in) || "Double down".equals(in)) && hand.getBet() * 2 <= Cash) {
				hand.drawCard();
				hand.setBet(hand.getBet() * 2);
				shouldBreak = true;
			}else if("Stand".equals(in) || "stand".equals(in)) {shouldBreak = true;}
			else System.out.println("The Input either couldn't be parsed or the command could not be completed");
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
		for(int cnt = 0; cnt < length; cnt++) {out.append(hand.toString(cnt));}
		System.out.println(out);
		System.out.println(" " + hand.getRunningTotal() + " is the hand's current total");
	}
	
	private void endOfRound(Hand hand) {
		if(21 < hand.getRunningTotal() && !(checkAce(hand))) {
			System.out.println("Player Busts!");
			shouldBreak = true;
		}else if(21 < hand.getRunningTotal() && checkAce(hand)) {
			hand.hand[findAce(hand)].setNumber(1);
			endOfRound(hand); //Checking to see if the player Blackjacks with an ace at 1. Rare but a possibility
		}
		if(21 == hand.getRunningTotal()) {
			System.out.println("Player Blackjacks!");
			hand.setBet(hand.getBet() + (int)(hand.getBet() * .5)); //When you blackjack you get 1.5 times your bet
			shouldBreak = true;
		}
	}
	
	public void moneyLose(Hand hand) {Cash -= hand.getBet();}
	
	public void moneyGain(Hand hand) {Cash += hand.getBet();}

	private void hit(Hand hand) {
		hand.drawCard();
		System.out.println("\nThe " + hand.toString(hand.findNextEmpty() - 1) + " was drawn bringing the hand to a total of " + hand.getRunningTotal());
	}
	
	public boolean checkAce(Hand hand) {
		for(int cnt = 0; cnt < hand.findNextEmpty(); cnt++) {
			if(hand.hand[cnt].getNumber() == 11) {return true;}
		}
		return false;
	}
	
	public int findAce(Hand hand) {
		for(int cnt = 0; cnt < hand.findNextEmpty(); cnt++) {
			if(hand.hand[cnt].getNumber() == 11) {return cnt;}
		}
		return 0; //Shouldn't happen ever
	}
}