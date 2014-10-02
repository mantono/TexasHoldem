import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import texasholdem.Player;
import texasholdem.Pot;

public class PotTest {
	
	private Player player;

	@Before
	public void setup() {
		player = new Player("kalle", 0);
	}
	
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
		
		Pot emptyPot = new Pot();
		emptyPot.betToPot(300,player);
		assertEquals(300, emptyPot.getAmount());
		
	}
	
	@Test
	public void betSizeTestFromPositiveSize() {
		
		Pot positivePot = new Pot(1);
		positivePot.betToPot(300,player);
		assertEquals(301, positivePot.getAmount());


	}



	@Test(expected = IllegalArgumentException.class)
	public void testForInvalidBet() {
		
		Pot invalidBet = new Pot(0);
		invalidBet.betToPot(0,player);
		
	}
	
	@Test
	public void testForNewBetOverrideAndMultipleBets(){
	
		Pot newPot = new Pot();
		newPot.betToPot(300,player);
		newPot.betToPot(200,player);
		assertEquals(500, newPot.getAmount());
	}
	
	@Test
	public void testBetHistoryMapAfterOneBet(){
		
		Pot newPot = new Pot();
		newPot.betToPot(300,player);
		assertEquals(300,newPot.getBetHistory(player));
	}
	
	@Test
	public void testBetHistoryMapAfterMultipleBets(){
		
		Pot newPot = new Pot();
		newPot.betToPot(300,player);
		newPot.betToPot(300,player);
		assertEquals(600,newPot.getBetHistory(player));
	}
	
	@Test
	public void resetPotTest(){
		
		Pot newPot = new Pot(500);
		newPot.betToPot(200,player);
		newPot.resetPot();
		assertEquals(0, newPot.getAmount());
		assertEquals(0, newPot.getBetHistory(player));

	}
}
