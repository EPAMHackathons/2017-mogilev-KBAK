package com.hackaton2017.domain;

/**
 * Created by Kanstantsin_Tolstsik on 4/4/2017.
 */
public enum JobType {

    GETTING_ITEM_DETAILS("Getting item details using URL");

    private String jobDescription;

    JobType(final String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}