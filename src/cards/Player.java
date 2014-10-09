package cards;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
	private final String name;
	private int chips = 0;
	private ArrayList<Card> hand = new ArrayList<Card>();
	private boolean inGame = false;
	
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

	public void newHand(Card... cards) {
		hand.clear();
		hand.addAll(Arrays.asList(cards));
	}
	
	public void addToHand(Card... cards) {
		hand.addAll(Arrays.asList(cards));
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

	public boolean betToPot(int bet) {
		if(bet > chips || bet < 1)
			return false;
		chips -= bet;
		assert chips >= 0 : "The amount of chips must not be negative";
		return true;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
		
	}

	public boolean isInGame() {
		return inGame;
	}

	
}
