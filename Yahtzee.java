/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	public void run() {
		setupPlayers();
		initDisplay();
		dm = new DiceMachine();
		resetSelectedDice();
		playGame();
	}
	
	/**
	 * Prompts the user for information about the number of players, then sets up the
	 * players array and number of players.
	 */
	private void setupPlayers() {
		nPlayers = chooseNumberOfPlayers();	
		
		/* Set up the players array by reading names for each player. */
		playerNames = new String[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			/* IODialog is a class that allows us to prompt the user for information as a
			 * series of dialog boxes.  We will use this here to read player names.
			 */
			IODialog dialog = getDialog();
			playerNames[i] = dialog.readLine("Enter name for player " + (i + 1));
		}
	}
	
	/**
	 * Prompts the user for a number of players in this game, reprompting until the user
	 * enters a valid number.
	 * 
	 * @return The number of players in this game.
	 */
	private int chooseNumberOfPlayers() {
		/* See setupPlayers() for more details on how IODialog works. */
		IODialog dialog = getDialog();
		
		while (true) {
			/* Prompt the user for a number of players. */
			int result = dialog.readInt("Enter number of players");
			
			/* If the result is valid, return it. */
			if (result > 0 && result <= MAX_PLAYERS)
				return result;
			
			dialog.println("Please enter a valid number of players.");
		}
	}
	
	/**
	 * Sets up the YahtzeeDisplay associated with this game.
	 */
	private void initDisplay() {
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
	}

	/**
	 * Actually plays a game of Yahtzee.  This is where you should begin writing your
	 * implementation.
	 */
	private void playGame() {
		for (int turn=1; turn<=N_SCORING_CATEGORIES; turn++ ){
			for (int player=0; player<nPlayers; player++){
					println("player turn = " + playerNames[player]);
					display.waitForPlayerToClickRoll(player);
					roll();
					for (int round=0; round<DICE_SELECTION_ROUNDS; round++){
						display.waitForPlayerToSelectDice();
						selectedDiceAssigner();
						roll();
					}
					display.waitForPlayerToSelectCategory();
			}
		}
	}
	private void firstRoll(String playerName){
		diceRolls = dm.rollDice(selectedDice);
		display.displayDice(diceRolls);
	}
	
	private void selectedDiceAssigner(){
		for (int i=0; i<selectedDice.length; i++){
			selectedDice[i] = display.isDieSelected(i);
		}
	}
	
	private void roll(){
		int[] replacedDiceRolls = dm.rollDice(selectedDice);
		for (int i=0; i<diceRolls.length; i++){
			if (selectedDice[i] == true){
				diceRolls[i] = replacedDiceRolls[i];
			}
		}
		display.displayDice(diceRolls);
	}
	
	private void resetSelectedDice(){
		for (int i=0; i<selectedDice.length; i++){
			selectedDice[i] = true;
		}
	}
	
	
	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private DiceMachine dm;
	private int[] diceRolls = new int[5];
	private boolean[] selectedDice = new boolean[5]; 
}
