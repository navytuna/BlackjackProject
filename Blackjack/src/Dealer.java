
public class Dealer extends Player {
	private Hand myHand = new Hand();
	public Dealer(Hand hand) {myHand = hand;}
	
	public void turn() {
		while(true) {
			//Simple dealer logic
			if(myHand.getRunningTotal() < 17) {myHand.drawCard();}
			if(17 <= myHand.getRunningTotal()) {if(21 <= myHand.getRunningTotal() && !(this.checkAce(myHand))) {myHand.hand[this.findAce(myHand)].setNumber(1);} else break;}
		}
	}
	
}
