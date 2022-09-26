package cycling;

import java.time.LocalTime;
import java.io.Serializable;

public class ResultOFStage implements Serializable{
    int stageId = 0;
    int riderId = 0;
    LocalTime result = LocalTime.now();
    ResultOFStageType type =ResultOFStageType.Segment_time;
}
