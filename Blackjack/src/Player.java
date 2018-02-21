import java.util.Scanner;
import java.lang.StringBuilder;

public class Player extends Hand{
	private int Cash, EndCode;
	private Scanner sc = new Scanner(System.in);
	private boolean shouldBreak;
	
	/**
	 * EndCode key:
	 * Code -1: Default, nothing happened, etc.
	 * Code 0: Doubled Down
	 * Code 1: Stood
	 * Code 2: Busted
	 * Code 3: Blackjacked
	 */
	
	public Player() {
		Cash = 1500;
		EndCode = -1;
		shouldBreak = false;
	}
	
	public void playerTurn(){
		EndCode = -1;
		Hand hand = new Hand();
		hand.drawCard();
		hand.drawCard();
		showHand(hand);
		hand.bet();
		menu(hand);
		if(EndCode == 0) {
			moneyLoss(hand);
		}
	}
	
	public String sTotalMoney() {
		return "$" + Cash;
	}
	
	public int getCash() {
		return Cash;
	}
	
	private void menu(Hand hand) {
		while(true) {
			System.out.println("Input your command");
			String in = sc.nextLine();
			if("help".equals(in) || "Help".equals(in)) {
				spacer();
				System.out.println("Commands:");
				System.out.println("\n\tHelp");
				System.out.println("\t\tDisplays the help module");
				System.out.println("\n\tShow Hand");
				System.out.println("\t\tShows the cards currently in your hand");
				System.out.println("\n\tHit");
				System.out.println("\t\tDraws a new card to your hand");
				System.out.println("\n\tDouble Down");
				System.out.println("\t\tHits, doubles the current bet, and stands the hand");
				System.out.println("\n\tStand");
				System.out.println("\t\tEnds the turn and lets the Computer play");
			}else if("Show Hand".equals(in) || "show hand".equals(in) || "show Hand".equals(in) || "Show hand".equals(in)) {
				spacer();
				int max = hand.findNextEmpty();
				showHand(hand, max);
			}else if("hit".equals(in) || "Hit".equals(in)) {
				hit(hand);
			}else if("Double Down".equals(in) || "double down".equals(in) || "double Down".equals(in) || "Double down".equals(in)) {
				hand.drawCard();
				hand.setBet(hand.getBet() * 2);
				break;
			}else if("Stand".equals(in) || "stand".equals(in)) {
				break;
			}else {
				System.out.println("Error: That input could not be parsed by the Scanner");
			}
			endOfRound(hand);
			if(shouldBreak == true) {
				break;
			}
		}
	}
	
	private void showHand(Hand hand) {
		hand.setRunningTotal();
		System.out.print("\n" + hand.toString() + "\n" + hand.toString(1));
		System.out.print("\n" + hand.getRunningTotal() + " is your current total");
	}
	
	private void showHand(Hand hand, int length) {
		hand.setRunningTotal();
		StringBuilder out = new StringBuilder();
		for(int cnt = 0; cnt < length; cnt++) {
			out.append(hand.toString(cnt));
		}
		System.out.println(out);
		System.out.println(hand.getRunningTotal() + " is your current total");
	}
	
	private void spacer() {
		for(int cnt = 0; cnt < 20; cnt++) {
			System.out.println();
		}
	}
	
	private void endOfRound(Hand hand) {
		int max = findNextEmpty();
		showHand(hand, max);
		if(21 < hand.getRunningTotal()) {
			System.out.println("Player Busts!");
			shouldBreak = true;
			EndCode = 0;
		}
		if(21 == hand.getRunningTotal()) {
			System.out.println("Player Blackjacks!");
			shouldBreak = true;
		}
	}
	
	private void moneyLoss(Hand hand) {
		Cash = Cash - hand.getBet();
	}

	private void hit(Hand hand) {
		hand.drawCard();
		System.out.print("\nThis is the card that was drawn: " + hand.toString(hand.findNextEmpty() - 1));
	}
}

/*else if("Split".equals(in) || "split".equals(in)) {
				if(hand.getCard(0).getNumber() == hand.getCard(1).getNumber()) {
					Hand split1 = new Hand();
					split1.setCard(0, hand.getCard(0));
					split1.drawCard();
					Hand split2 = new Hand();
					split2.setCard(0, hand.getCard(0));
					split2.drawCard();
					EndCode = 2;
					shouldBreak = true;
				}else {
					System.out.println("Error: You can not split this hand");
				}
				
//				System.out.println("\n\tSplit");
//				System.out.println("\t\tSplits the hand into two separate hands.");
//				System.out.println("\t\tCan be done multiple times");
//				System.out.println("\t\t***Note: If you split it will ask you which hand");
*/