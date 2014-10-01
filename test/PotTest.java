import static org.junit.Assert.*;

import org.junit.Test;

import texasholdem.Pot;

public class PotTest {
	
		@Test
		public void NewPotTest(){
			Pot newPot = new Pot();
			assertEquals(0, newPot.getSize());
			assertEquals(0, newPot.getBet());
		}

	@Test
	public void testPotSizeIfPositiveInt() {
		Pot pot = new Pot(500);
		assertEquals(500, pot.getSize());

	}
	@Test(expected = IllegalArgumentException.class)
	public void testForInvalidSize() {
		Pot invalidPot = new Pot(-1);
		assertEquals(-1, invalidPot.getSize());
	}
	
	@Test
	public void betSizeTestFromEmptyPot(){
		Pot emptyPot = new Pot();
		emptyPot.betToPot(300);
		assertEquals(300, emptyPot.getSize());
		assertEquals(300, emptyPot.getBet());
	}
	
	@Test
	public void betSizeTestFromPositiveSize() {

		Pot positivePot = new Pot(1);
		positivePot.betToPot(300);
		assertEquals(301, positivePot.getSize());
		assertEquals(300, positivePot.getBet());

	}



	@Test(expected = IllegalArgumentException.class)
	public void testForInvalidBet() {
		Pot invalidBet = new Pot(0);
		invalidBet.betToPot(0);
		
	}
	
	@Test
	public void testForNewBetOverrideAndMultipleBets(){
		Pot newPot = new Pot();
		newPot.betToPot(300);
		newPot.betToPot(200);
		assertEquals(500, newPot.getSize());
		assertEquals(200, newPot.getBet());
	}
	
	@Test
	public void resetPotTest(){
		Pot newPot = new Pot(500);
		newPot.betToPot(200);
		newPot.resetPot();
		assertEquals(0, newPot.getSize());
		assertEquals(0, newPot.getBet());
	}
}
