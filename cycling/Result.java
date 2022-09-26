package cycling;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Random;

/**
 * The Result class that represents a result of a stage for a rider
 * in the Cycling Tournament.
 * 
 */
public class Result implements Serializable {
    private int id;
    private int stageId;
    private int riderId;
    private LocalTime adjustedElapseTime;
    private LocalTime[] checkpoints;

    /**
	 * The class constructor for the Result class. Initializes a new result with
	 * a stage id, rider id, a list of checkpoints and a randomly generated id. 
     * 
	 * @param stageId The id of the stage the result is for
	 * @param riderId The id of the rider the result is for
     * @param checkpoints The list of times of when the rider
     * started the stage, finished the segments and finished the stage
	 * 
	 */
    Result(int stageId, int riderId, LocalTime... checkpoints){
        this.stageId = stageId;
        this.riderId = riderId;
        this.checkpoints = checkpoints;

        Random rand = new Random();
		int upperbound = 999999999;
		int random = rand.nextInt(upperbound);
        this.id = random;
    }

    /**
	 * The method that returns the id of the result.
	 * 
	 * @return id of the result initialized.
	 * 
	 */
    public int returnId(){
        return id;
    }

    /**
	 * The method that returns the id of the stage the result is registered for.
	 * 
	 * @return id of the stage.
	 * 
	 */
    public int stageId(){
        return stageId;
    }

    /**
	 * The method that returns an array of local times representing times of when the rider
     * started the stage, finished the segments and finished the stage
     * 
	 * 
	 * @return an array of local times representing checkpoints
	 * 
	 */
    public LocalTime[] returnCheckpoints(){
        return checkpoints;
    }

    /**
	 * The method that returns the adjusted elapsed time of the result.
	 * 
	 * @return Local Time of the adjusted elapsed time
	 * 
	 */
    public LocalTime returnAdjustedElapseTime(){
        return adjustedElapseTime;
    }

    /**
	 * A method that sets the adjusted elapsed time of the result to
     * the prameter.
     * 
	 * @param elapseTime The LocalTime of the new adjusted elapse time
	 * 
	 */
    public void setAdjustedElapseTime(LocalTime elapseTime){
        adjustedElapseTime = elapseTime;
    }

    /**
	 * The method that returns the id of the rider the result is registered for.
	 * 
	 * @return id of the rider.
	 * 
	 */
    public int returnRiderId(){
        return riderId;
    }

    /**
	 * The method that returns the finish time of the rider for the stage 
     * converted into nano seconds.
	 * 
	 * @return nanoseconds representing the finish time.
	 * 
	 */
    public long returnElapsedTime(){
        return (checkpoints[checkpoints.length -1]).toNanoOfDay();
    }
}
