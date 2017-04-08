package com.epam.hackaton.model;

/**
 * Created by Kanstantsin_Tolstsik on 4/8/2017.
 */

public class Goal {

    private Job job;

    private Long id;

    private GoalType goalType;

    //used for size, cost or other information
    private String data;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalType goalType) {
        this.goalType = goalType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "job=" + job +
                ", id=" + id +
                ", goalType=" + goalType +
                ", data='" + data + '\'' +
                '}';
    }
}