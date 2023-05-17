import java.util.*;

public class PerkStyleDTO {
    String description;
    List<PerkStyleSelectionDTO> selections;
    int style;
    public int getStyle() {
        return style;
    }
    public String getSelections() {
        String res = "";
        for(PerkStyleSelectionDTO c : selections) {
            res += "," + c.perk;
        }
        return res;
    }

    public PerkStyleDTO() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSelections(List<PerkStyleSelectionDTO> selections) {
        this.selections = selections;
    }

    public void setStyle(int style) {
        this.style = style;
    }
}
