package com.epam.hackaton.service;

import com.epam.hackaton.helper.JobHelper;
import org.apache.http.HttpResponse;

import java.util.Arrays;

/**
 * Created by Katsiaryna_Novikava on 4/8/2017.
 */
public class JobService {

    public void createJobWithGoals(String jobType,
                                   String shop,
                                   String url,
                                   String goalType,
                                   String data) {

        JobHelper jobHelper = new JobHelper();
        SendService sendService = new SendService();

        String jobRequestBody = jobHelper.createJobsRequestBody(jobType, shop, url);

        HttpResponse jobResponse = sendService.sendPost("http://localhost:8080/jobs", jobRequestBody);

        String jobUrl = Arrays.stream(jobResponse.getHeaders("Location"))
                .findFirst().get()
                .getValue();
        String jobId = jobUrl.substring(jobUrl.lastIndexOf('/') + 1);

        String goalsUrl = jobHelper.createGoalsUrl(jobId);
        String goalRequestBody = jobHelper.createGoalsRequestBody(goalType, data);

        HttpResponse goalsResponse = sendService.sendPost(goalsUrl, goalRequestBody);

    }
}
