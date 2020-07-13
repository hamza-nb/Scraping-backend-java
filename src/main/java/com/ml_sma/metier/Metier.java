package com.ml_sma.metier;

import com.ml_sma.entity.Tweets;
import com.mongodb.DBCursor;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

import java.io.IOException;
import java.util.List;

public interface Metier {
    public AgentController startAgent(String name, String classA, List list, String nameContainer) throws StaleProxyException;
    public List<Tweets> readTweets(String post) throws IOException, Exception;
    public AgentContainer createContainer(String nameContainer) throws ControllerException;
    public AgentController addAgentToContainer(AgentContainer container, String name, String classA, List list) throws StaleProxyException;
    public <Any> Any readMongo(String nameCollect);
    public Object sendRequest(String post, String url) throws Exception;
}
