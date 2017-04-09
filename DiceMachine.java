import acm.util.*;

public class DiceMachine implements YahtzeeConstants {
	public DiceMachine(){
		new RandomGenerator();
		rGen = RandomGenerator.getInstance();
		diceArray =new int[N_DICE];
	}
	
	public int[] rollDice(boolean[] selectedDice){
		for (int i=0; i<selectedDice.length; i++){
			if (selectedDice[i] == true){
				diceArray[i] = rGen.nextInt(1, 6);
			}
		}
		return diceArray;
	}
	
	int[] diceArray;
	RandomGenerator rGen;
}
