import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import texasholdem.Game;
import cards.Card;
import cards.Colour;
import cards.Deck;
import cards.Player;
import cards.Rank;

public class CardGameTest
{
	private Player kalle, pelle, kent;
	private Game game;

	@Before
	public void setUp() throws Exception
	{
		kalle = new Player("kalle", 200);
		pelle = new Player("pelle", 200);
		kent = new Player("kent", 200);
		game = new Game();
	}

	@Test
	public void onePlayerTest()
	{
		Game newGame = new Game(kalle);
		assertTrue(newGame.getPlayers().contains(kalle));
	}

	@Test
	public void twoPlayersTest()
	{
		Game newGame = new Game(kalle, pelle);
		assertTrue(newGame.getPlayers().contains(kalle));
		assertTrue(newGame.getPlayers().contains(pelle));
		assertEquals(2, newGame.getPlayers().size());
	}

	@Test
	public void clearAllHandsTest()
	{
		Game newGame = new Game(kalle, pelle);
		kalle.newHand(new Card(Colour.CLUBS, Rank.THREE), new Card(Colour.CLUBS, Rank.FOUR));
		kalle.newHand(new Card(Colour.CLUBS, Rank.SIX), new Card(Colour.CLUBS, Rank.FIVE));
		newGame.clearAllHands();
		assertEquals(0, kalle.getNumberOfCards());
		assertEquals(0, pelle.getNumberOfCards());
	}

	@Test
	public void newDeckTest()
	{
		Game newGame = new Game(kalle);
		newGame.newDeck();
		assertEquals(52, newGame.getSizeOfDeck());
		newGame.newDeck((byte) 2);
		assertEquals(104, newGame.getSizeOfDeck());
	}

	@Test
	public void testGetPlayer()
	{
		Game game = new Game(kalle, pelle, kent);
		assertEquals(kalle, game.getPlayer(0));
		assertEquals(pelle, game.getPlayer(1));
	}
	
	@Test
	public void cardsOnTableTest()
	{
		assertEquals(52, game.getSizeOfDeck());
		game.putRandomCardOnTable();
		game.putRandomCardOnTable();
		List<Card> tableCards = game.getCardsOnTable();
		assertEquals(50, game.getSizeOfDeck());
		assertEquals(2, tableCards.size());
		game.clearTableOfCards();
		tableCards = game.getCardsOnTable();
		assertEquals(0, tableCards.size());	
	}
	
	@Test
	public void putExistingCardOnTableTest()
	{
		Card card = new Card(Colour.DIAMONDS, Rank.EIGHT);
		game.putCardOnTable(card);
		assertTrue(game.getCardsOnTable().contains(card));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void putNonExistingCardOnTableTest()
	{
		Card card = new Card(Colour.DIAMONDS, Rank.EIGHT);
		game.putCardOnTable(card);
		game.putCardOnTable(card);
		assertFalse(game.getCardsOnTable().contains(card));
	}
	
	@Test
	public void dealCardsTest()
	{
		game = new Game(pelle, kalle, kent);
		game.dealCards(2);
		assertEquals(2, pelle.getNumberOfCards());
		assertEquals(2, kalle.getNumberOfCards());
		assertEquals(2, kent.getNumberOfCards());
	}
	
	@Test (expected = NegativeArraySizeException.class)
	public void dealCardsNegativeSizeTest()
	{
		game = new Game(pelle, kalle, kent);
		game.dealCards(-1);
	}
	
	@Test
	public void getNumberOfPlayersInGameTest()
	{
		game.addPlayer(kalle);
		game.addPlayer(pelle);
		assertEquals(0, game.getNumberOfPlayersInGame());
		kalle.setInRound(true);
		pelle.setInRound(true);
		assertEquals(2, game.getNumberOfPlayersInGame());
		
	}
	
	@Test
	public void getCurrentPlayerWithPlayersTest()
	{
		game.addPlayer(kalle);
		game.addPlayer(pelle);
		game.addPlayer(kent);
		assertEquals(kalle, game.getCurrentPlayer());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getCurrentPlayerWithNoPLayersTest()
	{
		assertEquals(kalle, game.getCurrentPlayer());
	}
	
	@Test
	public void removeValidPlayerTest()
	{
		assertTrue(game.addPlayer(pelle));
		assertTrue(game.removePlayer(pelle));
		assertEquals(0, game.getNumberOfPlayersInGame());
	}
	
	@Test
	public void removeInvalidPlayerTest()
	{
		assertFalse(game.removePlayer(pelle));
	}

}
