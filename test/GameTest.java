import static org.junit.Assert.*;
import texasholdem.*;

import org.junit.Before;
import org.junit.Test;

import cards.*;


public class GameTest {
	
	private Player kalle, pelle, kent;
	private Game defaultGame;
	
	@Before
	public void setup(){
		kalle = new Player("kalle", 200);
		pelle = new Player("pelle", 200);
		kent = new Player("kent", 200);
		defaultGame = new Game(kalle, pelle, kent);
	}

	@Test
	public void assertBlindsInNewGameTest(){
		Game newGame = new Game(4, 0.3, kalle, pelle);
		assertEquals(2, newGame.getSmallBlind());
		assertEquals(4, newGame.getBigBlind());
		assertEquals(0.3, newGame.getBlindsRaisePercentage(), 0.0001);
	}
	
	@Test

	public void addingPlayersTest(){
		Game newGame = new Game(4, 0.3, kalle, pelle);
		assertTrue(newGame.getPlayers().contains(kalle));
		assertTrue(newGame.getPlayers().contains(pelle));
	}
	
	
	@Test
	public void addingOnePlayerTest(){
		Game newGame = new Game(4, 0.3, kalle);
		assertTrue(newGame.getPlayers().contains(kalle));
	}
	
	@Test
	public void addingNoPlayerTest(){
		Game newGame = new Game(4, 0.3);
		assertEquals(0, newGame.getPlayers().size());
	}
	
	@Test
	public void playersWithBlindTest(){
		assertEquals(kalle, defaultGame.getPlayerWithSmallBlind());
		assertEquals(pelle, defaultGame.getPlayerWithBigBlind());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addingNegativeBigBlindTest(){
		Game newGame = new Game(-4, 0.3, kalle);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addingNegativeBlindsRaiserPercentageTest(){
		Game newGame = new Game(4, -0.2, kalle);
	}
	
	
	@Test
	public void raiseofBlindsTest(){
		Game game = new Game(4, 1, kalle, pelle);
		game.raiseBlinds();
		assertEquals(8, game.getBigBlind());
		assertEquals(4, game.getSmallBlind());
		Game gameWithSmallRaise = new Game(5, 0.05, kalle, pelle);
		gameWithSmallRaise.raiseBlinds();
		assertEquals(6, gameWithSmallRaise.getBigBlind());
		assertEquals(3, gameWithSmallRaise.getSmallBlind());
		
		Game gameWithNoRaise = new Game(8, 0, kalle, pelle);
		gameWithNoRaise.raiseBlinds();
		assertEquals(8, gameWithNoRaise.getBigBlind());
		assertEquals(4, gameWithNoRaise.getSmallBlind());
		
	}
	
	@Test
	public void initiateRoundAndPlaceBlindsTest(){
		Game newGame = new Game(4, 0.3, kalle, pelle, kent);
		newGame.initiateRound();
		assertEquals(4, newGame.getBigBlind());
		assertEquals(2, newGame.getSmallBlind());
		assertEquals(198, kalle.getChips());//Small blind
		assertEquals(196, pelle.getChips());//Big blind
		assertEquals(200, kent.getChips());
		newGame.endRound();
		
		newGame.initiateRound();
		assertEquals(5, newGame.getBigBlind());
		assertEquals(2, newGame.getSmallBlind());
		assertEquals(198, kalle.getChips());
		assertEquals(194, pelle.getChips());//Small blind
		assertEquals(195, kent.getChips());//Big blind
		newGame.endRound();
		
		newGame.initiateRound();
		assertEquals(7, newGame.getBigBlind());
		assertEquals(3, newGame.getSmallBlind());
		assertEquals(191, kalle.getChips());//Big blind
		assertEquals(194, pelle.getChips());
		assertEquals(192, kent.getChips());//Small blind
		newGame.endRound();
	}
	
	@Test (expected = IllegalStateException.class)
	public void initiateMultipleRoundTest(){
		Game newGame = new Game(4, 0.3, kalle, pelle, kent);
		newGame.initiateRound();
		newGame.initiateRound();
	}

	
	@Test
	public void initiateDealTest(){
		Game newGame = new Game(4, 0.3, kalle, pelle, kent);
		newGame.newDeck();
		assertEquals(0, newGame.getCardsOnTable().size());
		newGame.initiateDeal();
		assertEquals(3, newGame.getCardsOnTable().size());
		newGame.initiateDeal();
		assertEquals(4, newGame.getCardsOnTable().size());
		newGame.initiateDeal();
		assertEquals(5, newGame.getCardsOnTable().size());
	}
	
	@Test (expected = IllegalStateException.class)
	public void initiateDealFiveCardsOnTest(){
		Game newGame = new Game(4, 0.3, kalle, pelle, kent);
		newGame.newDeck();
		newGame.initiateDeal();
		newGame.initiateDeal();
		newGame.initiateDeal();
		newGame.initiateDeal();
	}
	
	@Test
	public void endRoundTest(){
		Game game = new Game(4, 1, kalle, pelle, kent);
		game.initiateRound();
		game.endRound();
		assertEquals(0, kalle.getNumberOfCards());
		assertEquals(0, pelle.getNumberOfCards());
		assertEquals(0, kent.getNumberOfCards());
		assertEquals(0, game.getCardsOnTable().size());
		assertEquals(8, game.getBigBlind());
		assertEquals(4, game.getSmallBlind());
		assertEquals(1, game.getSmallBlindPosition());
	}
	@Test
	public void endGameTest(){
		Game game = new Game(4, 1, kalle, pelle, kent);
		game.initiateRound();
		game.endGame();
		assertEquals(0, kalle.getNumberOfCards());
		assertEquals(0, pelle.getNumberOfCards());
		assertEquals(0, kent.getNumberOfCards());
	}
	
	@Test
	public void testPlayersSetInGame(){
		kalle.setInGame(false);
		pelle.setInGame(false);
		kent.setInGame(false);
		assertFalse(kalle.isInGame());
		assertFalse(pelle.isInGame());
		assertFalse(kent.isInGame());
		defaultGame.initiateRound();
		assertTrue(kalle.isInGame());
		assertTrue(pelle.isInGame());
		assertTrue(kent.isInGame());
	}
	
}
