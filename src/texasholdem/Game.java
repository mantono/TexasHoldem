package texasholdem;

import java.util.ArrayList;
import java.util.List;

import cards.*;

public class Game
{
	private Pot pot = new Pot();
	private final PlayerBook<BettingPlayer> players;
	private final Table table = new Table();
	private boolean roundIsActive = false;
	private int smallBlindPosition = 0;
	private int bigBlind = 4;
	private int raiseBy = bigBlind;
	private double blindsRaisePercentage = 0.2;

	public Game(int bigBlind, double blindsRaisePercentage, BettingPlayer... players)
	{
		this(players);
		if(bigBlind < 1)
			throw new IllegalArgumentException("Error: parameter bigBlind is less than 1.");
		if(blindsRaisePercentage < 0)
			throw new IllegalArgumentException("Error: parameter blindsRaisePercentage is less than 0.");
		this.bigBlind = bigBlind;
		this.blindsRaisePercentage = blindsRaisePercentage;
	}

	public Game(BettingPlayer... players)
	{
		this.players = new PlayerBook<BettingPlayer>(players);
	}

	public void initiateRound()
	{
		if(roundIsActive)
			throw new IllegalStateException("A new round can not be initialized while a round is still active.");
		roundIsActive = true;
		setAllPlayersInGame(true);
		table.newDeck();
		assert (table.getSizeOfDeck() == 52) : "A new deck must be generated before a new round.";
		placeBlinds();
		dealCards(2);
	}

	private void dealCards(int cards)
	{
		for(Player player : players.getPlayers())
			for(int i = 0; i < cards; i++)
				player.addTohand(table.getRandomCardFromDeck());
	}

	private void setAllPlayersInGame(boolean inGame)
	{
		for(Player player : players.getPlayers())
			player.setInRound(inGame);
	}

	private void placeBlinds()
	{
		int bigBlindPosition = smallBlindPosition + 1;
		if(bigBlindPosition >= players.getPlayers().size())
			bigBlindPosition = 0;
		placeSmallBlind();
		placeBigBlind(bigBlindPosition);
	}

	private void placeSmallBlind()
	{
		if(!(getPlayerWithSmallBlind().subtractChips(getSmallBlind())))
			pot.receiveBet(getSmallBlind(), players.getPlayers().get(smallBlindPosition));
	}

	private void placeBigBlind(int bigBlindPosition)
	{
		if(getPlayerWithBigBlind().subtractChips(getBigBlind()))
			pot.receiveBet(getBigBlind(), players.getPlayers().get(bigBlindPosition));
	}

	public BettingPlayer getPlayerWithSmallBlind()
	{
		return players.getPlayer(smallBlindPosition);
	}

	public BettingPlayer getPlayerWithBigBlind()
	{
		int bigBlindPosition = smallBlindPosition + 1;
		if(bigBlindPosition >= players.getPlayers().size())
			bigBlindPosition = 0;
		return players.getPlayer(bigBlindPosition);
	}
	
	public List<BettingPlayer> getPlayers()
	{
		return players.getPlayers();
	}

	public void raiseBlinds()
	{
		int previousBigBlind = bigBlind;
		bigBlind = (int) Math.round(bigBlind * (1 + blindsRaisePercentage));
		if(previousBigBlind == bigBlind && blindsRaisePercentage != 0)
			bigBlind++;
	}

	public void setRaiseBy(int raise)
	{
		if(raise < 1)
			throw new IllegalArgumentException("Lowest raise is by one chip");
		raiseBy = raise;
	}

	public boolean playerAction(BettingPlayer player, Action action)
	{
		if(!player.isInRound() || !roundIsActive)
			return false;

		switch(action)
		{
			case ALL_IN:
				return allIn(player);
			case CALL:
				return call(player);
			case FOLD:
				return fold(player);
			case RAISE:
				return raise(player);
			default:
				return check(player);
		}
	}

