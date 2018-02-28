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
			while(true) {
				if(isPlayerTurn) {player.playerTurn(dealerHand, hand); isPlayerTurn = false;}
				else {Dealer dealer = new Dealer(dealerHand); dealer.turn(); break;}
			}
			roundEnd(dealerHand, hand);
			if(gameOver) {break;}
		}
		System.out.print("\n\nGame Over\n\n");
	}
	
	private static void roundEnd(Hand dealerHand, Hand hand) {
		//Determines result of a round of play
		if(dealerHand.getRunningTotal() < hand.getRunningTotal() && hand.getRunningTotal() <= 21) { //Too large a statement to compress
			System.out.print("\nPlayer wins $" + hand.getBet() + " over the dealer's hand containing the ");
			endRound(dealerHand);
			System.out.print("\n");
			player.moneyGain(hand);
		}
		else if(dealerHand.getRunningTotal() == hand.getRunningTotal()) {System.out.println("Push!");}
		else { //Too large to compress
			System.out.print("\nPlayer loses $" + hand.getBet() + " to the dealer's hand containing the ");
			endRound(dealerHand);
			System.out.print("\n");
			player.moneyLose(hand);
		}
		if(player.getCash() <= 0) {gameOver = true;}
		isPlayerTurn = true;
		gameShuffle();
	}
	
	private static void gameShuffle() {
		//Shuffles after a few rounds and a random check
		if(9 < Math.random()*10 + 1 && 3 < shuffleThrottle) {deck.shuffle(); System.out.println("\n\nDeck Shuffled\n\n");}
		else shuffleThrottle++;
	}
	
	private static void endRound(Hand hand) {
		//Print statements adjusting for card count
		if(hand.findNextEmpty() != 2) {
			for(int cnt = 0; cnt < hand.findNextEmpty(); cnt++) {
				if(cnt < hand.findNextEmpty() - 1) {System.out.print(hand.toString(cnt) + ", ");} 
				else System.out.print("and " + hand.toString(cnt));
			}
		}
		else System.out.print(hand.toString(0) +  " and the " + hand.toString(1));
	}
}