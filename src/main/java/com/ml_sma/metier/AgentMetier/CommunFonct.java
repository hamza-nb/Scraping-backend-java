package com.ml_sma.metier.AgentMetier;

import com.ml_sma.entity.Tweets;
import com.mongodb.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommunFonct {

    public static <Any> Any ScrapingPost(String tweets, String type, String url) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        ArrayList<Tweets> list = new ArrayList<>();
        String result = "";
        try {
            JSONArray jsonArray = null;
            HttpPost request = new HttpPost("http://127.0.0.1:9090/"+url);
            request.addHeader("Content-Type", "application/json");
            request.addHeader("Accept", "application/json");
            // add request headers
            request.setEntity(new StringEntity(tweets.toString(),"UTF-8"));

            response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            if(type.equals("scraping") || type.equals("algoProcess")){
                result = br.readLine();
            }
            else {

                while ((result = br.readLine()) != null) {
                    jsonArray = new JSONArray(result);
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject rec = (JSONObject) jsonArray.get(i);
                    list.add(new Tweets(rec.optString("tweets"), rec.optString("hashtags"),
                            Integer.parseInt(rec.optString("likes")), Long.parseLong(rec.optString("date")), Integer.parseInt(rec.optString("retweets")),
                            rec.optString("target")));
                }
            }
        } finally {
            assert response != null;
            response.close();
            httpClient.close();
        }
        if(type == "scraping")
            return((Any) result);
        else
            return ((Any) list);
    }

    public static void saveTweets(List<Tweets> tweets){
        DBCollection collection = getCollection("tweets");
        for (Tweets tw : tweets) {
            DBObject tweet = new BasicDBObject("tweets", tw.getTweets())
                    .append("date", tw.getDate())
                    .append("target", tw.getTarget())
                    .append("hashtags", tw.getHashtags())
                    .append("likes", tw.getLikes())
                    .append("retweets", tw.getRetweets())
                    .append("_class", "com.example.demo.entity.Tweets");
            collection.insert(tweet);
        }
    }
    public static DBCollection getCollection(String nameCollection){
        MongoClient mongoClient = new MongoClient();
        DB database = mongoClient.getDB("Scraping");
        DBCollection collection = database.getCollection(nameCollection);
        return collection;
    }
}
