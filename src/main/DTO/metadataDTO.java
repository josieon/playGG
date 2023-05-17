package DTO;

import java.util.*;

public class metadataDTO {
    String dataVersion;
    String matchId;

    public void setDataVersion(String dataVersion) {
        this.dataVersion = dataVersion;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public metadataDTO() {
    }

    List<String> participants;
}
