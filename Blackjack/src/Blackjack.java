
public class Blackjack extends Deck{

	public static void main(String[] args) {
		DeckTest();
	}
	
	public static void DeckTest() {
		Deck test = new Deck();
		test.shuffle();
		for(int cnt = 0; cnt < 52; cnt++) {
			System.out.println(test.toString(cnt));
		}
	}
}
