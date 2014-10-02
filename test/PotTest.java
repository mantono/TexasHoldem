import static org.junit.Assert.*;

import org.junit.Test;

import texasholdem.Player;
import texasholdem.Pot;

public class PotTest {
	
		@Test
		public void NewPotTest(){
			Pot newPot = new Pot();
			assertEquals(0, newPot.getAmount());
			
		}

	@Test
	public void testPotSizeIfPositiveInt() {
		Pot pot = new Pot(500);
		assertEquals(500, pot.getAmount());

	}
	@Test(expected = IllegalArgumentException.class)
	public void testForInvalidSize() {
		Pot invalidPot = new Pot(-1);
		assertEquals(-1, invalidPot.getAmount());
	}
	
	@Test
	public void betSizeTestFromEmptyPot(){
		Player p = new Player("kalle");
		Pot emptyPot = new Pot();
		emptyPot.betToPot(300,p);
		assertEquals(300, emptyPot.getAmount());
		
	}
	
	@Test
	public void betSizeTestFromPositiveSize() {
		Player p = new Player("kalle");
		Pot positivePot = new Pot(1);
		positivePot.betToPot(300,p);
		assertEquals(301, positivePot.getAmount());


	}



	@Test(expected = IllegalArgumentException.class)
	public void testForInvalidBet() {
		Player p = new Player("kalle");
		Pot invalidBet = new Pot(0);
		invalidBet.betToPot(0,p);
		
	}
	
	@Test
	public void testForNewBetOverrideAndMultipleBets(){
		Player p = new Player("kalle");
		Pot newPot = new Pot();
		newPot.betToPot(300,p);
		newPot.betToPot(200,p);
		assertEquals(500, newPot.getAmount());
	}
	
	@Test
	public void testBetHistoryMapAfterOneBet(){
		Player p = new Player("kalle");
		Pot newPot = new Pot();
		newPot.betToPot(300,p);
		assertEquals(300,newPot.getBetHistory(p));
	}
	
	@Test
	public void testBetHistoryMapAfterMultipleBets(){
		Player p = new Player("kalle");
		Pot newPot = new Pot();
		newPot.betToPot(300,p);
		newPot.betToPot(300,p);
		assertEquals(600,newPot.getBetHistory(p));
	}
	
	@Test
	public void resetPotTest(){
		Player p = new Player("kalle");
		Pot newPot = new Pot(500);
		newPot.betToPot(200,p);
		newPot.resetPot();
		assertEquals(0, newPot.getAmount());
		assertEquals(0, newPot.containsKey(p));

	}
}