	private boolean allIn(BettingPlayer player)
	{
		if(!player.hasChips())
			return false;
		players.nextPlayer();
		return bet(player.getChips(), player);
	}

	private boolean call(BettingPlayer player)
	{
		// TODO
		/*
		 * Ta reda på senaste höjningen Kolla hur mycket player har satsat innan
		 * Satsa mellanskillnaden
		 */
		players.nextPlayer();
		return true;
	}

	private boolean check(BettingPlayer player)
	{
		players.nextPlayer();
		return true;
	}

	private boolean fold(BettingPlayer player)
	{
		if(player.isInRound() && !player.hasChips())
			return false;
		player.setInRound(false);
		players.nextPlayer();
		return true;
	}

	private boolean raise(BettingPlayer player)
	{
		if(!bet(raiseBy, player))
			return false;
		raiseBy = bigBlind;
		players.nextPlayer();
		return true;
	}

	public void endRound()
	{
		// TODO
		// Behöver kalkylera vem som vann

		roundIsActive = false;
		setAllPlayersInGame(false);
		raiseBlinds();
		moveSmallBlindPosition();
		players.clearAllHands();
		table.clearTableOfCards();
		pot.resetPot();
	}

	public void endGame()
	{
		if(roundIsActive)
			endRound();
		// Utse en vinnare av spelet och sedan restart
		blindsRaisePercentage = 0.2;
		bigBlind = 4;
		smallBlindPosition = 0;

	}

	public void distributeChip(ArrayList<ArrayList<BettingPlayer>> victoryOrder)
	{
		for(ArrayList<BettingPlayer> victoryPosition : victoryOrder)
		{
			int amountOfWinners = victoryPosition.size();
			int leftoverFromchipsWonCalculation = (pot.getBetHistory(victoryPosition.get(0)) % amountOfWinners);
			int chipsWon;
			boolean isThereLeftOvers = false;
			if(leftoverFromchipsWonCalculation != 0)
			{
				chipsWon = (pot.getBetHistory(victoryPosition.get(0)) / amountOfWinners) + 1;
				isThereLeftOvers = true;
			}
			else
			{
				chipsWon = (pot.getBetHistory(victoryPosition.get(0)) / amountOfWinners);
			}
			for(BettingPlayer winner : victoryPosition)
			{
				pot.handOutChips(winner, chipsWon, amountOfWinners);
				if(isThereLeftOvers)
				{
					if(victoryPosition.get(0) != winner)
					{
						int amountOfErrors = victoryPosition.size() - 1;
						winner.addChips(1);
						victoryPosition.get(0).subtractChips(1);

					}
				}

			}
		}
	}

	public boolean bet(int bet, BettingPlayer player)
	{
		if(player.subtractChips(bet))
		{
			pot.receiveBet(bet, player);
			return true;
		}
		return false;
	}

	public void initiateDeal()
	{
		if(table.getCardsOnTable().size() >= 5)
			throw new IllegalStateException();
		else if(table.getCardsOnTable().size() == 0)
			for(byte b = 0; b < 3; b++)
				table.putRandomCardOnTable();
		else
			table.putRandomCardOnTable();

		assert table.getCardsOnTable().size() >= 3 : "After the initiateDeal has been called, at least the flop (first three cards) must have been dealt.";
	}

	private int moveSmallBlindPosition()
	{
		smallBlindPosition++;
		if(smallBlindPosition > (players.getNumberOfPlayersInGame() - 1))
			smallBlindPosition = 0;
		return smallBlindPosition;
	}

	public int getBigBlind()
	{
		return bigBlind;
	}

	public int getSmallBlind()
	{
		return bigBlind / 2;
	}

	public double getBlindsRaisePercentage()
	{
		return blindsRaisePercentage;
	}

	public int getSmallBlindPosition()
	{
		return smallBlindPosition;
	}

	public boolean getRoundIsActive()
	{
		return roundIsActive;
	}

	public boolean addPlayer(BettingPlayer newPlayer)
	{
		return players.addPlayer(newPlayer);
	}

}
