package texasholdem;

import java.util.ArrayList;

import cards.Card;

public class Player {
	String name;
	int chips = 0;
	ArrayList<Card> hand = new ArrayList<Card>();
	Boolean inGame = false;
	
	public Player(String name){
	this.name = name;
		
	}
	
	public String getName(){
		return name;
	}

	public int getChips() {
		return chips;
	}

	public void newHand(Card card1, Card card2) {
		hand.clear();
		hand.add(card1);
		hand.add(card2);
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void addChips(int chips) {
		this.chips+=chips;
	}

	public boolean addToPot(int i) {
		if(i > chips)
			return false;
		//Detta ska nog kontrolleras i game via getChips innan requesten ens kommer hit i slutprodukten.
		chips =-i;
		//Pot.betToPot(i);
		return true;
	}
	
	public int hashCode() { //Detta är ett utkast på en hashCode. Förbättringar kan behövas.
		int hash = 1+name.hashCode();
		return hash;
	}
	public boolean equals(Object o){ //Detta är ett utkast på en equals metod. Förbättringar kan behövas
		if(o == null)
			return false;
		if(!(o instanceof Player))
			return false;
		
		Player other = (Player) o;
		return this.hashCode() == other.hashCode();
	}

	public void setInGame(boolean b) {
		inGame = b;
		
	}

	public boolean getInGame() {
		return inGame;
	}

	
}
