package texasholdem;
import java.util.*;

import cards.*;
public class Game{
	private Deck deck;
	private ArrayList<Player> playersInGame = new ArrayList<Player>();
	private List<Card> cardsOnTable = new ArrayList<Card>();
	private Pot pot = new Pot();
	private boolean roundIsActive = false;;
	private int smallBlindPosition = 0;
	private int bigBlind = 4;
	private double blindsRaisePercentage = 0.2;
	
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
		deck = new Deck();
	}
	
	public void newDeck(byte amoutOfSets){
		deck = new Deck(amoutOfSets);
	}
	
	public void initiateRound(){
		if(roundIsActive)
			throw new IllegalStateException("A new round can not be initialized while a round is still active.");
		roundIsActive = true;
		placeBets();
	}
	
	private void placeBets()
	{
		int bigBlindPosition = smallBlindPosition + 1;
		if(bigBlindPosition >= playersInGame.size())
			bigBlindPosition = 0;
		for(int i = 0; i < playersInGame.size(); i++)
		{
			if(i == smallBlindPosition)
				playersInGame.get(smallBlindPosition).addToPot(getSmallBlind(), pot);
			else if(i == bigBlindPosition)
				playersInGame.get(bigBlindPosition).addToPot(getBigBlind(), pot);
		}
	}
	
	public void clearAllHands(){
		for(Player p : playersInGame)
			p.clearHand();
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
		moveSmallBlindPosition();
		roundIsActive = false;
	}
	
	public void endGame(){
		
	}
	
	public void distributeChip(){
		
	}
	
	public void initiateDeal(){
		if(cardsOnTable.size() >= 5)
			throw new IllegalStateException();
		else if(cardsOnTable.size() == 0)
			for(byte b = 0; b < 3; b++)
				cardsOnTable.add(deck.drawCard());
		else
			cardsOnTable.add(deck.drawCard());
		
		assert cardsOnTable.size() >= 3 : "After the initiateDeal has been called, at least the flop (first three cards) must have been dealt.";
	}
	
	private int moveSmallBlindPosition()
	{
		smallBlindPosition++;
		if(smallBlindPosition > (playersInGame.size() - 1))
			smallBlindPosition = 0;
		return smallBlindPosition;
	}
	
	public void restartGame(){
		
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
	
	public int getSmallBlindPosition(){
		return smallBlindPosition;
	}
	
}
