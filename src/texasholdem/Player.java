package texasholdem;

import java.util.ArrayList;

import cards.Card;

public class Player {
	private final String name;
	private int chips = 0;
	private ArrayList<Card> hand = new ArrayList<Card>();
	private Boolean inGame = false;
	
	public Player(String name, int chips){
	this.name = name;
	this.chips = chips;
		
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

	public void clearHand(){
		hand.clear();
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}

	public int addChips(int add) {
		return chips += add;
	}

	public boolean addToPot(int i, Pot p) {
		if(i > chips)
			return false;
		//Detta ska nog kontrolleras i game via getChips innan requesten ens kommer hit i slutprodukten.
		chips =-i;
		p.betToPot(i, this);
		return true;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
		
	}

	public boolean getInGame() {
		return inGame;
	}

	
}
