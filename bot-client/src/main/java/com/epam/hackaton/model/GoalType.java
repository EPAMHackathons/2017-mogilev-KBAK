package com.epam.hackaton.model;

/**
 * Created by Kanstantsin_Tolstsik on 4/8/2017.
 */
public enum GoalType {

    SIZE_WAITING("Waiting for size"),
    COST_WAITING("Waiting for good cost");

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