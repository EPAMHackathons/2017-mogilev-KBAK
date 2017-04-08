package com.hackaton2017.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kanstantsin_Tolstsik on 4/4/2017.
 */
@Entity
public class Job {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private JobType jobType;

    @Enumerated(EnumType.ORDINAL)
    private Shop shop;

    private String url;

    private Boolean isCompleted;

    @OneToMany(mappedBy = "job")
    private List<Goal> goals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobType=" + jobType +
                ", shop=" + shop +
                ", url='" + url + '\'' +
                ", isCompleted=" + isCompleted +
                ", goals=" + goals +
                '}';
    }
}