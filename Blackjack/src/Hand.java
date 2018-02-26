import java.util.Scanner;

public class Hand {
	public Card[] hand = new Card[52];
	private int handBet;
	private int runningTotal;
	private int minimumBet;
	private Scanner sc = new Scanner(System.in);
	
	public Hand() {for(int cnt = 0; cnt < 52; cnt++) {hand[cnt] = new Card();} minimumBet = 1;}
	
	public void setBet(int bet) {handBet = bet;}
	
	public int getBet() {return handBet;}
	
	public void drawCard() {Card draw = Blackjack.deck.getCard(); setCard(findNextEmpty(), draw);}
	
	public String toString() {
		if(hand[0].getName()!= null) {return hand[0].getName() + " of " + hand[0].suitValue();}
		else return "" + hand[0].getNumber() +" of " + hand[0].suitValue();
	}
	
	public String toString(int index) {
		if(hand[index].getName() != null) {return hand[index].getName() + " of " + hand[index].suitValue();}
		else return "" + hand[index].getNumber() + " of " + hand[index].suitValue();
	}
	
	public int findNextEmpty() {
		boolean found = false;
		int index = -1;
		int cardIndx = 0;
		while(found != true) {
			if(hand[cardIndx].getSuit() == -1) {found = true; index = cardIndx; break;}
			else cardIndx++;
		}
		return index;
	}

	public int getRunningTotal() {setRunningTotal(); return runningTotal;}
	
	public void setRunningTotal() {
		int maxIndex = findNextEmpty();
		runningTotal = 0;
		for(int cnt = 0; cnt < maxIndex; cnt++) {runningTotal += hand[cnt].getNumber();}
	}
	
	public void bet() {
		while(true) {
			System.out.println("\nHow much do you want to bet?\n");
			try{handBet = betIn();}catch (java.util.InputMismatchException e) {System.out.print("\n\nERROR! Do not input anything other than a number when betting. The bet has defaulted to $1"); handBet = 1;}
			if(minimumBet - 1 < handBet && !(Blackjack.player.getCash() < handBet)) {break;}
			else {System.out.println("\nThe minimum bet is " + minimumBet + "\nThe maximum bet is " + Blackjack.player.getCash());} 
		}
		System.out.print("\nYou bet $" + handBet + "\n");
	}
	
	private int betIn() throws java.util.InputMismatchException{int bet = sc.nextInt(); return bet;}
	
	public void setCard(int index, Card card) {hand[index] = card;}
}
