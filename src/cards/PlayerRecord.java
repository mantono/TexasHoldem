package cards;

import java.util.List;

public interface PlayerRecord<T extends Player>
{
	boolean addPlayer(T player);
	boolean removePlayer(T player);
	void clearAllHands();
	void dealCards(Deck deck, int amountOfCards);
	List<T> getPlayers();
	T getPlayer(int index);
	T getCurrentPlayer();
	int nextPlayer();
	int getNumberOfPlayersInGame();
}
