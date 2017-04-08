package com.hackaton2017.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Kanstantsin_Tolstsik on 4/8/2017.
 */
@Entity
public class Goal {

    @JsonIgnore
    @ManyToOne
    private Job job;

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.ORDINAL)
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