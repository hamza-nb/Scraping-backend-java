package com.ml_sma.metier.AgentMetier;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import org.json.JSONObject;

import java.util.List;

import static com.ml_sma.metier.AgentMetier.CommunFonct.ScrapingPost;

public class AlgoAgent extends Agent {

    private Agent agent = this ;
    @Override
    protected void setup() {
        super.setup();

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                try {
                    List algo = (List) agent.getArguments()[0];
                    JSONObject object = new JSONObject();
                    object.put("algo",algo.get(0));
                    object.put("fileName",algo.get(1));
                    object.put("type",algo.get(2));
                    object.put("text",algo.get(3));
                    System.err.println(object.toString());
                    ScrapingPost(object.toString(), "algoProcess","AlgoProcess");
                    agent.doSuspend();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
