import static org.junit.Assert.*;
import texasholdem.*;

import org.junit.Test;


public class GameTest {

	@Test
	public void addingOnePlayerTest(){ 
		Player kalle = new Player("kalle");
		Game newGame = new Game(4, 2, 4, kalle);
		assertTrue(newGame.getAllPlayers().containsKey(kalle));
		assertTrue(newGame.getBlindsRotation().contains(kalle));
		
	}
	@Test
	public void addingMorePlayersTest(){
		Player kalle = new Player("kalle");
		Player pelle = new Player("pelle");
		Game newGame = new Game(4, 2, 4, kalle, pelle);
		assertTrue(newGame.getAllPlayers().containsKey(kalle));
		assertTrue(newGame.getBlindsRotation().contains(kalle));
		assertTrue(newGame.getAllPlayers().containsKey(pelle));
		assertTrue(newGame.getBlindsRotation().contains(pelle));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addingNoPlayersTest(){
		Game newGame = new Game(4, 2, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addingNegativeBigBlindTest(){
		Player kalle = new Player("Kalle");
		Game newGame = new Game(-4, 2, 4, kalle);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addingNegativeSmallBlindTest(){
		Player kalle = new Player("Kalle");
		Game newGame = new Game(4, -2, 4, kalle);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addingNegativeBlindsRotationFrequencyTest(){
		Player kalle = new Player("Kalle");
		Game newGame = new Game(4, 2, -4, kalle);
	}
	
	@Test
	public void initiateGameTest(){
		Player kalle = new Player("kalle");
		Player pelle = new Player("pelle");
		Game newGame = new Game(4, 2, 4, kalle, pelle);
		newGame.initiateGame();
		assertEquals(0, newGame.getTurn());
		assertEquals(2, newGame.getSmallBlind());
		assertEquals(4, newGame.getBigBlind());
		assertEquals(4, newGame.getBlindsRaiseFrequency());
	}
	
	@Test
	public void initiateRoundTest(){
		Player kalle = new Player("kalle");
		Player pelle = new Player("pelle");
		kalle.addChips(5);
		pelle.addChips(5);
		Game newGame = new Game(4, 2, 4, kalle, pelle);
		newGame.initiateRound();
		assertFalse(newGame.getCurrentDeck() == null);	
		for(Player player : newGame.getBlindsRotation()){
			if(player.getInGame()){
				assertTrue(player.getHand().size() == 2);
				assertTrue(player.getChips() > 0);
			}
			else{
				assertTrue(player.getHand().size() == 0);
				assertTrue(player.getChips() <= 0);
			}
		}
	}
	
	@Test
	public void initiateDealPhaseOneTest(){  //Påbörjad testfall. Behöver ett avslut.
		Player kalle = new Player("kalle");
		Player pelle = new Player("pelle");
		kalle.addChips(5);
		pelle.addChips(5);
		Game newGame = new Game(4, 2, 4, kalle, pelle);
		newGame.setPhase(1);
		newGame.initiateDeal();
		for(Player player : newGame.getBlindsRotation()){
			if(newGame.getBlindsRotation().get(0) == player){
				assertTrue(newGame.getAllPlayers().get(player) <= newGame.getSmallBlind());
				assertTrue(newGame.getAllPlayers().get(player) > 0);
			}
			else if(newGame.getBlindsRotation().get(1) == player){
				assertTrue(newGame.getAllPlayers().get(player) <= newGame.getBigBlind());
				assertTrue(newGame.getAllPlayers().get(player) > 0);
			}
			else if(player.getInGame()){

			}
		}
		
		
	}
}
