package cycling;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Team class that represents a team of riders in the Cycling Tournament.
 * 
 */
public class Team implements Serializable {
    private int id;
    private String name;
    private String description;
    private int numberOfRiders;

	/**
	 * The class constructor for the Team class. Initializes a new team with
	 * a team name, description and a randomly generated id. 
	 * @param name Name of the team being intisialized
	 * @param description Description of the team
	 * 
	 */
    Team(String name, String description){
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
	public int[] listToArray(List<Integer> list){
        //Creates a array with the same size as the list argument
		var array = new int[list.toArray().length];
		var temp = 0;
		for (int i : list){
			array[temp++] = i;
		}
		return array;
	}

    /**
	 * The method that returns the id of the team.
	 * 
	 * @return id of the team initialized.
	 * 
	 */
    public int returnId(){
        return id;
    }
    /**
	 * The method that returns the description of the team.
	 * 
	 * @return description of the team initialized.
	 * 
	 */
    public String returnDescription() {
        return description;
    }

    /**
	 * The method that returns the name of the team.
	 * 
	 * @return name of the team initialized.
	 * 
	 */
    public String returnName(){
        return name;
    }

    /**
	 * The method that returns the number of riders in the team.
	 * 
	 * @return number of riders in the team initialized.
	 * 
	 */
    public int returnNumberOfRiders(){
        return numberOfRiders;
    }

    public ArrayList<Rider> riderList = new ArrayList<>();

    /**
	 * A method that adds a rider to the riderList 
	 * @param rider The rider being added to the team
	 * 
	 */
    public void addRider(Rider rider){
        riderList.add(rider);
        numberOfRiders ++;
    }

    /**
	 * A method that removes a rider from the riderList 
	 * @param rider The rider being removed from the team
	 * 
	 */
    public void removeRider(Rider rider){
        riderList.remove(rider);
        numberOfRiders --;
    }

    /**
	 * The method that returns the ids of the riders in the team.
	 * 
	 * @return an array of the ids of the riders.
	 * 
	 */
    public int[] returnRiderIds(){
        //Creates a list then cycles through riderList adding each rider's ID
        List<Integer> riderIds = new ArrayList<Integer>();
        for (Rider rider : riderList){
            riderIds.add(rider.returnId());
        }
        return listToArray(riderIds);
    }

}
