package test.texasholdem;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import texasholdem.BettingPlayer;
import texasholdem.Pot;
import cards.Card;
import cards.Colour;
import cards.Hand;
import cards.Rank;


public class BettingPlayerTest
{

	private BettingPlayer player;

	@Before
	public void setup() {
		player = new BettingPlayer("kalle", 0);
	}

	@Test
	public void potTestIfIsZero() {

		assertEquals(0, player.getChips());

	}

	@Test
	public void addChipsTest() {

		player.addChips(200);
		assertEquals(200, player.getChips());

	}

	@Test
	public void addToPotTestSuccess() {
		Pot p = new Pot();
		player.addChips(200);
		assertTrue(player.subtractChips(200));
		player.addChips(100);
		assertTrue(player.subtractChips(1));
	}
	@Test
	public void add0ChipsToPotTest() {
		Pot p = new Pot();
		player.addChips(200);
		assertFalse(player.subtractChips(0));

	}

	@Test
	public void addToPotTestFail() {
		Pot p = new Pot();
		player.addChips(200);
		assertFalse(player.subtractChips(201));

	}
	
	@Test
	public void isAllInTest()
	{
		player.setInRound(true);
		assertTrue(player.isInRound());
		assertTrue(!player.hasChips());
		assertTrue(player.isAllIn());
	}
	
	@Test
	public void isAllInTestNotInRound()
	{
		player.setInRound(false);
		assertFalse(player.isInRound());
		assertTrue(!player.hasChips());
		assertFalse(player.isAllIn());
	}
	
	@Test
	public void isAllInTestHasChips()
	{
		BettingPlayer player = new BettingPlayer("Henrik", 200);
		player.setInRound(true);
		assertTrue(player.isInRound());
		assertFalse(!player.hasChips());
		assertFalse(player.isAllIn());
	}
	
	@Test(expected = NullPointerException.class)
	public void subtractNullChipsTest(){
		@SuppressWarnings("null")
		int chips = (Integer) null;
		player.subtractChips(chips);
	}
}
