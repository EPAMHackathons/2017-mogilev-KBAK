package com.epam.hackaton;

import com.epam.hackaton.helper.JobHelper;
import com.epam.hackaton.service.JobService;
import com.epam.hackaton.service.SendService;
import org.apache.http.HttpResponse;

import java.io.IOException;

/**
 * Created by Katsiaryna_Novikava on 4/8/2017.
 */
public class TestRunner {
    public static void main(String[] args) throws IOException {
        System.out.println("Good luck, KVAK!");

     /*   JobHelper jobHelper = new JobHelper();
        System.out.println(jobHelper.createJobsRequestBody("GETTING_ITEM_DETAILS", "WILDBERRIES", "http://example1.com"));
        System.out.println(jobHelper.createGoalsRequestBody("SIZE_WAITING", "XXL"));
        System.out.println(jobHelper.createGoalsUrl("14"));*/

     /*   JobService jobService = new JobService();
        jobService.createJobWithGoals("GETTING_ITEM_DETAILS",
                "WILDBERRIES",
                "http://example1.com",
                "SIZE_WAITING",
                "XXL");
        */
        SendService sendService = new SendService();
        System.out.println(sendService.sendGet("http://localhost:8080/check"));
    }
}
