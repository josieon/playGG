package DTO;

import java.util.*;

public class PerkStatsDTO {
    int defense;
    int flex;
    int offense;

    public PerkStatsDTO() {
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setFlex(int flex) {
        this.flex = flex;
    }

    public void setOffense(int offense) {
        this.offense = offense;
    }

    public PerkStatsDTO(int defense, int flex, int offense) {
        this.defense = defense;
        this.flex = flex;
        this. offense = offense;
    }
}
