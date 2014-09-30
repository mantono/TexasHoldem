import static org.junit.Assert.*;

import java.util.IllegalFormatException;

import org.junit.Test;

import cards.Deck;

import texasholdem.Pot;

public class PotTest {

	@Test
	public void PotSizeTest() {
		Pot pot = new Pot(500);
		assertEquals(500, pot.getSize());

	}

	@Test
	public void BetSizeTest() {

		Pot betToPot = new Pot(1);
		assertEquals(301, betToPot.betToPot(300, true));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testForInvalidSize() {
		Pot invalidpot = new Pot(0);
		assertEquals(0, invalidpot.getSize());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testForInvalidBet() {
		Pot invalidBet = new Pot(0, false);
		assertEquals(0, invalidBet.betToPot(0, false));
	}

}
