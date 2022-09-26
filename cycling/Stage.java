package cycling;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * The Stage class that represents a stage in the Cycling Tournament.
 * 
 */
public class Stage implements Serializable{
    private int id;
    private int raceId;
    private String stageName;
    private String description;
    private double length;
    private LocalDateTime startTime;
    private StageType type;
    private int numberOfSegments;
    private boolean isStagePrepared;

    /**
	 * The class constructor for the Stage class. Initializes a new Stage with
	 * a raceId, stage name, description, length, start time, type and a randomly generated id.
     * 
	 * @param raceId The id of the race the stage is being added to
	 * @param stageName Name of the stage being intisialized
	 * @param description Description of the stage
     * @param length The length of the stage
	 * @param startTime The start time of the stage
     * @param type The type of stage
	 */
    Stage(int raceId, String stageName, String description, double length, LocalDateTime startTime,
    StageType type){
        this.raceId = raceId;
        this.stageName = stageName;
        this.description = description;
        this.length = length;
        this.startTime = startTime;
        this.type = type;
        Random rand = new Random();
		int upperbound = 999999999;
		int random = rand.nextInt(upperbound);
        this.id = random;
    };

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
	 * The method that returns the id of the stage.
	 * 
	 * @return id of the stage initialized.
	 * 
	 */
    public int returnId(){
        return id;
    }

    /**
	 * The method that returns the description of the stage.
	 * 
	 * @return description of the stage initialized.
	 * 
	 */
    public String returnDescription() {
        return description;
    }

    /**
	 * The method that returns the start time of the stage.
	 * 
	 * @return start time of the stage initialized.
	 * 
	 */
    public LocalDateTime returnStartTime(){
        return startTime;
    }

    /**
	 * The method that returns the number of segments in the stage.
	 * 
	 * @return number of segments in the stage initialized.
	 * 
	 */
    public int returnNumberOfSegments(){
        return numberOfSegments;
    }

    /**
	 * The method that returns the id of the race the stage is added to.
	 * 
	 * @return id of the race the stage is added to.
	 * 
	 */
    public int returnRaceId(){
        return raceId;
    }

    /**
	 * The method that returns the length of the stage.
	 * 
	 * @return length of the stage initialized.
	 * 
	 */
    public double returnLength(){
        return length;
    }

    /**
	 * The method that returns the name of the stage.
	 * 
	 * @return name of the stage initialized.
	 * 
	 */
    public String returnName() {
        return stageName;
    }

    /**
	 * The method that returns the type of the stage.
	 * 
	 * @return type of the stage initialized.
	 * 
	 */
    public StageType returnType(){
        return type;
    }

    public ArrayList<Segment> segmentList = new ArrayList<>();

	/**
	 * A method that adds a segment to the segmentList 
	 * @param segment The segment being added to the stage
	 * 
	 */
    public void addSegment(Segment segment){
        segmentList.add(segment);
        numberOfSegments ++;
    }

	/**
	 * A method that removes a segment from the segmentList 
	 * @param segment The segment being removed from the stage
	 * 
	 */
    public void removeSegment(Segment segment){
        segmentList.remove(segment);
        numberOfSegments --;
    }

    /**
	 * The method that returns the ids of the segments in the stage by the order
     * in which they are in the actual stage of the race.
	 * 
	 * @return an array of the ids of the segments.
	 * 
	 */
    public int [] returnSegmentIds(){
        //Creates a HashMap with the segment IDs and their location 
        HashMap<Integer, Double> segmentTime = new HashMap<>();
        //Adds the segment IDs and location by cycling the segmentList
        for (Segment segment : segmentList){
            segmentTime.put(segment.returnId(),segment.returnLocation());
        }
        //Creates a new HashMap and adds the values from the segmentTime HashMap sorted by location
        Map<Integer,Double> sortedSegmentTime = 
        segmentTime.entrySet().stream().sorted(Entry.comparingByValue()).collect
        (Collectors.toMap(Entry::getKey, Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new));

        //Creates a list of integers and adds the IDs of the segments from the HashMap keeping the order
        List<Integer> segmentIds = new ArrayList<Integer>();
        sortedSegmentTime.forEach((i,y) -> {
            segmentIds.add(i);
        });

        return listToArray(segmentIds);
    }

