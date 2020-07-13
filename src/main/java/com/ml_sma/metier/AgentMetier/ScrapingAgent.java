package com.ml_sma.metier.AgentMetier;

import com.mongodb.*;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.ml_sma.metier.AgentMetier.CommunFonct.ScrapingPost;

public class ScrapingAgent extends Agent {

    private Agent agent = this ;
    @Override
    protected void setup() {
        super.setup();
        System.err.println("-----------------AGENT-----------------------");


        ParallelBehaviour parallelBehaviour = new ParallelBehaviour();

        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                try {
                    System.err.println("-----------------AGENT--START---------------------");
                    List list = (ArrayList) agent.getArguments()[0];
                    JSONObject object = new JSONObject();
                    object.put("target",list.get(0));
                    object.put("dateDebut",list.get(1));
                    object.put("dateFin",list.get(2));
                    JSONArray jsonArray =  new JSONArray();
                    jsonArray.put(object);
                    String data =ScrapingPost(jsonArray.toString(), "scraping","collect");
                    MongoClient mongoClient = new MongoClient();
                    DB database = mongoClient.getDB("Scraping");
                    DBCollection collection = database.getCollection("fileProperties");
                    assert data != null;
                    DBObject tweetFile = new BasicDBObject("nameFile", data);
                    collection.insert(tweetFile);
                    ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
                    aclMessage.setContent(data);
                    aclMessage.addReceiver(new AID("processingAgent", AID.ISLOCALNAME));
                    send(aclMessage);
                    agent.doSuspend();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        addBehaviour(parallelBehaviour);

    }
}
