package cycling;

import java.io.Serializable;

/**
 * The Segment class that represents a Segment of a stage in the Cycling Tournament.
 * 
 */
public class Segment implements Serializable {
    protected int id;
    protected int stageId;
    protected Double location;
    protected SegmentType type;

    /**
	 * The method that returns the id of the segment.
	 * 
	 * @return id of the segment initialized.
	 * 
	 */
    public int returnId(){
        return id;
    }

    /**
	 * The method that returns the location of the segment in the stage.
	 * 
	 * @return location of the segment initialized.
	 * 
	 */
    public double returnLocation(){
        return location;
    }

    /**
	 * The method that returns the id of the stage the segment is created for.
	 * 
	 * @return id of the stage.
	 * 
	 */
    public int returnStageId(){
        return stageId;
    }

    /**
	 * The method that the type of segment created.
	 * 
	 * @return type of segment.
	 * 
	 */
    public SegmentType returnType(){
        return type;
    }
}
