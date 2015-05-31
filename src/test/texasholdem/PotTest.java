package test.texasholdem;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cards.Player;
import texasholdem.BettingPlayer;
import texasholdem.Pot;

public class PotTest {
	
	private BettingPlayer player1, player2, player3, player4;

	@Before
	public void setup() {
		player1 = new BettingPlayer("kalle", 0);
		player2 = new BettingPlayer("pelle", 0);
		player3 = new BettingPlayer("kent", 0);
		player4 = new BettingPlayer("karl", 0);
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
		emptyPot.receiveBet(300,player1);
		assertEquals(300, emptyPot.getAmount());		
	}
	
	@Test
	public void betSizeTestFromPositiveSize() {
		Pot positivePot = new Pot(1);
		positivePot.receiveBet(300,player1);
		assertEquals(301, positivePot.getAmount());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testForInvalidBet() {	
		Pot invalidBet = new Pot(0);
		invalidBet.receiveBet(0,player1);	
	}
	
	@Test
	public void testForNewBetOverrideAndMultipleBets(){
	
		Pot newPot = new Pot();
		newPot.receiveBet(300,player1);
		newPot.receiveBet(200,player1);
		assertEquals(500, newPot.getAmount());
	}
	
	@Test
	public void testBetHistoryMapAfterOneBet(){
		
		Pot newPot = new Pot();
		newPot.receiveBet(300,player1);
		assertEquals(300,newPot.getBetHistory(player1));
	}
	
	@Test
	public void testBetHistoryMapAfterMultipleBets(){
		
		Pot newPot = new Pot();
		newPot.receiveBet(300,player1);
		newPot.receiveBet(300,player1);
		assertEquals(600,newPot.getBetHistory(player1));
	}
	
	@Test
	public void resetPotTest(){
		
		Pot newPot = new Pot(500);
		newPot.receiveBet(200,player1);
		newPot.resetPot();
		assertEquals(0, newPot.getAmount());
		assertEquals(0, newPot.getBetHistory(player1));

	}
	
	@Test
	public void handOutChipsTest(){
		Pot newPot = new Pot(0);
		player1.addChips(200);
		player2.addChips(200);
		player3.addChips(200);
		player4.addChips(200);
		newPot.receiveBet(50, player1);
		newPot.receiveBet(15, player2);		
		newPot.receiveBet(200, player3);
		newPot.receiveBet(50, player4);
		player1.subtractChips(50);
		player2.subtractChips(15);
		player3.subtractChips(200);
		player4.subtractChips(50);
		newPot.handOutChips(player1, 50, 2);
		newPot.handOutChips(player4, 50, 1);
		assertEquals(100, newPot.getAmount());
		assertEquals(258, player1.getChips());
		assertEquals(257, player4.getChips());
		assertEquals(0, newPot.getBetHistory(player2));
		assertEquals(100, newPot.getBetHistory(player3));
	}
	@Test(expected=IllegalArgumentException.class)
	public void handOutChipsPlayerHaveNotBetAnythingTest(){
		Pot newPot = new Pot(0);
		newPot.handOutChips(player1, 2, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void handOutChipsNoWinnersTest(){
		Pot newPot = new Pot(0);
		player1.addChips(2);
		newPot.receiveBet(2, player1);
		newPot.handOutChips(player1, 2, 0);
	}
	
}
