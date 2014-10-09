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
	public void addingPlayerTest(){
		Game newGame = new Game(4, 0.3, kalle, pelle);
		assertTrue(newGame.getPlayersInGame().contains(kalle));
		assertTrue(newGame.getPlayersInGame().contains(pelle));
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
	}
	
	@Test
	public void initiateRoundTest(){
		Game newGame = new Game(4, 0.3, kalle, pelle, kent);
		newGame.initiateRound();
		assertTrue(newGame.getCurrentDeck() == null);
		assertEquals(198, kalle.getChips());
		assertEquals(196, pelle.getChips());
		assertEquals(200, kent.getChips());
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
	
	@Test (expected = NullPointerException.class)
	public void initiateDealWithNoDeckTest(){
		Game newGame = new Game(kalle);
		newGame.initiateDeal();
	}
	
	@Test
	public void endRoundTest(){
		Game game = new Game(4, 1, kalle, pelle, kent);
		game.initiateRound();
		game.endRound();
		assertEquals(null, game.getCurrentDeck());
		assertEquals(0, kalle.getHand().size());
		assertEquals(0, pelle.getHand().size());
		assertEquals(0, kent.getHand().size());
		assertEquals(0, game.getCardsOnTable().size());
		assertEquals(8, game.getBigBlind());
		assertEquals(4, game.getSmallBlind());
		assertEquals(1, game.getSmallBlindPosition());
	}
	
}
