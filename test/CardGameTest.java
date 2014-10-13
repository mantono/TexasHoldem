import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import texasholdem.Game;
import cards.Card;
import cards.Colour;
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
		assertTrue(newGame.getPlayersInGame().contains(kalle));
	}

	@Test
	public void twoPlayersTest()
	{
		Game newGame = new Game(kalle, pelle);
		assertTrue(newGame.getPlayersInGame().contains(kalle));
		assertTrue(newGame.getPlayersInGame().contains(pelle));
		assertEquals(2, newGame.getPlayersInGame().size());
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
		game.putCardOnTable();
		game.putCardOnTable();
		List<Card> tableCards = game.getCardsOnTable();
		assertEquals(50, game.getSizeOfDeck());
		assertEquals(2, tableCards.size());
		game.clearTableOfCards();
		tableCards = game.getCardsOnTable();
		assertEquals(0, tableCards.size());	
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

}