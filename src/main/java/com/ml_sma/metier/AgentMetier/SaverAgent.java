package com.ml_sma.metier.AgentMetier;

import com.ml_sma.entity.Tweets;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

import static com.ml_sma.metier.AgentMetier.CommunFonct.saveTweets;

public class SaverAgent extends Agent {

    private Agent agent = this;
    @Override
    protected void setup() {
        super.setup();
        agent.doSuspend();
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage aclMessage = receive();
                System.out.println("----------------> Start SaverAgent");
                if (aclMessage != null) {
                    try {
                        ArrayList<Tweets> tweets = (ArrayList<Tweets>) aclMessage.getContentObject();
                        saveTweets(tweets);
                        agent.doSuspend();
                    } catch (Exception ignored) {

                    }
                }


            }
        });

    }
}
