package texasholdem;

import java.util.ArrayList;
import java.util.List;

import cards.*;

public class Game extends CardGame {
	private Pot pot = new Pot();
	private boolean roundIsActive = false;
	private int smallBlindPosition = 0;
	private int bigBlind = 4;
	private double blindsRaisePercentage = 0.2;

	public Game(int bigBlind, double blindsRaisePercentage, Player... players) {
		this(players);
		if (bigBlind < 1)
			throw new IllegalArgumentException(
					"Error: parameter bigBlind is less than 1.");
		if (blindsRaisePercentage < 0)
			throw new IllegalArgumentException(
					"Error: parameter blindsRaisePercentage is less than 0.");
		this.bigBlind = bigBlind;
		this.blindsRaisePercentage = blindsRaisePercentage;
	}

	public Game(Player... players) {
		super(players);
	}

	public void initiateRound() {
		if (roundIsActive)
			throw new IllegalStateException(
					"A new round can not be initialized while a round is still active.");
		roundIsActive = true;
		setAllPlayersInGame(true);
		newDeck();
		assert (getSizeOfDeck() == 52) : "A new deck must be generated before a new round.";
		placeBlinds();
		dealCards(2);
	}

	private void setAllPlayersInGame(boolean inGame) {
		for (Player player : getPlayers())
			player.setInRound(inGame);
	}

	private void placeBlinds() {
		int bigBlindPosition = smallBlindPosition + 1;
		if (bigBlindPosition >= getPlayers().size())
			bigBlindPosition = 0;
		for (int i = 0; i < getPlayers().size(); i++) {
			if (i == smallBlindPosition) {
				if (getPlayerWithSmallBlind().subtractChips(getSmallBlind()))
					pot.receiveBet(getSmallBlind(),
							getPlayers().get(smallBlindPosition));
			} else if (i == bigBlindPosition) {
				if (getPlayerWithBigBlind().subtractChips(getBigBlind()))
					pot.receiveBet(getBigBlind(),
							getPlayers().get(bigBlindPosition));
			}
		}
	}

	public Player getPlayerWithSmallBlind() {
		return getPlayer(smallBlindPosition);
	}

	public Player getPlayerWithBigBlind() {
		int bigBlindPosition = smallBlindPosition + 1;
		if (bigBlindPosition >= getPlayers().size())
			bigBlindPosition = 0;
		return getPlayer(bigBlindPosition);
	}

	public void raiseBlinds() {
		int previousBigBlind = bigBlind;
		bigBlind = (int) Math.round(bigBlind * (1 + blindsRaisePercentage));
		if (previousBigBlind == bigBlind && blindsRaisePercentage != 0)
			bigBlind++;
	}
	

	public boolean playerAction(Player player, Action action, int raiseBy) {
		return false;
	}

	public boolean playerAction(Player player, Action action) {
		if(!player.isInRound() || !roundIsActive)
			throw new IllegalStateException("Player may not " + action + " while not in a round.");
		
		switch(action){
			case ALL_IN: return allIn(player);
			case CALL: return call(player);
			case CHECK: return check(player);
			case FOLD: return fold(player);
			case RAISE: return raise(player);
			default: return false;
		}
	}
	
	private boolean allIn(Player player){
		return false;
	}
	
	private boolean call(Player player){
		// TODO 
		/*
		 * Ta reda på senaste höjningen
		 * Kolla hur mycket player har satsat innan
		 * Satsa mellanskillnaden
		*/
		return true;
	}
	private boolean check(Player player){
		return false;
	}
	private boolean fold(Player player){
		player.setInRound(false);
		return true;
	}
	private boolean raise(Player player){
		return false;
	}

	public void endRound() {
		roundIsActive = false;
		setAllPlayersInGame(false);
		raiseBlinds();
		moveSmallBlindPosition();
		clearAllHands();
		clearTableOfCards();
		pot.resetPot();
	}

	public void endGame() {
		if(roundIsActive)
			endRound();
		blindsRaisePercentage = 0.2;
		bigBlind = 4;
		smallBlindPosition = 0;
	}

	public void distributeChip(ArrayList<ArrayList<Player>> victoryOrder) {
		for (ArrayList<Player> victoryPosition : victoryOrder) {
			int amountOfWinners = victoryPosition.size();
			int leftoverFromchipsWonCalculation = (pot.getBetHistory(victoryPosition.get(0)) % amountOfWinners);
			int chipsWon;
			boolean isThereLeftOvers = false;
			if (leftoverFromchipsWonCalculation != 0) {
				chipsWon = (pot.getBetHistory(victoryPosition.get(0)) / amountOfWinners) + 1;
				isThereLeftOvers = true;
			} else {
				chipsWon = (pot.getBetHistory(victoryPosition.get(0)) / amountOfWinners);
			}
			for (Player winner : victoryPosition) {
				pot.handOutChips(winner, chipsWon, amountOfWinners);
				if (isThereLeftOvers) {
					if (victoryPosition.get(0) != winner) {
						int amountOfErrors = victoryPosition.size() - 1;
						winner.addChips(1);
						victoryPosition.get(0).subtractChips(1);

					}
				}

			}
		}
	}

	public void receiveBet(int bet, Player player) {
		pot.receiveBet(bet, player);
	}

	public void initiateDeal() {
		if (getCardsOnTable().size() >= 5)
			throw new IllegalStateException();
		else if (getCardsOnTable().size() == 0)
			for (byte b = 0; b < 3; b++)
				putCardOnTable();
		else
			putCardOnTable();

		assert getCardsOnTable().size() >= 3 : "After the initiateDeal has been called, at least the flop (first three cards) must have been dealt.";
	}

	private int moveSmallBlindPosition() {
		smallBlindPosition++;
		if (smallBlindPosition > (getPlayers().size() - 1))
			smallBlindPosition = 0;
		return smallBlindPosition;
	}

	public int getBigBlind() {
		return bigBlind;
	}

	public int getSmallBlind() {
		return bigBlind / 2;
	}

	public double getBlindsRaisePercentage() {
		return blindsRaisePercentage;
	}

	public int getSmallBlindPosition() {
		return smallBlindPosition;
	}
	public boolean getRoundIsActive(){
		return roundIsActive;
	}

}
