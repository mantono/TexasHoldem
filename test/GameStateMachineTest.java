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
	public void inRoundToNotInRound_CallTest()
	{
		game.initiateRound();
		game.initiateDeal();
		assertTrue(rasmus.isInRound());
		assertTrue(game.playerAction(rasmus, Action.CALL));
		assertTrue(rasmus.isInRound());
	}
	
	@Test
	public void inRoundToNotInRound_CheckTest()
	{
		game.initiateRound();
		game.initiateDeal();
		assertTrue(rasmus.isInRound());
		assertTrue(game.playerAction(rasmus, Action.CALL));
		assertTrue(rasmus.isInRound());
	}
}
