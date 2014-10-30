package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CardGame<T extends Player>
{
	private Deck deck = new Deck();
	private final ArrayList<T> players = new ArrayList<T>();
	private final List<Card> cardsOnTable = new ArrayList<Card>();
	private int currentPlayer = 0;

	public CardGame(T... players)
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
	
	public boolean addPlayer(T player)
	{
		if(player == null)
			throw new NullPointerException("A player variable is pointing to null.");
		return players.add(player);
	}
	
	public boolean removePlayer(T player)
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

	public ArrayList<T> getPlayers()
	{
		return players;
	}
	
	public T getPlayer(int index)
	{
		return players.get(index);
	}
	
	public T getCurrentPlayer()
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
