
public class Deck extends Card {
	
	private Card[] CardDeck = new Card[52];
	
	public Deck() {
		int index = 0;
		String name = "Not Used";
		for(int cnt = 0; cnt < 4; cnt++) {
			for(int cnt2 = 0; cnt2 < 13; cnt2++) {
				if(cnt2 == 0 || 9 < cnt2) {
					if(cnt2 == 0) {
						name = "ace";
					}else if(cnt2 == 10) {
						name = "jack";
					}else if(cnt2 == 11) {
						name = "queen";
					}else if(cnt2 == 12) {
						name = "king";
					}else {
						System.out.println("Critical Error: Cnt2 out of bounds");
						System.exit(1);
					}
				}
				if(name.equals("Not Used")) {
					CardDeck[index] = new Card(cnt2 + 1, cnt);
				}else {
					CardDeck[index] = new Card(cnt2 + 1	, cnt, name);
				}
				index++;
			}
		}
		for(int cnt = 0; cnt < 4; cnt++) {
			shuffle();
		}
	}
	
	public void shuffle() {
		Card[] Split1 = new Card[26];
		int Split1Cnter = 0;
		Card[] Split2 = new Card[26];
		int Split2Cnter = 0;
		for(int cnt = 0; cnt < 52; cnt++) {
			if(cnt == 0 || cnt%2 == 0) {
				Split1[Split1Cnter] = CardDeck[cnt];
				Split1Cnter++;
			}else {
				Split2[Split2Cnter] = CardDeck[cnt];
				Split2Cnter++;
			}
		}
		Split1Cnter--;
		Split2Cnter--;
		for(int cnt = 0; cnt < 7; cnt++) {
			for(int cnt2 = 0; cnt2 < 52; cnt2++) {
				double choice = Math.random()*(100) + 1;
				if(choice <= 50) {
					if(0 < Split1Cnter) {
						CardDeck[cnt] = Split1[Split1Cnter];
						Split1Cnter--;
					}else {
						if(0 < Split2Cnter) {
							CardDeck[cnt] = Split2[Split2Cnter];
							Split2Cnter--;
						}
					}
				}else {
					if(0 < Split2Cnter) {
						CardDeck[cnt] = Split2[Split2Cnter];
						Split2Cnter--;
					}else {
						if(0 < Split1Cnter) {
							CardDeck[cnt] = Split1[Split1Cnter];
							Split1Cnter--;
						}
					}
				}
			}
		}
	}
	
	public String toString() {
		String out = "";
		for(int cnt = 0; cnt < CardDeck.length; cnt++) {
			out.concat(CardDeck[cnt].toString());
		}
		return out;
	}
}
