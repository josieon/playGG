package DTO;

public class ObjectivesDTO {
    ObjectiveDTO baron;
    ObjectiveDTO champion;
    ObjectiveDTO dragon;
    ObjectiveDTO inhibitor;
    ObjectiveDTO riftHerald;
    ObjectiveDTO tower;
    public String getObjectives() {
        return "\"" + baron.kills + ',' + champion.kills + ',' + dragon.kills + ','  + riftHerald.kills + ',' + tower.kills + '"';
    }

    public void setBaron(ObjectiveDTO baron) {
        this.baron = baron;
    }

    public void setChampion(ObjectiveDTO champion) {
        this.champion = champion;
    }

    public void setDragon(ObjectiveDTO dragon) {
        this.dragon = dragon;
    }

    public void setInhibitor(ObjectiveDTO inhibitor) {
        this.inhibitor = inhibitor;
    }

    public void setRiftHerald(ObjectiveDTO riftHerald) {
        this.riftHerald = riftHerald;
    }

    public void setTower(ObjectiveDTO tower) {
        this.tower = tower;
    }
}