package com.epam.hackaton.helper;

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
}
