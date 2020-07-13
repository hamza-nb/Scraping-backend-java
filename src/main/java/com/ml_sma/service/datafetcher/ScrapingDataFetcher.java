package com.ml_sma.service.datafetcher;

import com.ml_sma.metier.Metier;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class ScrapingDataFetcher implements DataFetcher<Boolean> {
    @Autowired
    private Metier agentMetier;
    public static AgentContainer container;
    public static AgentController scraping_controller = null;
    public static AgentController processing_controller = null;
    public static AgentController saver_controller = null;
    @Override
    public Boolean get(DataFetchingEnvironment dataFetchingEnvironment) {

        Object scraping = dataFetchingEnvironment.getArgument("data");
        List<Object> list = new ArrayList<>();
        ((LinkedHashMap) scraping).forEach((key, value) -> {
            list.add(value);
        });
        try {
            container = agentMetier.createContainer("scraping_Process_container");
            scraping_controller = agentMetier.addAgentToContainer(container, "scraping", "com.ml_sma.metier.AgentMetier.ScrapingAgent", list);
            processing_controller = agentMetier.addAgentToContainer(container, "processingAgent", "com.ml_sma.metier.AgentMetier.PreProcessingAgent", null);
            saver_controller = agentMetier.addAgentToContainer(container, "saver", "com.ml_sma.metier.AgentMetier.SaverAgent", null);
            while (this.scraping_controller.getState().getCode() != 4) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
