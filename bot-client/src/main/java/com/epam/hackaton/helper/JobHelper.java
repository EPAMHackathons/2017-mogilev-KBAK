package com.epam.hackaton.helper;

import org.apache.http.HttpResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Katsiaryna_Novikava on 4/8/2017.
 */
public class JobHelper {

    public String createGoalsUrl(String jobId) {

        return new StringBuilder().append("http://localhost:8080/jobs/")
                .append(jobId)
                .append("/goals").toString();
    }

    public String createJobsRequestBody(String jobType, String shop, String url) {

        return new StringBuilder().append("{\"jobType\":\"")
                .append(jobType)
                .append("\",\"shop\":\"")
                .append(shop)
                .append("\",\"url\":\"")
                .append(url)
                .append("\",\"completed\": false}").toString();
    }

    public String createGoalsRequestBody(String goalType, String data) {

        return new StringBuilder().append("{\"goalType\":\"")
                .append(goalType)
                .append("\",\"data\":\"")
                .append(data)
                .append("\"}").toString();
    }
}
