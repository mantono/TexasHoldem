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
		setAllPlayersInGame();
		newDeck();
		assert (getSizeOfDeck() == 52) : "A new deck must be generated before a new round.";
		placeBlinds();
		dealCards(2);
	}

	private void setAllPlayersInGame() {
		for (Player player : getPlayers())
			player.setInRound(true);
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

	public boolean playerAction(Player player, Action action) {
		return false;
	}

	public boolean playerAction(Player player, Action action, int raiseBy) {
		return false;
	}

	public void endRound() {
		roundIsActive = false;
		raiseBlinds();
		moveSmallBlindPosition();
		clearAllHands();
	}

	public void endGame() {
		
		if(getPlayers().size() != 1){
			throw new IllegalStateException("Error: Only one player can be the winner.");
		}
		
		
		blindsRaisePercentage = 0.2;
		bigBlind = 4;
		smallBlindPosition = 0;
		roundIsActive = false;
		
		assert getPlayers().size() == 1 : "Only one player can be the winner.";
		
		clearAllHands();
		clearTableOfCards();
		pot.resetPot();

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
