public class Blackjack {
	public static Player player = new Player();
	public static Deck deck = new Deck();
	public static boolean isPlayerTurn = true;
	public static boolean gameOver = false;
	private static int shuffleThrottle = 0;
	
	public static void main(String[] args) {game();}
	
	public static void game() {
		System.out.println("If you get confused or forget the commands they are found in PlayerCommands.txt");
		while(true) {
			Hand dealerHand = new Hand();
			Hand hand = new Hand();
			if(isPlayerTurn) {
				player.playerTurn(dealerHand, hand);
				isPlayerTurn = false;
			}else {
				Dealer dealer = new Dealer(dealerHand);
				dealer.turn();
				break;
			}
			roundEnd(dealerHand, hand);
			if(gameOver) {break;}
		}
		System.out.print("\n\nGame Over\n\n");
	}
	
	private static void roundEnd(Hand dealerHand, Hand hand) {
		if(dealerHand.getRunningTotal() < hand.getRunningTotal() && hand.getRunningTotal() <= 21) {
			System.out.print("\nPlayer wins ");
			endRound(hand);
			System.out.print("\nOver the dealer's hand of");
			endRound(dealerHand);
			player.moneyGain(hand);
		}else if(dealerHand.getRunningTotal() == hand.getRunningTotal()) {
			System.out.println("Push!");
		}else {
			System.out.print("\nPlayer loses ");
			endRound(hand);
			System.out.print("\nTo the dealer's hand of");
			endRound(dealerHand);
			player.moneyLose(hand);
		}
		if(player.getCash() <= 0) {gameOver = true;}
		isPlayerTurn = true;
		gameShuffle();
	}
	
	private static void gameShuffle() {
		if(9 < Math.random()*10 + 1 && 12 < shuffleThrottle) {
			deck.shuffle(); 
			System.out.println("\n\nDeck Shuffled\n\n");
		}else shuffleThrottle++;
	}
	
	private static void endRound(Hand hand) {
		if(0 < hand.getBet()) {System.out.print(hand.getBet());} //Prevents the 0 bet of the dealer's hand from cropping up
		System.out.print(" with a hand of ");
		for(int cnt = 0; cnt < hand.findNextEmpty(); cnt++) {System.out.print(hand.toString(cnt) + " ");}
		System.out.print("\n");
	}
}