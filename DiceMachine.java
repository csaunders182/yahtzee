import acm.util.*;

public class DiceMachine implements YahtzeeConstants {
	public DiceMachine(){
		new RandomGenerator();
		rGen = RandomGenerator.getInstance();
		diceArray =new int[N_DICE];
	}
	
	public int[] rollDice(boolean[] whichDice){
		for (int i=0; i<whichDice.length; i++){
			if (whichDice[i] == true){
				diceArray[i] = rGen.nextInt(1, 6);
			}
		}
		return diceArray;
	}
	
	int[] diceArray;
	RandomGenerator rGen;
}
