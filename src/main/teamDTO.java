import java.util.*;

public class teamDTO {
    List<BanDTO> bans;
    ObjectivesDTO objectives;
    int teamId;
    boolean win;
    public String getTeam() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"bans\": \"");
        for (int i = 0; i < 5; i++) {
            sb.append(bans.get(i).championId);
            if(i < 4)
                sb.append(",");
        }
        sb.append("\",");
        sb.append("\"objects\" : ");
        sb.append(objectives.getObjectives());
        sb.append(",\"team\":" + (teamId == 200 ? "\"RED\"" : "\"BLUE\""));
        sb.append(",\"win\":" + (win ? "true" : "false")+'}');
        return sb.toString();
    }

    public teamDTO() {
    }

    public void setBans(List<BanDTO> bans) {
        this.bans = bans;
    }

    public void setObjectives(ObjectivesDTO objectives) {
        this.objectives = objectives;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
