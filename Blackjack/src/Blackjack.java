
public class Blackjack extends Deck{
	public static Player Player = new Player();
	public static Deck deck = new Deck();
	public static boolean isPlayerTurn = true;
	public static boolean gameOver = false;
	
	public static void main(String[] args) {
		System.out.println("Welcome.");
		System.out.println("Please wait, the game is initializing");
		deck.shuffle();
		game();
		for(int cnt = 0; cnt < 40; cnt++) {
			System.out.println("\n");
		}
		System.out.println("You have stood from the table.");
		System.out.println("Come back soon");
	}
	
	public static void game() {
		while(true) {
			while(true) {
				if(isPlayerTurn) {
					Player.playerTurn();
					isPlayerTurn = false;
				}else {
					//computer.computerTurn();
					break;
				}
			}
			//roundEnd();
			if(gameOver) {
				break;
			}
		}
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
