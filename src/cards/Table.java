package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Table
{
	private Deck deck = new Deck();
	private final List<Card> cardsOnTable = new LinkedList<Card>();

	public void newDeck()
	{
		deck = new Deck();
	}

	public void newDeck(byte amoutOfSets)
	{
		deck = new Deck(amoutOfSets);
	}

	public void clearTableOfCards()
	{
		cardsOnTable.clear();
	}

	public void putRandomCardOnTable()
	{
		cardsOnTable.add(deck.drawCard());
	}
	
	public Card getRandomCardFromDeck()
	{
		return deck.drawCard();
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
