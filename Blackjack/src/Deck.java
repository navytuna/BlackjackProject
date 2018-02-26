
public class Deck {
	private Card[] CardDeck = new Card[52];
	private int DeckIndex = 0;
	
	public Deck() {
		int index = 0;
		String name = "Not Used";
		int value = 0;
		for(int cnt = 0; cnt < 4; cnt++) {
			for(int cnt2 = 0; cnt2 < 13; cnt2++) {
				if(cnt2 == 0 || 9 < cnt2) {		//Setting Card Names
					if(cnt2 == 0) {name = "Ace"; value = 11;}
					else if(cnt2 == 10) {name = "Jack"; value = 10;}
					else if(cnt2 == 11) {name = "Queen"; value = 10;}
					else if(cnt2 == 12) {name = "King"; value = 10;}
					else {System.out.println("Error: Cnt2 out of bounds"); System.exit(0);} //Error Handling
				}
				if(name.equals("Not Used")) {CardDeck[index] = new Card(cnt + 1, cnt2 + 1);}
				else {CardDeck[index] = new Card(cnt + 1, value, name); name = "Not Used";}
				index++;
			}
		}
		for(int cnt = 0; cnt < 8; cnt++) {shuffle();}
	}
	
	public void shuffle() {
		for(int cnt = 0; cnt < (int)(Math.random()*100+22); cnt++) {
			int randIndex1 = (int)(Math.random()*52);
			int randIndex2 = 0;
			int temp = 0;
			for(int cnt2 = 0; cnt2 < 10000; cnt2++) {
				temp = (int)(Math.random()*52);
				if(temp != randIndex1) {randIndex2 = temp; break;}
			}
			cardSwap(CardDeck, randIndex1, randIndex2);
		}
		DeckIndex = 0;
	}
	
	public void cardSwap(Card[] Card, int index1, int index2) {
		Card temp = Card[index1];
		Card[index1] = Card[index2];
		Card[index2] = temp;
	}
	
	public Card getCard() { //Functions so that the next card off of the stack is drawn rather than the same card
		int card = 0;
		if(DeckIndex <= 52) {
			card = DeckIndex;
			if(DeckIndex == 52) {DeckIndex = 0;}
			else {DeckIndex++;}
		}
		return CardDeck[card];
	}
}
