import java.util.Scanner;

public class Hand {
	public Card[] hand = new Card[52];
	private int handBet;
	private int runningTotal;
	private int minimumBet;
	private Scanner sc = new Scanner(System.in);
	
	public Hand() {
		for(int cnt = 0; cnt < 52; cnt++) {hand[cnt] = new Card();}
		minimumBet = 1;
	}
	
	public void setBet(int bet) {handBet = bet;}
	
	public int getBet() {return handBet;}
	
	public void drawCard() {
		Card draw = Blackjack.deck.getCard();
		hand[findNextEmpty()] = draw;
	}
	
	public String toString() {return "" + hand[0];}
	
	public String toString(int index) {return "" + hand[index];}
	
	public int findNextEmpty() {
		boolean found = false;
		int index = -1;
		int cardIndx = 0;
		while(found != true) {
			if(hand[cardIndx].getSuit() == -1) {
				found = true;
				index = cardIndx;
				break;
			}else cardIndx++;
		}
		return index;
	}

	public int getRunningTotal() {
		setRunningTotal();
		return runningTotal;
	}
	
	public void setRunningTotal() {
		int maxIndex = findNextEmpty();
		runningTotal = 0;
		for(int cnt = 0; cnt < maxIndex; cnt++) {runningTotal += hand[cnt].getNumber();}
	}
	
	public void bet() {
		while(true) {
			System.out.println("\nHow much do you want to bet?\n");
			handBet = betIn();
			if(minimumBet - 1 < handBet && !(Blackjack.player.getCash() < handBet)) {break;}
			else {
				System.out.println("\nThe minimum bet is " + minimumBet);
				System.out.println("\nThe maximum bet is " + Blackjack.player.getCash());
			} 
		}
		System.out.print("\nYou bet " + handBet + "\n");
	}
	
	private int betIn(){
		int bet = sc.nextInt();
		return bet;
	}
	
	public void setCard(int index, Card card) {hand[index] = card;}
}
