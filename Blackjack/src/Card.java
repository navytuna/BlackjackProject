
public class Card {
	
	private int suit, number;
	private String name; //For facecards only
	
	public Card() { //Placeholder card shit
		name = "p";
		suit =0;
		number = 0;
	}
	
	public Card(int initSuit, int initNum) {
		suit = initSuit;
		number = initNum;
	}
	
	public Card(int initSuit, int initNum, String initName) {
		name = initName;
		suit = initSuit;
		number = initNum;
	}
	
	public Card(Card arg) {
		name = arg.getName();
		suit = arg.getSuit();
		number = arg.getNumber();
	}
	
	public String getName() {
		return name;
	}
	
	public int getSuit() {
		return suit;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setName(String nameIn) {
		name = nameIn;
	}
	
	public void setSuit(int suitIn) {
		suit = suitIn;
	}
	
	public void setNumber(int numIn) {
		number = numIn;
	}
	
	public String suitValue() {
		if(suit == 1) {
			return "Spades";
		}else if(suit == 2) {
			return "Hearts";
		}else if(suit == 3) {
			return "Clubs";
		}else if(suit == 4) {
			return "Diamonds";
		}
		return "Error: Suit Data Exceeds Expectations";
	}
	
	public String toString() {
		if(name == null) {
		 return "Suit: " + suitValue() + " Value: " + number;	
		}else {
		 return "Name: " + name + " Suit: " + suitValue() + " Value: " + number;
		}
	}
}
