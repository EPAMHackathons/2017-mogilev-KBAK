package com.epam.hackaton.helper;

import org.apache.http.HttpResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Katsiaryna_Novikava on 4/8/2017.
 */
public class JobHelper {

    public String createGoalsUrl(String jobId) {
//        http://localhost:8080/jobs/2/goals

        return new StringBuilder().append("http://localhost:8080/jobs/")
                .append(jobId)
                .append("/goals").toString();
    }

    public String createJobsRequestBody(String jobType, String shop, String url) {

//        {
//            "jobType": "GETTING_ITEM_DETAILS",
//                "shop": "WILDBERRIES",
//                "url": "http://example1.com",
//                "completed": false
//        }

        return new StringBuilder().append("{\"jobType\":\"")
                .append(jobType)
                .append("\",\"shop\":\"")
                .append(shop)
                .append("\",\"url\":\"")
                .append(url)
                .append("\",\"completed\": false}").toString();
    }

    public String createGoalsRequestBody(String goalType, String data) {
//        {
//            "goalType": "SIZE_WAITING",
//                "data": "XXL"
//        }
        return new StringBuilder().append("{\"goalType\":\"")
                .append(goalType)
                .append("\",\"data\":\"")
                .append(data)
                .append("\"}").toString();
    }

    public String getValue(HttpResponse response)  {

        String result = "";

        try {

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            String line;
            while ((line = rd.readLine()) != null) {

                JSONObject obj = new JSONObject(line);

                System.out.println("ob:"+obj.get("result"));
                result = obj.get("result").toString();
                System.out.println("res:"+result);
            }

        }catch (IOException ex){

            System.out.println("Error while checking a job");
        }

        return result;
    }
}
