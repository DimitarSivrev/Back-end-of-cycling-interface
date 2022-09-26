package cycling;

import java.time.LocalTime;
import java.io.Serializable;

public class ResultOfSegment implements Serializable{
    int segmentId = 0;
    int stageId = 0;
    int riderId = 0;
    LocalTime result = LocalTime.now();
    ResultOFStageType type =ResultOFStageType.Segment_time;
    SegmentType segmentType = SegmentType.C4;
}
