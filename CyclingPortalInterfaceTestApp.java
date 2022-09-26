import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import cycling.CyclingPortal;
import cycling.DuplicatedResultException;
import cycling.IDNotRecognisedException;
import cycling.IllegalNameException;
import cycling.InvalidCheckpointsException;
import cycling.InvalidLengthException;
import cycling.InvalidLocationException;
import cycling.InvalidNameException;
import cycling.InvalidStageStateException;
import cycling.InvalidStageTypeException;
import cycling.MiniCyclingPortalInterface;
import cycling.SegmentType;
import cycling.StageType;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the CyclingPortalInterface interface -- note you
 * will want to increase these checks, and run it on your CyclingPortal class
 * (not the BadCyclingPortal class).
 *
 * 
 * @author Diogo Pacheco
 * @version 1.1
 */
public class CyclingPortalInterfaceTestApp {

	/**
	 * Test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("The system compiled and started the execution...");

		// TODO replace BadMiniCyclingPortal by CyclingPortal
		MiniCyclingPortalInterface portal1 = new CyclingPortal();
		MiniCyclingPortalInterface portal2 = new CyclingPortal();

		assert (portal1.getRaceIds().length == 1)
				: "Innitial Portal not empty as required or not returning an empty array.";
		assert (portal1.getTeams().length == 0)
				: "Innitial Portal not empty as required or not returning an empty array.";

		try {
			
			int raceId1 = portal1.createRace("Race1", "The first race");
			int raceId2 = portal1.createRace("Race2", "The second race race");
			System.out.println(Arrays.toString(portal1.getRaceIds()));
			//portal1.removeRaceById(raceId1);
			int stageId2 = portal1.addStageToRace(raceId1, "stage1", "description", 5.0, LocalDateTime.now(), StageType.FLAT);
			System.out.println(Arrays.toString(portal1.getRaceIds()));
			System.out.println(portal1.viewRaceDetails(raceId1));
			int stageId = portal1.addStageToRace(raceId1, "stage2", "description", 5.0, LocalDateTime.now(), StageType.HIGH_MOUNTAIN);
			System.out.println(portal1.getNumberOfStages(raceId1));
			System.out.println(Arrays.toString(portal1.getRaceStages(raceId1)));
			System.out.println(portal1.getStageLength(stageId));
			//
			System.out.println(Arrays.toString(portal1.getRaceStages(raceId1)));
			portal1.addCategorizedClimbToStage(stageId, 4.0, SegmentType.C3, 4.0, 1.0);
			// System.out.println(portal1.addIntermediateSprintToStage(stageId, 1.5));
			// System.out.println(portal1.addIntermediateSprintToStage(stageId, 0.5));
			// System.out.println(portal1.addIntermediateSprintToStage(stageId, 2.5));
			System.out.println(Arrays.toString(portal1.getStageSegments(stageId)));
			System.out.println(portal1.createTeam("team1", "description"));
			int teamId = portal1.createTeam("team2", "description");
			//portal1.removeTeam(teamId);
			System.out.println(Arrays.toString(portal1.getTeams()));
portal1.concludeStagePreparation(stageId);
			int riderId = portal1.createRider(teamId, "Rider1", 1901);
			int riderId2 = portal1.createRider(teamId, "Rider2", 1901);
			int riderId3 = portal1.createRider(teamId, "Rider3", 1901);
			portal1.saveCyclingPortal("portal.ser");
			portal1.eraseCyclingPortal();
			portal1.loadCyclingPortal("portal.ser");
			System.out.println(Arrays.toString(portal1.getTeamRiders(teamId)));
			portal1.registerRiderResultsInStage(stageId, riderId,  LocalTime.of(2, 0, 0),LocalTime.of(2, 2, 15), LocalTime.of(1, 15, 26));		
			portal1.registerRiderResultsInStage(stageId, riderId2,  LocalTime.of(1, 0, 0),LocalTime.of(3, 2, 15), LocalTime.of(1, 15, 24,500000000));
			portal1.registerRiderResultsInStage(stageId, riderId3,  LocalTime.of(0, 0, 0),LocalTime.of(1, 2, 15), LocalTime.of(1, 15, 25,500000000));

			// System.out.println(Arrays.toString(portal1.getRiderResultsInStage(stageId,riderId2)));
			//portal1.removeRider(riderId);
			portal1.deleteRiderResultsInStage(stageId, riderId);
			portal1.registerRiderResultsInStage(stageId, riderId,  LocalTime.of(2, 0, 0),LocalTime.of(2, 2, 15), LocalTime.of(1, 15, 26));
			// System.out.println(portal1.getRiderAdjustedElapsedTimeInStage(stageId, riderId));
			// System.out.println(portal1.getRiderAdjustedElapsedTimeInStage(stageId, riderId3));
			// System.out.println(portal1.getRiderAdjustedElapsedTimeInStage(stageId, riderId2));

			System.out.println(Arrays.toString(portal1.getRidersRankInStage(stageId)));
			System.out.println(Arrays.toString(portal1.getRankedAdjustedElapsedTimesInStage(stageId)));
			System.out.println(Arrays.toString(portal1.getRidersPointsInStage(stageId)));
			System.out.println(Arrays.toString(portal1.getRidersMountainPointsInStage(stageId)));

			portal1.createTeam("TeamOne", "My favorite");
			portal2.createTeam("TeamOne", "My favorite");
		} catch (IllegalNameException e) {
			e.printStackTrace();
		} catch (InvalidNameException e) {
			e.printStackTrace();
		}catch (IDNotRecognisedException e) {
			e.printStackTrace();
		}catch (InvalidLengthException e) {
			e.printStackTrace();
		}catch (InvalidLocationException e) {
			e.printStackTrace();
		}catch (InvalidStageStateException e) {
			e.printStackTrace();
		}catch (InvalidStageTypeException e) {
			e.printStackTrace();
		}catch (DuplicatedResultException e) {
			e.printStackTrace();
		}catch (InvalidCheckpointsException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		assert (portal1.getTeams().length == 1)
				: "Portal1 should have one team.";

		assert (portal2.getTeams().length == 1)
				: "Portal2 should have one team.";

	}

}
