package texasholdem;
import java.util.*;

import cards.*;
public class Game{
	Deck deck;
	Map<Player, Integer> allPlayers = new HashMap<Player, Integer>();
	List<Player> blindsRotation = new LinkedList<Player>();
	List<Card> tableCards = new ArrayList<Card>();
	
	
	int bigBlind, smallBlind, blindsRaiseFrequency, phase, turn;
	
	public Game(int bigBlind, int smallBlind, int blindsRaiseFrequency, Player... players){
		
		
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
	
	public List<Card> getTableCards(){
		return tableCards;
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
	
	public int getBigBlind(){
		return bigBlind;
	}
	
	public int getSmallBlind(){
		return smallBlind;
	}
	
	public int getBlindsRaiseFrequency(){
		return blindsRaiseFrequency;
	}
	
}
