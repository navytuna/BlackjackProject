
public class Card {
	
	private int suit, number;
	private String name; //For facecards only
	
	public Card() {name = "p"; suit = -1; number = 0;} //Empty constructor so a hand of cards can be created to later be instantiated
	
	public Card(int initSuit, int initNum) {suit = initSuit; number = initNum;}
	
	public Card(int initSuit, int initNum, String initName) {name = initName; suit = initSuit; number = initNum;}
	
	public String getName() {return name;}
	
	public int getSuit() {return suit;}
	
	public int getNumber() {return number;}
	
	public void setNumber(int numIn) {number = numIn;}
	
	public String suitValue() {
		if(suit == 1) {return "Spades";}
		if(suit == 2) {return "Hearts";}
		if(suit == 3) {return "Clubs";}
		if(suit == 4) {return "Diamonds";}
		return "Error: Suit Data Exceeds Expectations";
	}
	
	public boolean isFaceCard() {if("p".equals(this.getName())) {return false;} return true;}
}
