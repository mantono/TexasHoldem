package texasholdem;

import java.util.ArrayList;

import cards.Card;

public class Player {
	String name;
	int chips = 0;
	ArrayList<Card> hand = new ArrayList<Card>();
	Boolean inGame = false;
	
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
	
	public int hashCode() { //Detta �r ett utkast p� en hashCode. F�rb�ttringar kan beh�vas.
		int hash = 1+name.hashCode();
		return hash;
	}
	
	public boolean equals(Object object){
		if(object == null)
			return false;
		if(!(object.getClass().equals(this.getChips())))
			return false;
		
		Player otherPlayer = (Player) object;
		return this.hashCode() == otherPlayer.hashCode();
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
		
	}

	public boolean getInGame() {
		return inGame;
	}

	
}
