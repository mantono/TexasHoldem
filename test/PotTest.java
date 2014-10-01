import static org.junit.Assert.*;

import java.util.IllegalFormatException;

import org.junit.Test;

import cards.Deck;

import texasholdem.Pot;

public class PotTest {

	@Test
	public void TestPotSizeIfPositiveInt() {
		Pot pot = new Pot(500);
		assertEquals(500, pot.getSize());

	}
		@Test(expected = IllegalArgumentException.class)
	public void testForInvalidSize() {
		Pot invalidpot = new Pot(0);
		assertEquals(0, invalidpot.getSize());
	}

	@Test
	public void BetSizeTest() {

		Pot Pot = new Pot(1);
		Pot.betToPot(300);
		assertEquals(301, Pot.getSize());
		assertEquals(300, Pot.getBet());

	}



	@Test(expected = IllegalArgumentException.class)
	public void testForInvalidBet() {
		Pot invalidBet = new Pot(0);
		invalidBet.betToPot(0);
		
	}

}
