package cycling;

import java.util.Random;

/**
 * The Sprint sub-class that extends the Segment class.
 * 
 */
public class Sprint extends Segment {
    /**
	 * The class constructor for the Sprint class. Initializes a new segment
     * that is of a type intermediate sprint. Uses the id of the stage
     * the segment is being added to, the location of the segment inside of the stage
     * and a randomly generated id. 
     * 
	 * @param stageId The id of the stage the segment is for
	 * @param location The location of the climb
     * 
	 * 
	 */
    Sprint(int stageId, double location){
        this.stageId = stageId;
        this.location = location;
        this.type = SegmentType.SPRINT;

        Random rand = new Random();
		int upperbound = 999999999;
		int random = rand.nextInt(upperbound);
        this.id = random;
    }
}
