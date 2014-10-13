package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CardGame
{
	private Deck deck = new Deck();
	private final ArrayList<Player> playersInGame = new ArrayList<Player>();
	private final List<Card> cardsOnTable = new ArrayList<Card>();

	public CardGame(Player... players)
	{
		playersInGame.addAll(Arrays.asList(players));
	}
	
	public void newDeck()
	{
		deck = new Deck();
	}

	public void newDeck(byte amoutOfSets)
	{
		deck = new Deck(amoutOfSets);
	}

	public void clearAllHands()
	{
		for(Player player : playersInGame)
			player.clearHand();
	}

	public void dealCards(int amountOfCards)
	{
		for(Player player : playersInGame)
			player.newHand(deck.drawCards(amountOfCards));
	}

	public ArrayList<Player> getPlayersInGame()
	{
		return playersInGame;
	}
	
	public Player getPlayer(int index)
	{
		return playersInGame.get(index);
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