package texasholdem;
import java.util.*;

import cards.*;
public class Game{
	Deck deck;
	Map<Player, Integer> allPlayers = new HashMap<Player, Integer>();
	List<Player> blindsRotation = new LinkedList<Player>();
	int turn; //Håller koll på hur många rundor som hittilds har spelats (tänkte att det kunde vara intressant längre fram)
	int phase; //Håller koll på vilken fas (hole cards, the flop, the turn eller the river) rundan är i.
	
	public Game(){
		
		
	}
	
	public void initiateRound(){
		
	}
	
	public Action requestPlayerAction(){
		
	}
	
	public void endRound(){
		
	}
	
	public void distrubuteChip(){
		
	}
	
	public void initiateGame(){ //Vad är tanken med denna funktion nu igen? - Emil
		
	}
	
	public void initiateDeal(){
		
	}
	
	public void setPhase(int i){ //Denna metod är enbart till för testning. Behöver göras sådana där "fake classer" elr vad de hette som gör att dessa metoder inte skapar en säkerhetsrisk (denna metod är just nu "public")
		phase = i;
	}
	
	public Map<Player, Integer> getAllPlayers(){
	return allPlayers;
	}
	
	public List<Player> getBlindsRotation(){ //Denna metod är mest till för testfallen
		return blindsRotation;
	}
	
	public Deck getCurrentDeck(){
		return deck;
	}
	
	public int getCurrentPhase(){
		return phase;
	}
	
	public int getTurn(){
		return turn;
	}
	
	
}
