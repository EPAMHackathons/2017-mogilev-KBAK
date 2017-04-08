package com.epam.hackaton.service;

import com.epam.hackaton.helper.JobHelper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by Katsiaryna_Novikava on 4/8/2017.
 */
public class SendService {

    public HttpResponse sendPost(String url, String body) {

        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;

        try {

            HttpPost request = new HttpPost(url);

            StringEntity params = new StringEntity(body);

            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");
            request.setEntity(params);

            response = httpClient.execute(request);

        } catch (Exception ex) {

            System.out.println("Exception in sending post request" + ex);

        } finally {

            httpClient.getConnectionManager().shutdown();
        }

        return response;
    }

    public String sendGet(String url) {

        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        String result = null;

        try {
            HttpGet request = new HttpGet(url);

            response = httpClient.execute(request);

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            String line;
            while ((line = rd.readLine()) != null) {

                JSONObject obj = new JSONObject(line);

                result = obj.get("result").toString();
            }

        } catch (Exception ex) {

            System.out.println("Exception in sending get request" + ex);

        } finally {

            httpClient.getConnectionManager().shutdown();
        }

        return result;

    }
}
