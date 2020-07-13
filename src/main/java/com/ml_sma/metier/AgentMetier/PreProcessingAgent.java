package com.ml_sma.metier.AgentMetier;

import com.ml_sma.entity.Tweets;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

import static com.ml_sma.metier.AgentMetier.CommunFonct.ScrapingPost;

public class PreProcessingAgent extends Agent {
    private Agent agent = this;

    @Override
    protected void setup() {
        super.setup();
        agent.doSuspend();
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage aclMessage = receive();
                if (aclMessage != null) {
                    try {
                        String data = aclMessage.getContent();
                        System.err.println("DATA NAME FILE " +data);
                        ArrayList<Tweets> tweets = ScrapingPost(data, "dd","process");
                        ACLMessage aclMessage2 = new ACLMessage(ACLMessage.REQUEST);
                        aclMessage2.setContentObject(tweets);
                        aclMessage2.addReceiver(new AID("saver", AID.ISLOCALNAME));
                        send(aclMessage2);
                        agent.doSuspend();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }
}
