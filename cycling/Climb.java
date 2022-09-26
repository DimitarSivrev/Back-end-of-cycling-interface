package cycling;

import java.util.Random;

/**
 * The Climb sub-class that extends the Segment class.
 * 
 */
public class Climb extends Segment{
    private Double averageGradient;
    private Double length;

    /**
	 * The class constructor for the Climb class. Initializes a new segment
     * that is of a type C4, C3, C2, C1 or HC (a climb). Uses the id of the stage
     * the segment is being added to, the location of the segment inside of the stage,
     * the type of segment, the average gradient of the climb, the length of the climb
     * and a randomly generated id. 
     * 
	 * @param stageId The id of the stage the segment is for
	 * @param location The location of the climb
     * @param type The type of climb
     * @param averageGradient The average gradient of the climb
     * @param length The length of the segment
     * 
	 * 
	 */
    Climb(int stageId, Double location, SegmentType type, Double averageGradient,
    Double length){
        this.stageId = stageId;
        this.location = location;
        this.type = type;
        this.averageGradient = averageGradient;
        this.length = length;

        Random rand = new Random();
		int upperbound = 999999999;
		int random = rand.nextInt(upperbound);
        this.id = random;
    }

    /**
	 * The method that returns the averageGradient of the segment.
	 * 
	 * @return averageGradient of the segment initialized.
	 * 
	 */
    public Double returnAverageGradient(){
        return averageGradient;
    }

    /**
	 * The method that returns the length of the segment.
	 * 
	 * @return length of the segment initialized.
	 * 
	 */
    public Double returnLength(){
        return length;
    }


}
