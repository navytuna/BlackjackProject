
public class Hand extends Deck{
	
	private Card[] hand = new Card[52];
	private int handBet; //Used for main betting and for split betting
	private int runningTotal;
	
	public Hand() {
		for(int cnt = 0; cnt < 52; cnt++) {
			hand[cnt] = new Card();
		}
	}
	
	public void setBet(int bet) {
		handBet = bet;
	}
	
	public int getBet() {
		return handBet;
	}
	
	public void drawCard() {
		Card draw = Blackjack.deck.getCard(0);
		hand[findNextEmpty()] = draw;
	}
	
	public String toString() {
		return "" + hand[0];
	}
	
	public int findNextEmpty() {
		boolean found = false;
		int index = -1;
		int cardIndx = 0;
		while(found != true) {
			if(hand[cardIndx].getSuit() == -1) {
				found = true;
				index = cardIndx;
				break;
			}else {
				cardIndx++;
			}
		}
		return index;
	}
	
	public String toString(int index) {
		return "" + hand[index];
	}
	
	public int getRunningTotal() {
		setRunningTotal();
		return runningTotal;
	}
	
	public void setRunningTotal() {
		int maxIndex = findNextEmpty();
		runningTotal = 0;
		for(int cnt = 0; cnt < maxIndex; cnt++) {
			runningTotal += hand[cnt].getNumber();
		}
	}
}
