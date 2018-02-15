
public class Blackjack extends Deck{
	
	public static Deck deck = new Deck();
	
	public static void main(String[] args) {
		deck.shuffle();
	}
	
	//Make sure to only use one deck ever
//	public static void DeckTest() {
//		Deck test = new Deck();
//		test.shuffle();
//		for(int cnt = 0; cnt < 52; cnt++) {
//			System.out.println(test.toString(cnt));
//		}
//	}
	
//	public static void HandTest() {
//		handTest.drawCard();
//		System.out.println(handTest.toString());
//		handTest.drawCard();
//		System.out.println(handTest.toString(1));
//		handTest.drawCard();
//		System.out.println(handTest.toString(2));
//	}
	
}
