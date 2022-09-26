package cycling;

import java.io.Serializable;
import java.util.Random;

/**
 * The Rider class that represents a rider in the Cycling Tournament.
 * 
 */
public class Rider implements Serializable {
    private int id;
    private int teamID;
    private String name;
    private int yearOfBirth;

    /**
	 * The class constructor for the Rider class. Initializes a new rider with
	 * a team id, rider name, year of birth and a randomly generated id. 
     * 
	 * @param teamID The id of the team the rider is being added to
	 * @param name The name of the rider
     * @param yearOfBirth The year the rider was born
	 * 
	 */
    Rider(int teamID, String name, int yearOfBirth){
        this.teamID = teamID;
        this.name = name;
        this.yearOfBirth = yearOfBirth;

        Random rand = new Random();
		int upperbound = 999999999;
		int random = rand.nextInt(upperbound);
        this.id = random;
    }

    /**
	 * The method that returns the id of the rider.
	 * 
	 * @return id of the rider initialized.
	 * 
	 */
    public int returnId(){
        return id;
    }

    /**
	 * The method that returns the year of birth of the rider.
	 * 
	 * @return year of birth of the rider.
	 * 
	 */
    public int returnYearOfBirth(){
        return yearOfBirth;
    }

    /**
	 * The method that returns the id of the team the rider is a apart of.
	 * 
	 * @return id of the rider's team.
	 * 
	 */
    public int returnTeamID(){
        return teamID;
    }

    /**
	 * The method that returns the name of the rider.
	 * 
	 * @return name of the rider initialized.
	 * 
	 */
    public String returnName(){
        return name;
    }


}
