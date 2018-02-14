
public class Deck extends Card {
	
	private Card[] CardDeck = new Card[52];
	
	public Deck() {
		int index = 0;
		String name = "Not Used";
		int value = 0;
		for(int cnt = 0; cnt < 4; cnt++) {
			for(int cnt2 = 0; cnt2 < 13; cnt2++) {
				if(cnt2 == 0 || 9 < cnt2) {
					if(cnt2 == 0) {
						name = "ace";
						value = -1;
					}else if(cnt2 == 10) {
						name = "jack";
						value = 10;
					}else if(cnt2 == 11) {
						name = "queen";
						value = 10;
					}else if(cnt2 == 12) {
						name = "king";
						value = 10;
					}else {
						System.out.println("Critical Error: Cnt2 out of bounds");
						System.exit(1);
					}
				}
				if(name.equals("Not Used")) {
					CardDeck[index] = new Card(cnt + 1, cnt2 + 1);
				}else {
					CardDeck[index] = new Card(cnt + 1, value, name);
					name = "Not Used";
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
		//Make CERTAIN to use the correct cnt!!!!!!!
		for(int cnt = 0; cnt < 7; cnt++) {
			for(int cnt2 = 0; cnt2 < 52; cnt2++) {
				double choice = Math.random()*(100) + 1;
				int index1 = (int)Math.floor(Math.random()*23 + 1);
				int index2 = (int)Math.floor(Math.random()*23 + 1);
				if(choice <= 50) {
					if(0 < Split1Cnter) {
						int addVar = 0;
						while(Split1[index1 + addVar].getNumber() == -2) {
							if(25 <= index1 + addVar + 1) {
								addVar = 25 - index1;
							}else {
								addVar++;
							}
						}
						CardDeck[cnt2] = Split1[index1 + addVar];
						Split1[index1 + addVar].setNumber(-2);
						Split1Cnter--;
					}else {
						if(0 < Split2Cnter) {
							int addVar = 0;
							while(Split2[index2 + addVar].getNumber() == -2) {
								if(25 <= index2 + addVar + 1) {
									addVar = 25 - index2;
								}else {
									addVar++;
								}
							}
							CardDeck[cnt2] = Split2[index2 + addVar];
							Split2[index2 + addVar].setNumber(-2);
							Split2Cnter--;
						}
					}
				}else {
					if(0 < Split2Cnter) {
						if(0 < Split2Cnter) {
							int addVar = 0;
							while(Split2[index2 + addVar].getNumber() == -2) {
								if(25 <= index2 + addVar + 1) {
									addVar = 25 - index2;
								}else {
									addVar++;
								}
							}
							CardDeck[cnt2] = Split2[index2 + addVar];
							Split2[index2 + addVar].setNumber(-2);
							Split2Cnter--;
						}
					}else {
						if(0 < Split1Cnter) {
							int addVar = 0;
							while(Split1[index1 + addVar].getNumber() == -2) {
								if(25 <= index1 + addVar + 1) {
									addVar = 25 - index1;
								}else {
									addVar++;
								}
							}
							CardDeck[cnt2] = Split1[index1 + addVar];
							Split1[index1 + addVar].setNumber(-2);
							Split1Cnter--;
						}
					}
				}
			}
		}
	}
	
	public String toString() {
		return CardDeck[0].toString();
	}
	
	public String toString(int card) {
		return CardDeck[card].toString();
	}
}
