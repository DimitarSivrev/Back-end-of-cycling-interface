package cycling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class CyclingPortal implements MiniCyclingPortalInterface {
    
    /**
	 * An ArrayList that stores Races
	 */
    private ArrayList<Race> raceList = new ArrayList<>();

	/**
	 * An ArrayList that stores Teams
	 */
    private ArrayList<Team> teamList = new ArrayList<>();

	/**
	 * The method searches for Objects that match the type and id entered.
	 * 
	 * @param id,
	 * @param type the type of class
	 * @throws IDNotRecognisedException If the id doesn't match any of the classes with
	 * the type entered.
	 * @return the class which corresponds to the id.
	 * 
	 */
    private Object findClassesByID(int id, ClassType type)throws IDNotRecognisedException{

		//If the type is a race the race list is searched
        if (type.equals(ClassType.Race)){
        for (Race race : raceList) {
			//If the IDs match the race is returned
			if (race.returnId() == id) {
				return race;
			}
		}}
		//If the type is a stage each race has its stage list searched
        else if(type.equals(ClassType.Stage)){
        for (Race race : raceList){
            for (Stage stage : race.stageList){
				//If the IDs match the stage is returned
                if(stage.returnId() == id){
                    return stage;
                }
            }}}
		//If the type is a segment each stage has its segment list searched
        else if(type.equals(ClassType.Segment)){
            for (Race race : raceList){
                for (Stage stage : race.stageList){
                    for(Segment segment : stage.segmentList){
						//If the IDs match the segment is returned
                        if(segment.returnId() == id){
                            return segment;
                        }
                }}}}
		//If the type is a team the team list is searched
		else if (type.equals(ClassType.Team)){
			for (Team team : teamList) {
				//If the IDs match the team is returned
				if (team.returnId() == id) {
					return team;
				}
			}}
		//If the type is a rider each team's rider list is searched
		else if (type.equals(ClassType.Rider)){
			for (Team team : teamList) {
				for (Rider rider : team.riderList){
					//If the IDs match the rider is returned
					if(rider.returnId() == id){
						return rider;
					}
				}
			}}
		//If none of the IDs match an exception is thrown
        throw new IDNotRecognisedException();
    }

	/**
	 * The method returns the ArrayList raceList.
	 * 
	 * @return ArrayList raceList.
	 * 
	 */
	public ArrayList<Race> returnRaceList(){
		return raceList;
	}
	/**
	 * The method returns the ArrayList teamList.
	 * 
	 * @return ArrayList teamList.
	 * 
	 */
	public ArrayList<Team> returnTeamList(){
		return teamList;
	}

	@Override
	public int[] getRaceIds() {
		//Creates an integer array big enough to store the unique IDs of each race
        int x = raceList.size();
		int[] raceIds = new int[x];
		//Cycles through the race list adding each race's ID to the array by using the race's method
        for (int i=0; i< x; i++) {

            raceIds[i] = raceList.get(i).returnId();
        }

		return raceIds;
	}

	@Override
	public int createRace(String name, String description) throws IllegalNameException,
	InvalidNameException {
		//Checks if the name inputted is valid otherwise an exception is thrown 
		if(name == null || name.isEmpty() || name.length() > 30 || name.contains(" ")){
            throw new InvalidNameException();};

		//If the name entered matches a race's name already an exception is thrown
        for (Race race : raceList){
            if(race.returnName().equals(name)){
                throw new IllegalNameException();}}

		//If the name passes both checks a new race instance is created
        Race x = new Race(name,description);
        raceList.add(x);

		return x.returnId();
	}

	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		//Searches for a race with the ID inputted then casts the Race class onto the object returned
		Race x = Race.class.cast(findClassesByID(raceId, ClassType.Race));
		//Returns the details of the race found by using the race's methods
		return (
            " raceID = " + x.returnId() +
                    "\n name = " + x.returnName() +
                    "\n description = " + x.returnDescription() +
                    "\n number of stages = " + x.returnNumberOfStages() +
                    "\n total length = " + x.returnTotalLength());
	}

	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {
		//Searches for a race with the ID inputted then removes the race from the race list
		raceList.remove(Race.class.cast(findClassesByID(raceId, ClassType.Race)));

	}

	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		//Searches for a race with the ID inputted then uses the race's method
		return Race.class.cast(findClassesByID(raceId, ClassType.Race)).returnNumberOfStages();
	}

	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length,
	LocalDateTime startTime,
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException,
			InvalidLengthException {
        //Checks if the name is valid otherwise it throws an exception
        if(stageName == null || stageName.isEmpty() || stageName.length() > 30 ||
		stageName.contains(" ")){
                    throw new InvalidNameException();}
        //Checks if the stage's length is over 5 otherwise throws an exception
        if(length < 5){
            throw new InvalidLengthException();}
        //Checks to see if a stage with the same name already exists if so an exception is thrown
        for (Race race : raceList){
            for (Stage stage : race.stageList){
                if(stage.returnName().equals(stageName)){
                    throw new IllegalNameException();
                }
            }}

		//Creates an instance of the stage class with the arguments inputted
		Stage x = new Stage(raceId, stageName, description, length, startTime, type);
		//Adds the stage to the race that matches the race ID
        Race.class.cast(findClassesByID(raceId, ClassType.Race)).addStage(x);
		return x.returnId();
	}

	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
		//Searches for a race with the ID inputted then returns the IDs of its stages
		return Race.class.cast(findClassesByID(raceId, ClassType.Race)).returnStageIds();
	}

	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		//Searches for a stage with the ID inputted then returns the length of the stage
		return Stage.class.cast(findClassesByID(stageId, ClassType.Stage)).returnLength();
	}

	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		//Searches for a stage with the ID inputted
        Stage x = Stage.class.cast(findClassesByID(stageId, ClassType.Stage));
		//Returns the race ID of the race the stage is in
        int raceId = x.returnRaceId();
		//Searches for a race with the ID inputted then removes the stage from that race
        Race.class.cast(findClassesByID(raceId, ClassType.Race)).removeStage(x);

	}

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type,
	Double averageGradient,Double length) throws IDNotRecognisedException,
	InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
        
		//Creates an instance of the class segment using the arguments
        Climb x = new Climb(stageId, location, type, averageGradient, length);
		//Finds the stage the user wants to add the segment to using the ID
        Stage stage = Stage.class.cast(findClassesByID(stageId, ClassType.Stage));
		//Checks if the location of the segment is valid if not throws an exception
        if (stage.returnLength() < location|| 0.0 > location){
            throw new InvalidLocationException();}
		//Checks if the stage is already prepared if so it throws an exception 
        if (stage.returnIsStagePrepared() == true){
            throw new InvalidStageStateException();}
		//Checks if the stage type is a time trial if so it throws an exception
        if (stage.returnType() == StageType.TT){
            throw new InvalidStageTypeException();}

		//If no exceptions are thrown the segment gets added to the stage
        stage.addSegment(x);

		return x.returnId();
	}

	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {

		//Creates an instance of the sprint class using the arguments
        Sprint x = new Sprint(stageId, location);
		//Finds the stage the segment needs to be added to using the stage ID
        Stage stage = Stage.class.cast(findClassesByID(stageId, ClassType.Stage));
		//Checks if the location of the segment is valid if not throws an exception
        if (stage.returnLength() < location|| 0.0 > location){
            throw new InvalidLocationException();}
		//Checks if the stage is already prepared if so it throws an exception 
        if (stage.returnIsStagePrepared() == true){
            throw new InvalidStageStateException();}
		//Checks if the stage type is a time trial if so it throws an exception
        if (stage.returnType() == StageType.TT){
            throw new InvalidStageTypeException();}

		//If no exceptions are thrown the segment gets added to the stage
        stage.addSegment(x);

        return x.returnId();
	}

	@Override
	public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
		//Finds the segment the user want to delete using the ID given as an argument
        Segment x = Segment.class.cast(findClassesByID(segmentId, ClassType.Segment));
		//Finds the stage that segment is in
        int stageId = x.returnStageId();
		//Removes the segment from the stage list
        Stage.class.cast(findClassesByID(stageId, ClassType.Stage)).removeSegment(x);

	}

	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException,
	InvalidStageStateException {
		//Sets the stage state to "waiting for results" meaning segments can't be added
        Stage.class.cast(findClassesByID(stageId, ClassType.Stage)).prepareStage();

	}

	@Override
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
		//Searches for the stage using the ID given as an argument then returns the segment IDs
		return Stage.class.cast(findClassesByID(stageId, ClassType.Stage)).returnSegmentIds();
	}

	@Override
	public int createTeam(String name, String description) throws IllegalNameException,
	InvalidNameException {
		//Checks if the team name is valid if not throws an exception
		if(name == null || name.isEmpty() || name.length() > 30 || name.contains(" ")){
            throw new InvalidNameException();};

		//Checks if the team name aready is used by another team if so throws an exception
        for (Team team : teamList){
            if(team.returnName().equals(name)){
                throw new IllegalNameException();}}

		//If no exceptions are thrown an instance of the class Team is created
		Team team = new Team(name, description);
		//The team is added to the Team list
		teamList.add(team);
		return team.returnId();
	}

	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		//The team is found using the ID given as an argument and is removed from teamList
		teamList.remove(Team.class.cast(findClassesByID(teamId, ClassType.Team)));

	}

	@Override
	public int[] getTeams() {
		//Creates an array with the same size as the teamList meaning every team ID will fit in
		int x = teamList.size();
		int[] teamIds = new int[x];
		//Cycles through the teamList and adds the ID of each team to the array
        for (int i=0; i< x; i++) {

            teamIds[i] = teamList.get(i).returnId();
        }

		return teamIds;
	}

	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		//Searches for team using the ID given as an argument and returns the IDs of the riders in that team
		return Team.class.cast(findClassesByID(teamId, ClassType.Team)).returnRiderIds();
	}

	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
		//Checks if the rider name given is valid if not throws an exception
		if (name == null || name.isEmpty() || yearOfBirth <1900){
			throw new IllegalArgumentException();}

		//If no exception is thrown then a new instance of the Rider class is created 
		Rider rider = new Rider(teamID, name, yearOfBirth);
		//The rider is added to the team which the user gave the ID to
		Team.class.cast(findClassesByID(teamID, ClassType.Team)).addRider(rider);
		return rider.returnId();
	}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		//Searches for a rider with the rider ID given as an argument
		Rider rider = Rider.class.cast(findClassesByID(riderId, ClassType.Rider));
		//Return the team ID of which that rider is in
		int teamID = rider.returnTeamID();
		//Removes the Rider from that team
		Team.class.cast(findClassesByID(teamID, ClassType.Team)).removeRider(rider);

	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
			InvalidStageStateException {
		//Creates a new instance of the Result class
		Result result = new Result(stageId, riderId, checkpoints);
		//Finds the rider the result is for using the ID the user inputted
		Rider rider = Rider.class.cast(findClassesByID(riderId, ClassType.Rider));
		//Finds the stage the result is for using the ID the user inputted
		Stage stage = Stage.class.cast(findClassesByID(stageId, ClassType.Stage));

		//Checks if there are the correct amount of checkpoints for that stage
		if (stage.returnNumberOfSegments() +2 != checkpoints.length){
			throw new InvalidCheckpointsException();}

		//Checks if the stage is prepared if not throws an exception
		if (stage.returnIsStagePrepared() != true ){
			throw new InvalidStageStateException();}

		//Checks if the rider has a result already registered for that stage
		if (stage.riderResultMap.get(rider) == null){
			//If not then the rider and result are added inside of a HashMap inside of the stage
			stage.registerRiderResult(rider, result);
		}else{
			//If there is a result already an exception is thrown
			throw new DuplicatedResultException();}
		
	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) 
			throws IDNotRecognisedException {
		//Finds the corresponding rider and stage using the IDs given as arguments
		Rider rider = Rider.class.cast(findClassesByID(riderId, ClassType.Rider));
		Stage stage = Stage.class.cast(findClassesByID(stageId, ClassType.Stage));

		//Returns the result the rider got in the stage 
		return stage.returnRiderResult(rider);
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId)
			throws IDNotRecognisedException {
		//Finds the corresponding rider and stage using the IDs given as arguments
		Rider rider = Rider.class.cast(findClassesByID(riderId, ClassType.Rider));
		Stage stage = Stage.class.cast(findClassesByID(stageId, ClassType.Stage));
		//Finds the result the rider got in that stage
		Result result = stage.riderResultMap.get(rider);
		//Calculates the adjusted elapsed time of the result
		stage.calculateAdjustedElapsedTime(result);

		return stage.riderResultMap.get(rider).returnAdjustedElapseTime();
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		//Finds the corresponding rider and stage using the IDs given as arguments
		Rider rider = Rider.class.cast(findClassesByID(riderId, ClassType.Rider));
		Stage stage = Stage.class.cast(findClassesByID(stageId, ClassType.Stage));
		//Finds the result the rider got in that stage
		Result result = stage.riderResultMap.get(rider);
		//Removes the rider's result registered in the stage
		stage.riderResultMap.remove(rider,result);

	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		//Finds the stage using the ID given as an argument
		Stage stage = Stage.class.cast(findClassesByID(stageId, ClassType.Stage));

		//Returns an array of riders ranked by their finish times
		Rider [] riders = stage.returnRankedRiders();
		//Creates a new array the same size as riders
		int[] riderIds = new int[riders.length];
		//Cycles through riders adding each rider's id to the array in the same order
		for (int i = 0; i < riders.length; i++){
			riderIds[i] = riders[i].returnId();
		}
		return riderIds;
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId)
			throws IDNotRecognisedException {
		//Finds the stage using the ID given as an argument
		Stage stage = Stage.class.cast(findClassesByID(stageId, ClassType.Stage));
		//Returns an array of riders ranked by their finish times
		Rider [] riders = stage.returnRankedRiders();
		//Creates a new array the same size as riders to store the local times
		LocalTime[] riderTimes = new LocalTime[riders.length];
		//Cycles through riders adding their adjusted elapsed times for the stage
		for (int i = 0; i < riders.length; i++){
			riderTimes[i] = getRiderAdjustedElapsedTimeInStage(stageId,riders[i].returnId());
		}
		return riderTimes;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		//Finds the stage using the ID given as an argument
		Stage stage = Stage.class.cast(findClassesByID(stageId, ClassType.Stage));
		//Returns the type the stage is
		StageType type = stage.returnType();

		//Returns an array of riders ranked by their finish times
		Rider [] riders = stage.returnRankedRiders();
		//Creates a new array the same size as riders
		int[] riderPoints = new int[riders.length];

		//Using the type of stage each rider gets a different amount of points
		if (type.equals(StageType.FLAT)){
			int [] points = {50, 30, 20, 18, 16, 14, 12, 10, 8, 7, 6, 5, 4, 3, 2};
			for (int i = 0; i < riders.length; i++){
				riderPoints[i] = points[i];
			}			
		}else if (type.equals(StageType.MEDIUM_MOUNTAIN)){
			int [] points = {30, 25, 22, 19, 17, 15, 13, 11, 9, 7, 6, 5, 4, 3, 2};
			for (int i = 0; i < riders.length; i++){
				riderPoints[i] = points[i];
			}
		}else if (type.equals(StageType.HIGH_MOUNTAIN)){
			int [] points = {20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
			for (int i = 0; i < riders.length; i++){
				riderPoints[i] = points[i];
			}
		}else if(type.equals(StageType.TT)){
			int [] points = {20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
			for (int i = 0; i < riders.length; i++){
				riderPoints[i] = points[i];
			}
		}

		return riderPoints;
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		//Finds the stage using the ID given as an argument
		Stage stage = Stage.class.cast(findClassesByID(stageId, ClassType.Stage));
		//Returns the segment IDs of the stage
		int[] segments = getStageSegments(stageId);

		//Returns an array of riders ranked by their finish times
		Rider [] riders = stage.returnRankedRiders();
		//Creates a new array the same size as riders
		int[] riderPoints = new int[riders.length];

		//Cycles through every segment in the stage
		for (Integer id: segments){
			//Adds the points the riders got depending on what their finish time was
			SegmentType type = Segment.class.cast(findClassesByID(id, ClassType.Segment)).returnType();
			if (type.equals(SegmentType.C4)){
				int [] points = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				for (int i = 0; i < riders.length; i++){
					riderPoints[i] = riderPoints[i]+points[i];
				}				
			}else if(type.equals(SegmentType.C3)){
				int [] points = {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				for (int i = 0; i < riders.length; i++){
					riderPoints[i] = riderPoints[i]+points[i];
				}				
			}else if(type.equals(SegmentType.C2)){
				int [] points = {5, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				for (int i = 0; i < riders.length; i++){
					riderPoints[i] = riderPoints[i]+points[i];
				}				
			}else if(type.equals(SegmentType.C1)){
				int [] points = {10, 8, 6, 4, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				for (int i = 0; i < riders.length; i++){
					riderPoints[i] = riderPoints[i]+points[i];
				}				
			}else if(type.equals(SegmentType.HC)){
				int [] points = {20, 15, 12, 10, 8, 6, 4, 2, 0, 0, 0, 0, 0, 0, 0};
				for (int i = 0; i < riders.length; i++){
					riderPoints[i] = riderPoints[i]+points[i];
				}				
			}
		}
		return riderPoints;
	}

	@Override
	public void eraseCyclingPortal() {
		//Clears both team and rider lists which store all of the classes
		raceList.clear();
		teamList.clear();

	}

	@Override
	public void saveCyclingPortal(String filename) throws IOException {
		//Creates a file output stream using the filename as an argument
		FileOutputStream fileOut = new FileOutputStream(filename);
		//Creates an object output stream to the file output stream
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		//Serializes the whole CyclingPortal class
		out.writeObject(this);
		//Closes the output stream
		out.close();
		//closes the file output stream
		fileOut.close();
	}

	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		//Finds the file that will be desirialized
		FileInputStream fileIn = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fileIn);

		//Tries to see if the Cycling Portal class can be cassed onto the object
		try {
			Object object = in.readObject();
			CyclingPortal cyclingPortal;

			if (object instanceof CyclingPortal) {
				//Casts the Cycling Portal class
				cyclingPortal = (CyclingPortal)object;

				//Sets the lists with all of the classes to the same ones
				//in the cycling portal that was deserialized
				raceList = cyclingPortal.returnRaceList();
				teamList = cyclingPortal.returnTeamList();
			}
		} catch (IOException e) {
			in.close();
			throw new IOException();
		}catch (ClassNotFoundException e) {
			in.close();
			throw new ClassNotFoundException();
			
		}
		in.close();

	}

}
