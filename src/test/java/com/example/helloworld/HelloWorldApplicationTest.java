package com.example.helloworld;



import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by avec on 08/07/16.
 */
public class HelloWorldApplicationTest {

    private static Logger LOG = LoggerFactory.getLogger(HelloWorldApplicationTest.class);

    // Run manually after server is up, to test responsetime
    public static void main(String[] args) throws IOException {

        StopWatch timer = new StopWatch();

        HttpClient client = HttpClients.createDefault();

        timer.start();

        // Choose how many requests you want
        for(int i=0; i< 1000; i++) {
            HttpGet request = new HttpGet("http://localhost:8080/hello-world"+"?name=yourname" + i);

            HttpResponse response = client.execute(request);

            // Get the response
            BufferedReader rd = new BufferedReader
                    (new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
//                LOG.info(line);
            }

        }
        timer.stop();
        System.out.println(timer.toString());


    }

}