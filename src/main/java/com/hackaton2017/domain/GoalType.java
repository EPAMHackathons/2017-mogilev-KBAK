package com.hackaton2017.domain;

/**
 * Created by Kanstantsin_Tolstsik on 4/8/2017.
 */
public enum GoalType {

    SIZE_WAITING("Waiting for size");

    private String goalDescription;

    GoalType(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }
}