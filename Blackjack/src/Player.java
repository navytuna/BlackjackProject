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
		System.out.println("\n\nYour hand:");
		showHand(hand, hand.findNextEmpty());
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
			else if(("Double Down".equals(in) || "double down".equals(in) || "double Down".equals(in) || "Double down".equals(in)) && hand.getBet() * 2 <= Cash) {hand.drawCard(); hand.setBet(hand.getBet() * 2); shouldBreak = true;}
			else if("Stand".equals(in) || "stand".equals(in)) {shouldBreak = true;}
			else if("Exit".equals(in) || "exit".equals(in)) {System.out.println("\n\nExit Succesful"); System.exit(0);}
			else {System.out.println("The Input either couldn't be parsed or the command could not be completed\n");}
			endOfRound(hand);
			if(shouldBreak) {break;}
		}
	}
	
	public void showHand(Hand hand, int length) {
		hand.setRunningTotal();
		StringBuilder out = new StringBuilder("");
		for(int cnt = 0; cnt < length; cnt++) {out.append(hand.toString(cnt)); if((cnt + 1) != length) {out.append(", ");};}
		System.out.println(out);
		System.out.println("" + hand.getRunningTotal() + " is the hand's current total");
	}
	
	private void endOfRound(Hand hand) {
		if(21 < hand.getRunningTotal() && !(checkAce(hand))) {System.out.println("Player Busts!"); shouldBreak = true;}
		//^Checks for bust
		else if(21 < hand.getRunningTotal() && checkAce(hand)) {hand.hand[findAce(hand)].setNumber(1); endOfRound(hand);}
		//^Checks to see if the hand blackjacks with the ace valued at one. Rare, but a possibility
		if(21 == hand.getRunningTotal()) {System.out.println("Player Blackjacks!"); hand.setBet(hand.getBet() + (int)(hand.getBet() * .5)); shouldBreak = true;}
		//^Checks for a blackjack
	}
	
	public void moneyLose(Hand hand) {Cash -= hand.getBet();}
	
	public void moneyGain(Hand hand) {Cash += hand.getBet();}

	private void hit(Hand hand) {
		hand.drawCard();
		if(21 < hand.getRunningTotal() && hand.hand[hand.findNextEmpty() -1].getNumber() == 11) hand.hand[hand.findNextEmpty() -1].setNumber(1);
		System.out.println("\nThe " + hand.toString(hand.findNextEmpty() - 1) + " was drawn bringing the hand to a total of " + hand.getRunningTotal());
	}
	
	public boolean checkAce(Hand hand) {
		for(int cnt = 0; cnt < hand.findNextEmpty(); cnt++) {if(hand.hand[cnt].getNumber() == 11) {return true;}}
		return false; //Doesn't find an ace
	}
	
	public int findAce(Hand hand) {
		for(int cnt = 0; cnt < hand.findNextEmpty(); cnt++) {if(hand.hand[cnt].getNumber() == 11) {return cnt;}}
		return -1; //For compiling only. It should never be returned
	}
}