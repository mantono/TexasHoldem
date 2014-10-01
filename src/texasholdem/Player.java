package texasholdem;

import java.util.ArrayList;

import cards.Card;

public class Player {
	String name;
	int chips = 0;
	ArrayList<Card> hand = new ArrayList<Card>();
	
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

}
