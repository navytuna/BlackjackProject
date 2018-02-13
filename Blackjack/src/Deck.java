
public class Deck extends Card {
	
	private Card[] Card;
	
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
					Card[index] = new Card(cnt2 + 1, cnt);
				}else {
					Card[index] = new Card(cnt2 + 1	, cnt, name);
				}
				index++;
			}
		}
	}
}
