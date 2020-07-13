package com.ml_sma.service.datafetcher;

import com.ml_sma.metier.Metier;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessDataFetcher  implements DataFetcher<Boolean> {


    @Override
    public Boolean get(DataFetchingEnvironment dataFetchingEnvironment) {
        AgentContainer container;
        AgentController processing_controller;
        AgentController saver_controller;
       try {
            container = ScrapingDataFetcher.container;
           processing_controller = ScrapingDataFetcher.processing_controller;
           saver_controller = ScrapingDataFetcher.saver_controller;
           processing_controller.activate();
           // wait until the agent finish his job
            while (processing_controller.getState().getCode() != 4){
            }

            saver_controller.activate();
           while (saver_controller.getState().getCode() != 4){
           }
//           container.kill();

        }catch (Exception ignored){

        }

        return true;
    }
}
