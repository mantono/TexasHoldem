package texasholdem;
import java.util.*;

import cards.*;
public class Game{
	Deck deck;
	ArrayList<Player> playersInGame = new ArrayList<Player>();
	List<Card> cardsOnTable = new ArrayList<Card>();
	int currentSmallBlindPosition = 0;
	int bigBlind = 4;
	double blindsRaisePercentage = 0.2;
	
	public Game(int bigBlind, double blindsRaisePercentage, Player... players){
		this(players);
		if(bigBlind < 1)
			throw new IllegalArgumentException("Error: parameter bigBlind is less than 1.");
		if(blindsRaisePercentage < 0)
			throw new IllegalArgumentException("Error: parameter blindsRaisePercentage is less than 0.");
		this.bigBlind = bigBlind;
		this.blindsRaisePercentage = blindsRaisePercentage;
	}
	
	public Game(Player... players){
		playersInGame.addAll(Arrays.asList(players));
	}
	
	public void newDeck(){
		
	}
	
	public void initiateRound(){
		
	}
	
	public void clearAllHands(){
		
	}
	
	public void raiseBlinds(){
		int previousBigBlind = bigBlind;
		bigBlind = (int) Math.round(bigBlind*(1+blindsRaisePercentage));
		if(previousBigBlind == bigBlind && blindsRaisePercentage != 0)
			bigBlind++;
	}

	
	public Action requestPlayerAction(){
		return Action.FOLD;		
	}
	
	public void endRound(){
		raiseBlinds();
	}
	
	public void endGame(){
		
	}
	
	public void distrubuteChip(){
		
	}
	
	public void initiateGame(){
		
	}
	
	public void initiateDeal(){
		
	}

	public ArrayList<Player> getPlayersInGame(){
		return playersInGame;
	}
	
	public List<Card> getCardsOnTable(){
		return cardsOnTable;
	}
	
	public Deck getCurrentDeck(){
		return deck;
	}

	public int getBigBlind(){
		return bigBlind;
	}
	
	public int getSmallBlind(){
		return bigBlind/2;
	}

	public double getBlindsRaisePercentage(){
		return blindsRaisePercentage;
	}
	
	public int getCurrentSmallBlindPosition(){
		return currentSmallBlindPosition;
	}
	
}
