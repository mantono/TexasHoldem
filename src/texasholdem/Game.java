package texasholdem;

import java.util.List;

import cards.*;
public class Game extends CardGame{
	private Pot pot = new Pot();
	private boolean roundIsActive = false;
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
		super(players);
	}
	
	public void initiateRound(){
		if(roundIsActive)
			throw new IllegalStateException("A new round can not be initialized while a round is still active.");
		roundIsActive = true;
		placeBets();
		dealCards(2);
	}
	
	private void placeBets(){
		int bigBlindPosition = smallBlindPosition + 1;
		if(bigBlindPosition >= getPlayers().size())
			bigBlindPosition = 0;
		for(int i = 0; i < getPlayers().size(); i++){
			if(i == smallBlindPosition){
				if(getPlayerWithSmallBlind().betToPot(getSmallBlind()))
					pot.receiveBet(getSmallBlind(), getPlayers().get(smallBlindPosition));
			}
			else if(i == bigBlindPosition){
				if(getPlayerWithBigBlind().betToPot(getBigBlind()))
					pot.receiveBet(getBigBlind(), getPlayers().get(bigBlindPosition));
			}
		}
	}

	public Player getPlayerWithSmallBlind(){
		return getPlayer(smallBlindPosition);		
	}
	
	public Player getPlayerWithBigBlind(){
		int bigBlindPosition = smallBlindPosition + 1;
		if(bigBlindPosition >= getPlayers().size())
			bigBlindPosition = 0;
		return getPlayer(bigBlindPosition);
	}
	
	public void raiseBlinds(){
		int previousBigBlind = bigBlind;
		bigBlind = (int) Math.round(bigBlind*(1+blindsRaisePercentage));
		if(previousBigBlind == bigBlind && blindsRaisePercentage != 0)
			bigBlind++;
	}

	
	public boolean playerAction(Player player, Action action){
		return false;
	}
	
	public boolean playerAction(Player player, Action action, int raiseBy){
		return false;
	}
	
	public void endRound(){
		raiseBlinds();
		moveSmallBlindPosition();
		clearAllHands();
		roundIsActive = false;
	}
	
	public void endGame(){
		
	}
	
	public void distributeChip(){
		
	}
	
	public void initiateDeal(){
		if(getCardsOnTable().size() >= 5)
			throw new IllegalStateException();
		else if(getCardsOnTable().size() == 0)
			for(byte b = 0; b < 3; b++)
				putCardOnTable();
		else
			putCardOnTable();
		
		assert getCardsOnTable().size() >= 3 : "After the initiateDeal has been called, at least the flop (first three cards) must have been dealt.";
	}
	
	private int moveSmallBlindPosition()
	{
		smallBlindPosition++;
		if(smallBlindPosition > (getPlayers().size() - 1))
			smallBlindPosition = 0;
		return smallBlindPosition;
	}
	
	public void restartGame(){
		
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
