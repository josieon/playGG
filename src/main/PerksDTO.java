import java.util.*;

public class PerksDTO {
    PerkStatsDTO statPerks;
    List<PerkStyleDTO> styles;

    public String getPerks() {
        StringBuilder sb = new StringBuilder();
        sb.append(statPerks.defense + "," + statPerks.flex + "," + statPerks.offense);
        sb.append(styles.get(0).getSelections() + styles.get(1).getSelections());
        return sb.toString();
    }
    public int getSub() {
        return styles.get(1).getStyle();
    }

    public PerksDTO() {
    }

    public void setStatPerks(PerkStatsDTO statPerks) {
        this.statPerks = statPerks;
    }

    public void setStyles(List<PerkStyleDTO> styles) {
        this.styles = styles;
    }
}
