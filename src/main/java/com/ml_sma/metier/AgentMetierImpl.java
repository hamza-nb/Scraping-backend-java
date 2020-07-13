package com.ml_sma.metier;


import com.ml_sma.DAO.FileRepository;
import com.ml_sma.entity.Tweets;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgentMetierImpl implements Metier {


    @Autowired
    private FileRepository fileRepository;

    @Override
    public AgentController startAgent(String name, String classA, List list, String nameContainer) throws StaleProxyException {
            Runtime runtime = Runtime.instance();
            ProfileImpl profile = new ProfileImpl();
            profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
            profile.setParameter(ProfileImpl.CONTAINER_NAME, nameContainer);
            AgentContainer container = runtime.createAgentContainer(profile);
            // name Agent , class Agent , Parameters(reference vers l'interface)
    //        String a = String.valueOf(Math.random());
            AgentController agentController = container
                    .createNewAgent(name , classA, new Object[]{list});
            agentController.start();
        return agentController;

    }

    @Override
    public List<Tweets> readTweets(String post) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ArrayList<Tweets> tweets = new ArrayList<>();
        JSONArray jsonArray = null;
        try {

            HttpPost request = new HttpPost("http://127.0.0.1:9090/read");
            request.addHeader("Content-Type", "application/json");
            request.addHeader("Accept", "application/json");
            // add request headers
            request.setEntity(new StringEntity(post.toString(), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                HttpEntity entity = response.getEntity();
                BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
                String result = "";
                String output = null;

                while ((result = br.readLine()) != null) {
                    jsonArray = new JSONArray(result);
                }


                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject rec = (JSONObject) jsonArray.get(i);
                    tweets.add(new Tweets(rec.optString("tweets"), rec.optString("hashtags"),
                            Integer.parseInt(rec.optString("likes")), Long.parseLong(rec.optString("date")), Integer.parseInt(rec.optString("retweets")),
                            rec.optString("target")));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        assert jsonArray != null;
        return tweets;
    }

    @Override
    public AgentContainer createContainer(String nameContainer) throws ControllerException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        profile.setParameter(ProfileImpl.CONTAINER_NAME, nameContainer);
        AgentContainer container = runtime.createAgentContainer(profile);
        container.start();
        return container;
    }

    @Override
    public AgentController addAgentToContainer(AgentContainer container, String name, String classA, List list) throws StaleProxyException {
        AgentController agentController = null;
            agentController = container.createNewAgent(name,classA,new Object[]{list});
            agentController.start();
        return agentController;
    }



    @Override
    public DBCursor readMongo(String nameCollect) {
        MongoClient mongoClient = new MongoClient();
        DB database = mongoClient.getDB("Scraping");
        DBCollection collection = database.getCollection(nameCollect);
        if (nameCollect.equals("data_statistics")){

        }
        else {

        }
        return collection.find();
    }

    @Override
    public Object sendRequest(String post, String url) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try {
            JSONArray jsonArray = null;
            HttpPost request = new HttpPost("http://127.0.0.1:9090/"+url);
            request.addHeader("Content-Type", "application/json");
            request.addHeader("Accept", "application/json");
            // add request headers
            request.setEntity(new StringEntity(post,"UTF-8"));

            response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            result = br.readLine();


        } finally {
            response.close();
            httpClient.close();
        }
        return result;



    }


}
