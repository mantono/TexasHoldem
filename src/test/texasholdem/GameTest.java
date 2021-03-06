package test.texasholdem;
import static org.junit.Assert.*;

import java.util.ArrayList;

import texasholdem.*;

import org.junit.Before;
import org.junit.Test;

import cards.*;


public class GameTest {
	
	private BettingPlayer kalle, pelle, kent, joe, quinn, martin, jim;
	private Game defaultGame;
	
	@Before
	public void setup(){
		kalle = new BettingPlayer("kalle", 200);
		pelle = new BettingPlayer("pelle", 200);
		kent = new BettingPlayer("kent", 200);
		joe = new BettingPlayer("joe", 200);
		quinn = new BettingPlayer("quinn", 200);
		martin = new BettingPlayer("martin", 200);
		jim = new BettingPlayer("jim", 200);
		defaultGame = new Game(kalle, pelle, kent, joe, quinn, martin, jim);
	}

	@Test
	public void assertBlindsAndPlayersInNewGameTest(){
		Game newGame = new Game(4, 0.3, kalle, pelle);
		assertEquals(2, newGame.getSmallBlind());
		assertEquals(4, newGame.getBigBlind());
		assertEquals(0.3, newGame.getBlindsRaisePercentage(), 0.0001);
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

	
//	@Test
//	public void initiateDealTest(){
//		Game newGame = new Game(4, 0.3, kalle, pelle, kent);
//		newGame.newDeck();
//		assertEquals(0, newGame.getCardsOnTable().size());
//		newGame.initiateDeal();
//		assertEquals(3, newGame.getCardsOnTable().size());
//		newGame.initiateDeal();
//		assertEquals(4, newGame.getCardsOnTable().size());
//		newGame.initiateDeal();
//		assertEquals(5, newGame.getCardsOnTable().size());
//	}
	
	@Test (expected = IllegalStateException.class)
	public void initiateDealFiveCardsOnTest(){
		Game newGame = new Game(4, 0.3, kalle, pelle, kent);
		//newGame.newDeck();
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
		assertEquals(8, game.getBigBlind());
		assertEquals(4, game.getSmallBlind());
		assertEquals(1, game.getSmallBlindPosition());
	}
	
	@Test
	public void endGameTest(){
		Game game = new Game(4, 1, joe);
		game.initiateRound();
		game.endGame();
		assertEquals(0, joe.getNumberOfCards());
				
		assertEquals(2, game.getSmallBlind());
		assertEquals(0, game.getSmallBlindPosition());		
		assertFalse(game.getRoundIsActive());
		assertEquals(1, game.getPlayers().size());
		assertEquals(4, game.getBigBlind());
		
		
	}

	
	@Test
	public void testPlayersSetInGame(){
		kalle.setInRound(false);
		pelle.setInRound(false);
		kent.setInRound(false);
		assertFalse(kalle.isInRound());
		assertFalse(pelle.isInRound());
		assertFalse(kent.isInRound());
		defaultGame.initiateRound();
		assertTrue(kalle.isInRound());
		assertTrue(pelle.isInRound());
		assertTrue(kent.isInRound());
	}
	
	@Test
	public void distributeChipsTest(){
		defaultGame.bet(10, kalle);
		kalle.setInRound(true);
		defaultGame.bet(20, pelle);		
		pelle.setInRound(true);
		defaultGame.bet(41, kent);
		kent.setInRound(true);
		defaultGame.bet(41, quinn);
		quinn.setInRound(true);
		defaultGame.bet(41, joe);
		defaultGame.bet(35, martin);
		defaultGame.bet(5, jim);
		ArrayList<ArrayList<BettingPlayer>> victoryOrder = new ArrayList<ArrayList<BettingPlayer>>();
		victoryOrder.add(new ArrayList<BettingPlayer>());
		victoryOrder.get(0).add(kalle);
		victoryOrder.add(new ArrayList<BettingPlayer>());
		victoryOrder.get(1).add(pelle);
		victoryOrder.add(new ArrayList<BettingPlayer>());
		victoryOrder.get(2).add(kent);
		victoryOrder.get(2).add(quinn);
		defaultGame.distributeChip(victoryOrder);
		assertEquals(255, kalle.getChips());
		assertEquals(270, pelle.getChips());
		assertEquals(179, kent.getChips());
		assertEquals(178, quinn.getChips());
		assertEquals(159, joe.getChips());
		assertEquals(165, martin.getChips());
		assertEquals(195, jim.getChips());
	}
	
	@Test(expected = NullPointerException.class)
	public void nullPlayerConstructorTest(){
		BettingPlayer nullPlayer = null;
		Game game = new Game(2, 0.5, nullPlayer);
	}	

	@Test(expected = NullPointerException.class)
	public void nullPlayerTest(){
		Game game = new Game();
		BettingPlayer nullPlayer = null;
		game.addPlayer(nullPlayer);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalRaiseTest(){
		defaultGame.setRaiseBy(0);
	}
	
	@Test
	public void validRaiseTest(){
		defaultGame.setRaiseBy(10);
		defaultGame.initiateRound();
		assertEquals(198, kalle.getChips());
		defaultGame.playerAction(kalle, Action.RAISE);
		assertEquals(188, kalle.getChips());
	}
	
}
