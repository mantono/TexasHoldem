package texasholdem;
import java.util.*;

import cards.*;
public class Game{
	Deck deck;
	Map<Player, Integer> allPlayers = new HashMap<Player, Integer>();
	List<Player> blindsRotation = new LinkedList<Player>();
	int turn; //H�ller koll p� hur m�nga rundor som hittilds har spelats (t�nkte att det kunde vara intressant l�ngre fram)
	int phase; //H�ller koll p� vilken fas (hole cards, the flop, the turn eller the river) rundan �r i.
	
	public Game(Player... players){
		
		
	}
	
	public void initiateRound(){
		
	}
	
	public Action requestPlayerAction(){
		return Action.FOLD;		
	}
	
	public void endRound(){
		
	}
	
	public void distrubuteChip(){
		
	}
	
	public void initiateGame(){ //Vad �r tanken med denna funktion nu igen? - Emil
		
	}
	
	public void initiateDeal(){
		
	}
	
	public void setPhase(int i){ //Denna metod �r enbart till f�r testning. Beh�ver g�ras s�dana d�r "fake classer" elr vad de hette som g�r att dessa metoder inte skapar en s�kerhetsrisk (denna metod �r just nu "public")
		phase = i;
	}
	
	public Map<Player, Integer> getAllPlayers(){
	return allPlayers;
	}
	
	public List<Player> getBlindsRotation(){ //Denna metod �r mest till f�r testfallen
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
