package texasholdem;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import cards.Deck;
import cards.Player;
import cards.PlayerRecord;

public class PlayerBook<T extends Player> implements PlayerRecord<T>
{
	private final List<T> players = new LinkedList<T>();
	private int currentPlayer = 0;

	public PlayerBook(T... players)
	{
		this.players.addAll(Arrays.asList(players));
		if(this.players.contains(null))
			throw new NullPointerException("A player variable is pointing to null.");
	}
	
	
	public boolean addPlayer(T player)
	{
		if(player == null)
			throw new NullPointerException("A player variable is pointing to null.");
		return players.add(player);
	}
	
	public boolean removePlayer(T player)
	{
		return players.remove(player);
	}

	public void clearAllHands()
	{
		for(T player : players)
			player.clearHand();
	}
	
	public void dealCards(Deck deck, int amountOfCards)
	{
		for(Player player : players)
			player.newHand(deck.drawCards(amountOfCards));
	}

	public List<T> getPlayers()
	{
		return players;
	}
	
	public T getPlayer(int index)
	{
		return players.get(index);
	}
	
	public T getCurrentPlayer()
	{
		return players.get(currentPlayer);
	}
	
	public int nextPlayer()
	{
		currentPlayer++;
		if(currentPlayer >= players.size())
			currentPlayer = 0;
		return currentPlayer;
	}
	
	public int getNumberOfPlayersInGame()
	{
		int numberOfPlayers = 0;
		for(T player : players)
			if(player.isInRound())
				numberOfPlayers++;
		return numberOfPlayers;
	}
}
