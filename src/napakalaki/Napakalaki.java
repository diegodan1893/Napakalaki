/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Juanjo
 */
public class Napakalaki {
	
	private static final Napakalaki instance = new Napakalaki();
	private Monster currentMonster;
	private CardDealer dealer;
	private Player currentPlayer;
	private ArrayList<Player> players;
	
	private Napakalaki() { }
	
	public static Napakalaki getInstance(){
		return instance;
	}
	
	private void initPlayers(ArrayList<String> names){
		for (String name : names)
			players.add(new Player(name));
	}
	
	private Player nextPlayer(){
		int indice;
		
		if (currentPlayer == null){
			Random rand = new Random();
			
			indice = rand.nextInt((players.size() - 1) + 1) + 1;
		}
		else{
			indice = players.indexOf(currentPlayer);
			indice = (indice + 1) % players.size();
		}
		
		currentPlayer = players.get(indice);
		return currentPlayer;
	}
	
	private boolean nextTurnAllowed(){
		return currentPlayer.validState();
	}
	
	private void setEnemies(){
		ArrayList<Integer> arrayPlayers = new ArrayList();
		ArrayList<Integer> arrayEnemies = new ArrayList();
		boolean abort, finished = false;
		
		for (int i = 0; i < players.size(); ++i){
			arrayPlayers.set(i, i);
			arrayEnemies.set(i, i);
		}
		
		while (!finished){
			Collections.shuffle(arrayEnemies);
			
			abort = false;
			for (int i = 0; i < players.size() - 1 && !abort; ++i){
				if (!finished
						&& arrayPlayers.get(i)   != arrayEnemies.get(i)
						&& arrayPlayers.get(i+1) != arrayEnemies.get(i+1)){
					
					// Asignar al jugador players[arrayPlayers[0]] el enemigo
					// players[arrayEnemies[0]] y sacarlos de los arrays
					players.get(arrayPlayers.remove(0))
							.setEnemy(players.get(arrayEnemies.remove(0)));
				}
				else{
					abort = true;
				}
			}
		}
		
		// Asignar el último jugador con el último enemigo
		players.get(arrayPlayers.get(0))
				.setEnemy(players.get(arrayEnemies.get(0)));
	}
	
	public CombatResult developCombat(){
		return CombatResult.LOSE;
	}
	
	public void discardVisibleTreasures(ArrayList<Treasure> treasures){
		
	}
	
	public void discardHiddenTreasures(ArrayList<Treasure> treasures){
		
	}
	
	public void makeTrasuresVisible(ArrayList<Treasure> treasures){
		
	}
	
	public void initGame(ArrayList<String> players){
		
	}
	
	public Player getCurrentPlayer(){
		return currentPlayer;
	}
	
	public Monster getCurrentMonster(){
		return currentMonster;
	}
	
	public boolean nextTurn(){
		return false;
	}
	
	public boolean endOfGame(CombatResult result){
		return result == CombatResult.WINGAME;
	}
}
