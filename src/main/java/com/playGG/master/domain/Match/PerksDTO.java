package com.playGG.master.domain.Match;
import java.util.*;

public class PerksDTO {
    PerkStatsDTO statPerks;
    List<PerkStyleDTO> styles;

    public List<PerkStyleDTO> getStyles() {
        return styles;
    }

    public PerkStatsDTO getStatPerks() {
        return statPerks;
    }

    public String getperks() {
        StringBuilder sb = new StringBuilder();
        sb.append(statPerks.defense + "," + statPerks.flex + "," + statPerks.offense);
        sb.append(styles.get(0).getSelectionsString() + styles.get(1).getSelectionsString());
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
