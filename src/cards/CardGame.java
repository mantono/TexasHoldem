package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CardGame
{
	private Deck deck = new Deck();
	private final ArrayList<Player> players = new ArrayList<Player>();
	private final List<Card> cardsOnTable = new ArrayList<Card>();
	private int currentPlayer = 0;

	public CardGame(Player... players)
	{
		this.players.addAll(Arrays.asList(players));
		if(this.players.contains(null))
			throw new NullPointerException("A player variable is pointing to null.");
	}
	
	public void newDeck()
	{
		deck = new Deck();
	}

	public void newDeck(byte amoutOfSets)
	{
		deck = new Deck(amoutOfSets);
	}
	
	public boolean addPlayer(Player player)
	{
		if(player == null)
			throw new NullPointerException("A player variable is pointing to null.");
		return players.add(player);
	}
	
	public boolean removePlayer(Player player)
	{
		return players.remove(player);
	}

	public void clearAllHands()
	{
		for(Player player : players)
			player.clearHand();
	}

	public void dealCards(int amountOfCards)
	{
		for(Player player : players)
			player.newHand(deck.drawCards(amountOfCards));
	}

	public ArrayList<Player> getPlayers()
	{
		return players;
	}
	
	public Player getPlayer(int index)
	{
		return players.get(index);
	}
	
	public Player getCurrentPlayer()
	{
		return players.get(currentPlayer);
	}
	
	public int nextPlayer()
	{
		currentPlayer++;
		if(currentPlayer >= players.size())
			currentPlayer = 0;
		return currentPlayer;
	}
	
	public int getNumberOfPlayersInGame()
	{
		int numberOfPlayers = 0;
		for(Player player : players)
			if(player.isInRound())
				numberOfPlayers++;
		return numberOfPlayers;
	}

	public void clearTableOfCards()
	{
		cardsOnTable.clear();
	}

	public void putRandomCardOnTable()
	{
		cardsOnTable.add(deck.drawCard());
	}
	
	public void putCardOnTable(Card card)
	{
		if(deck.removeCard(card))
			cardsOnTable.add(card);
	}
	
	public List<Card> getCardsOnTable()
	{
		return cardsOnTable;
	}
	
	public int getSizeOfDeck()
	{
		return deck.getSize();
	}
	
}
