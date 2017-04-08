package com.epam.hackaton.rest;

import com.epam.hackaton.helper.JobHelper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


import java.io.*;
import java.util.Arrays;

/**
 * Created by Katsiaryna_Novikava on 4/8/2017.
 */
public class KvakRestClientPost {

    public static void main(String[] args) {

        HttpClient httpClient = new DefaultHttpClient();

        try {
            HttpPost request = new HttpPost("http://localhost:8080/jobs");
            StringEntity params = new StringEntity(new JobHelper().createJobsRequestBody("GETTING_ITEM_DETAILS", "WILDBERRIES", "http://example1.com"));
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            Header[] locations = response.getHeaders("Location");
            Header loc = Arrays.stream(locations).findFirst().get();
            String url = loc.getValue();

            System.out.println(url.substring(url.lastIndexOf('/') + 1));


            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            System.out.println("status:" + response.getStatusLine().getStatusCode());
//            response.getEntity();
            // handle response here...
        } catch (Exception ex) {
            System.out.println("Exception");
            // handle exception here
        } finally {
            httpClient.getConnectionManager().shutdown();
        }



       /* try {

            URL url = new URL("http://localhost:8080/jobs");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

//            String input = "{\"qty\":100,\"name\":\"iPad 4\"}";
//            String input = "{\"jobType\": \"GETTING_ITEM_DETAILS\",\"shop\": \"WILDBERRIES\",\"url\": \"http://example1.com\",\"completed\": false}";

            String input = new JobHelper().createJobsRequestBody("GETTING_ITEM_DETAILS", "WILDBERRIES", "http://example1.com");

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
*/
    }

}