    /**
	 * The method returns true if the stage is prepared and false if it isn't
	 * 
	 * @return true or false depending if the stage is prepared
	 * 
	 */
    public boolean returnIsStagePrepared(){
        return isStagePrepared;
    }

    /**
	 * Prepares the stage by setting isStagePrepared to true
	 * 
	 */
    public void prepareStage(){
        this.isStagePrepared = true;
    }

    //A HashMap to store riders and the result they got this stage 
    HashMap<Rider,Result> riderResultMap = new HashMap<>();

    /**
	 * This method registers a rider and their score by adding
     * them to the hashmap riderResultMap
	 * @param rider The rider object that is being added
	 * @param result The result object that is being added
	 * 
	 */
    public void registerRiderResult(Rider rider, Result result){
        riderResultMap.put(rider, result);

    }

    /**
	 * This method calculates the adjusted elapsed time for a result by 
     * comparing the times of all the other results registered in this stage.
     * 
	 * @param result The result that is having its elapsed time adjusted
	 * 
	 */
    public void calculateAdjustedElapsedTime(Result result1){
        //Sets a variable of the last time in the checkpoints array of the result which is the finish time
        LocalTime riderTime = result1.returnCheckpoints()[numberOfSegments+1];
        //Sets the finish time as the adjusted elapsed time
        result1.setAdjustedElapseTime(riderTime);
        //Cycles through the other results in this stage
        for (Result result2 : riderResultMap.values()) {
            //Calculates the time difference in milli seconds between the results
            long timeDif = result2.returnCheckpoints()[numberOfSegments+1].until(riderTime,ChronoUnit.MILLIS);

            //If the result of another rider is faster with less than 1000 milliseconds
            if (0 < timeDif && timeDif < 1000){
                //If the adjusted elapsed time of the second rider is null that means the result 
                //hasn't gone through this function 
                if (result2.returnAdjustedElapseTime() == null){
                    //The adjusted elapsed time of the result entered as an argument is set to the finish time
                    result1.setAdjustedElapseTime(result2.returnCheckpoints()[numberOfSegments+1]);}
                else{
                    //Else it is set to the adjusted elapsed time of the second rider
                    result1.setAdjustedElapseTime(result2.returnAdjustedElapseTime());}
            }
        }

    }

	/**
	 * The method returns an array for the riders checkpoints in this stage.
     * For this method to work the rider needs to have a result registered for
     * this stage, otherwise an empty array gets returned.
	 * 
	 * @param rider The rider instance
     * 
	 * @return An array of Local Times representing the rider's checkpoints
     * or an empty array.
	 * 
	 */
    public LocalTime[] returnRiderResult(Rider rider){
        //If the rider has a result it is returned
        if (riderResultMap.get(rider) != null){
        return riderResultMap.get(rider).returnCheckpoints();}
        else{
            //Else an empty array is returned
            LocalTime[] checkpoints = {};
            return checkpoints;
        }
    }

    /**
	 * This method sorts the hashmap riderResultMap and returns an ordered array of riders
     * based on the time they finished the stage.
	 * 
	 * @return An ordered array of riders
	 * 
	 */
    public Rider []  returnRankedRiders (){
        //Creates a HashMap of the riders and their finish time in long format
        HashMap<Rider, Long> ridersTimes = new HashMap<>();
        riderResultMap.forEach((rider,result) -> {
            ridersTimes.put(rider,result.returnElapsedTime());
    });
        //Creats a new HashMap using the ridersTimes HashMap's values but ordered
        HashMap<Rider,Long> sortedRidersTimes= 
        ridersTimes.entrySet().stream().sorted(Entry.comparingByValue()).collect
        (Collectors.toMap(Entry::getKey, Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new));

        //Creates a list of riders and adds the rider from the sortedRidersTimes HashMap keeping the order
        List<Rider> Riders = new ArrayList<Rider>();
        sortedRidersTimes.forEach((i,y) -> {
            
            Riders.add(i);
        });

        //Creates an array and adds the Riders list riders to it
        Rider [] RiderArray = new Rider[Riders.size()];
        for (int i =0; i<Riders.size();i++){
            RiderArray[i] = Riders.get(i);
        }
        return RiderArray;

    }

}
