package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CardGame
{
	private Deck deck = new Deck();
	private final ArrayList<Player> players = new ArrayList<Player>();
	private final List<Card> cardsOnTable = new ArrayList<Card>();

	public CardGame(Player... players)
	{
		this.players.addAll(Arrays.asList(players));
	}
	
	public void newDeck()
	{
		deck = new Deck();
	}

	public void newDeck(byte amoutOfSets)
	{
		deck = new Deck(amoutOfSets);
	}
	
	public void addPlayer(Player player)
	{
		players.add(player);
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
	
	public int getNumberOfPlayersInGame()
	{
		int numberOfPlayers = 0;
		for(Player player : players)
			if(player.isInGame())
				numberOfPlayers++;
		return numberOfPlayers;
	}

	public void clearTableOfCards()
	{
		cardsOnTable.clear();
	}

	public void putCardOnTable()
	{
		cardsOnTable.add(deck.drawCard());
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
