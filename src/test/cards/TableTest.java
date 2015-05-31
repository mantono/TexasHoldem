package test.cards;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import texasholdem.BettingPlayer;
import texasholdem.Game;
import texasholdem.PlayerBook;
import cards.Card;
import cards.Colour;
import cards.Rank;
import cards.Table;

public class TableTest
{
	private Table table;

	@Before
	public void setUp() throws Exception
	{
		table = new Table();
	}

	@Test
	public void newDeckTest()
	{
		table.newDeck();
		assertEquals(52, table.getSizeOfDeck());
		table.newDeck((byte) 2);
		assertEquals(104, table.getSizeOfDeck());
	}
	
	@Test
	public void cardsOnTableTest()
	{
		assertEquals(52, table.getSizeOfDeck());
		table.putRandomCardOnTable();
		table.putRandomCardOnTable();
		List<Card> tableCards = table.getCardsOnTable();
		assertEquals(50, table.getSizeOfDeck());
		assertEquals(2, tableCards.size());
		table.clearTableOfCards();
		tableCards = table.getCardsOnTable();
		assertEquals(0, tableCards.size());	
	}
	
	@Test
	public void putExistingCardOnTableTest()
	{
		Card card = new Card(Colour.DIAMONDS, Rank.EIGHT);
		table.putCardOnTable(card);
		assertTrue(table.getCardsOnTable().contains(card));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void putNonExistingCardOnTableTest()
	{
		Card card = new Card(Colour.DIAMONDS, Rank.EIGHT);
		table.putCardOnTable(card);
		table.putCardOnTable(card);
		assertFalse(table.getCardsOnTable().contains(card));
	}
}
