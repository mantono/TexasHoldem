package test.texasholdem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cards.Card;
import cards.Colour;
import cards.Rank;
import cards.Table;
import texasholdem.BettingPlayer;
import texasholdem.Game;
import texasholdem.PlayerBook;

public class PlayerBookTest
{
	private BettingPlayer kalle, pelle, kent;
	private PlayerBook<BettingPlayer> players;

	@Before
	public void setUp() throws Exception
	{
		kalle = new BettingPlayer("kalle", 200);
		pelle = new BettingPlayer("pelle", 200);
		kent = new BettingPlayer("kent", 200);
		players = new PlayerBook<BettingPlayer>(kalle, pelle, kent);
	}

	@Test
	public void testGetPlayer()
	{
		assertEquals(kalle, players.getPlayer(0));
		assertEquals(pelle, players.getPlayer(1));
	}

	@Test
	public void onePlayerTest()
	{
		final PlayerBook<BettingPlayer> players = new PlayerBook<BettingPlayer>(kalle);
		assertTrue(players.getPlayers().contains(kalle));
	}

	@Test
	public void twoPlayersTest()
	{
		final PlayerBook<BettingPlayer> players = new PlayerBook<BettingPlayer>(kalle, pelle);
		assertTrue(players.getPlayers().contains(kalle));
		assertTrue(players.getPlayers().contains(pelle));
		assertEquals(2, players.getPlayers().size());
	}

	@Test
	public void clearAllHandsTest()
	{
		final PlayerBook<BettingPlayer> players = new PlayerBook<BettingPlayer>(kalle, pelle);
		kalle.newHand(new Card(Colour.CLUBS, Rank.THREE), new Card(Colour.CLUBS, Rank.FOUR));
		kalle.newHand(new Card(Colour.CLUBS, Rank.SIX), new Card(Colour.CLUBS, Rank.FIVE));
		players.clearAllHands();
		assertEquals(0, kalle.getNumberOfCards());
		assertEquals(0, pelle.getNumberOfCards());
	}

	@Test
	public void getNumberOfPlayersInGameTest()
	{
		final PlayerBook<BettingPlayer> players = new PlayerBook<BettingPlayer>(kalle, pelle);
		assertEquals(0, players.getNumberOfPlayersInGame());
		kalle.setInRound(true);
		pelle.setInRound(true);
		assertEquals(2, players.getNumberOfPlayersInGame());

	}

	@Test
	public void getCurrentPlayerWithPlayersTest()
	{
		final PlayerBook<BettingPlayer> players = new PlayerBook<BettingPlayer>();
		players.addPlayer(kalle);
		players.addPlayer(pelle);
		players.addPlayer(kent);
		assertEquals(kalle, players.getCurrentPlayer());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getCurrentPlayerWithNoPLayersTest()
	{
		final PlayerBook<BettingPlayer> players = new PlayerBook<BettingPlayer>();
		assertEquals(kalle, players.getCurrentPlayer());
	}

	@Test
	public void removeValidPlayerTest()
	{
		final PlayerBook<BettingPlayer> players = new PlayerBook<BettingPlayer>();
		assertTrue(players.addPlayer(pelle));
		assertTrue(players.removePlayer(pelle));
		assertEquals(0, players.getNumberOfPlayersInGame());
	}

	@Test
	public void removeInvalidPlayerTest()
	{
		final PlayerBook<BettingPlayer> players = new PlayerBook<BettingPlayer>();
		assertFalse(players.removePlayer(pelle));
	}

}
