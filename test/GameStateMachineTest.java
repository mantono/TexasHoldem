import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import texasholdem.*;
import cards.*;


public class GameStateMachineTest
{
	private Player emil, rasmus, elliot;
	private Game game;
	
	@Before
	public void setup()
	{
		emil = new Player("emil", 100);
		rasmus = new Player("rasmus", 100);
		elliot = new Player("elliot", 100);
		game = new Game(emil, rasmus, elliot);
	}
	
	// Not in round
	
	@Test(expected=IllegalStateException.class)
	public void notInRoundToIllegalState_CheckTest()
	{
		game.playerAction(emil, Action.CHECK);
	}
	
	@Test(expected=IllegalStateException.class)
	public void notInRoundToIllegalState_AllInTest()
	{
		game.playerAction(emil, Action.ALL_IN);
	}
	
	@Test(expected=IllegalStateException.class)
	public void notInRoundToIllegalState_RaiseTest()
	{
		game.playerAction(emil, Action.RAISE);
	}
	
	@Test(expected=IllegalStateException.class)
	public void notInRoundToIllegalState_CallTest()
	{
		game.playerAction(emil, Action.CALL);
	}
	
	@Test(expected=IllegalStateException.class)
	public void notInRoundToIllegalState_FoldTest()
	{
		game.playerAction(emil, Action.FOLD);
	}
	
	@Test
	public void notInRoundToInRound_EnterRoundTest()
	{
		assertFalse(emil.isInRound());
		game.initiateRound();
		assertTrue(emil.isInRound());
	}
	
	// In round
	
	@Test
	public void inRoundToNotInRound_FoldTest()
	{
		game.initiateRound();
		game.initiateDeal();
		assertTrue(game.playerAction(rasmus, Action.FOLD));
		assertFalse(rasmus.isInRound());
	}
	
	@Test
	public void inRoundToNotInRound_RoundOverTest()
	{
		game.initiateRound();
		game.initiateDeal();
		game.initiateDeal();
		game.initiateDeal();
		game.endRound();
		assertFalse(rasmus.isInRound());
	}
	
	@Test
	public void inRoundToInRound_CallTest()
	{
		game.initiateRound();
		game.initiateDeal();
		assertTrue(rasmus.isInRound());
		assertTrue(game.playerAction(rasmus, Action.CALL));
		assertTrue(rasmus.isInRound());
	}
	
	@Test
	public void inRoundToInRound_CheckTest()
	{
		game.initiateRound();
		game.initiateDeal();
		assertTrue(elliot.isInRound());
		assertTrue(game.playerAction(elliot, Action.CHECK));
		assertTrue(elliot.isInRound());
	}
	
	@Test
	public void inRoundToInRound_RaiseTest()
	{
		game.initiateRound();
		game.initiateDeal();
		assertTrue(elliot.isInRound());
		assertTrue(game.playerAction(elliot, Action.RAISE));
		assertTrue(elliot.isInRound());
	}
	
	@Test
	public void inRoundToZeroChips_AllInTest()
	{
		game.initiateRound();
		game.initiateDeal();
		assertTrue(elliot.isInRound());
		assertTrue(game.playerAction(elliot, Action.ALL_IN));
		assertTrue(elliot.isInRound());
		assertEquals(0, elliot.getChips());
	}
	
	//zero chips
	@Test(expected=IllegalStateException.class)
	public void zeroChipsToIllegalState_call(){
		game.initiateRound();
		game.initiateDeal();
		game.playerAction(elliot, Action.ALL_IN);
		game.playerAction(elliot, Action.CALL);
	}
	
	
	@Test(expected=IllegalStateException.class)
	public void zeroChipsToIllegalState_allIn(){
		game.initiateRound();
		game.initiateDeal();
		game.playerAction(elliot, Action.ALL_IN);
		game.playerAction(elliot, Action.ALL_IN);
	}
	
	@Test(expected=IllegalStateException.class)
	public void zeroChipsToIllegalState_fold(){
		game.initiateRound();
		game.initiateDeal();
		game.playerAction(elliot, Action.ALL_IN);
		game.playerAction(elliot, Action.FOLD);
	}
	
	@Test(expected=IllegalStateException.class)
	public void zeroChipsToIllegalState_raise(){
		game.initiateRound();
		game.initiateDeal();
		game.playerAction(elliot, Action.ALL_IN);
		game.playerAction(elliot, Action.RAISE);
	}
	
	@Test(expected=IllegalStateException.class)
	public void zeroChipsToIllegalState_check(){
		game.initiateRound();
		game.initiateDeal();
		game.playerAction(elliot, Action.ALL_IN);
		game.playerAction(elliot, Action.CHECK);
	}
	
	@Test
	public void zeroChipsToNotInRound_wonRound(){
		game.initiateRound();
		game.initiateDeal();
		game.playerAction(elliot, Action.ALL_IN);
		game.playerAction(emil, Action.FOLD);
		game.playerAction(rasmus, Action.FOLD);
		game.endRound();
		assertFalse(elliot.isInRound());
	}
	
	@Test
	public void zeroChipsToNotInGame_lostRound(){
		game.initiateRound();
		game.initiateDeal();
		game.playerAction(elliot, Action.ALL_IN);
		game.playerAction(emil, Action.ALL_IN);
		game.playerAction(rasmus, Action.FOLD);
		elliot.clearHand();
		emil.clearHand();
		elliot.newHand(new Card(Colour.CLUBS, Rank.FIVE), new Card(Colour.SPADES, Rank.EIGHT));
		emil.newHand(new Card(Colour.DIAMONDS, Rank.FIVE), new Card(Colour.DIAMONDS, Rank.ACE));
		game.putCardOnTable(new Card(Colour.DIAMONDS, Rank.ACE));
		game.putCardOnTable(new Card(Colour.DIAMONDS, Rank.KING));
		game.putCardOnTable(new Card(Colour.DIAMONDS, Rank.FOUR));
		game.putCardOnTable(new Card(Colour.CLUBS, Rank.ACE));
		game.putCardOnTable(new Card(Colour.SPADES, Rank.ACE));
		game.endRound();
		assertFalse(game.getPlayers().contains(elliot));
	}
	
	
}
