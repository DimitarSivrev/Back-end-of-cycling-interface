package cycling;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Race class that represents a race in the Cycling Tournament.
 * 
 */
public class Race implements Serializable {
    private int id;
    private int totalLength;
    private int numberOfStages;
    private String name;
    private String description;

	/**
	 * The class constructor for the Race class. Initializes a new Race with
	 * a race name, description and a randomly generated id. 
	 * @param name Name of the race being intisialized
	 * @param description Description of the race
	 * 
	 */
    Race(String name, String description) {
        this.name = name;
        this.description = description;
        Random rand = new Random();
		int upperbound = 999999999;
		int random = rand.nextInt(upperbound);
        this.id = random;
    }
    /**
	 * The method converts a list of integers to an array of integers.
	 * 
	 * @return An array of integers int[].
	 * 
	 */
	public int[] listToArray(List<Integer> list)
	{
        //Creates a array with the same size as the list argument
		var array = new int[list.toArray().length];
		var temp = 0;
		for (int i : list){
			array[temp++] = i;
		}
		return array;
	}

    /**
	 * The method that returns the id of the race.
	 * 
	 * @return id of the race initialized.
	 * 
	 */
    public int returnId() {
        return id;
    }
    /**
	 * The method that returns the number of stages in the race.
	 * 
	 * @return number of stages in the race initialized.
	 * 
	 */
    public int returnNumberOfStages() {
        return numberOfStages;
    }
    /**
	 * The method that returns the total length of the race.
	 * 
	 * @return total length of the race initialized.
	 * 
	 */
    public int returnTotalLength() {
        return totalLength;
    }
    /**
	 * The method that returns the name of the race.
	 * 
	 * @return name of the race initialized.
	 * 
	 */
    public String returnName() {
        return name;
    }
    /**
	 * The method that returns the description of the race.
	 * 
	 * @return description of the race initialized.
	 * 
	 */
    public String returnDescription() {
        return description;
    }

    public ArrayList<Stage> stageList = new ArrayList<>();

	/**
	 * A method that adds a stage to the stageList 
	 * @param stage The stage being added to the race
	 * 
	 */
    public void addStage(Stage stage){
        stageList.add(stage);
        numberOfStages ++;
    }
    /**
	 * A method that removes a stage from the stageList 
	 * @param stage The stage being removed from the race
	 * 
	 */
    public void removeStage(Stage stage){
        stageList.remove(stage);
        numberOfStages --;
    }

    /**
	 * The method that returns the ids of the stages in the race.
	 * 
	 * @return an array of the ids of the stages.
	 * 
	 */
    public int[] returnStageIds(){
        //Creates a list the cycles through stageList adding each stage's ID
        List<Integer> stageIds = new ArrayList<Integer>();
        for (Stage stage : stageList){
            stageIds.add(stage.returnId());
        }
        return listToArray(stageIds);
    }


}
