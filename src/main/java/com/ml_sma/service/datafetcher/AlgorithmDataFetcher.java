package com.ml_sma.service.datafetcher;

import com.ml_sma.DAO.FileRepository;
import com.ml_sma.DAO.StatisticRepository;
import com.ml_sma.entity.Statistic;
import com.ml_sma.entity.fileProperties;
import com.ml_sma.metier.Metier;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import jade.wrapper.AgentContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlgorithmDataFetcher implements DataFetcher<Statistic>{

    @Autowired
    private Metier agentMetier;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private StatisticRepository statisticRepository;
    @Override
    public Statistic get(DataFetchingEnvironment dataFetchingEnvironment) {

        Object listAlgo = dataFetchingEnvironment.getArgument("data");
        try {


        AgentContainer container = agentMetier.createContainer("Algo-container");
        List<fileProperties> files = fileRepository.findAll();
        ArrayList algos = (ArrayList) listAlgo;
        for (int i = 0; i < algos.size()-2; i++) {
            System.err.println("- >>>>>>>>>>>>>--"+algos.get(i)+"--------------");
            List list = new ArrayList();
            list.add(algos.get(i).toString());  // the name of algorithm

            list.add(files.get(files.size() - 1).getNameFile());  // the name of file to read in MongoDB ~~
            list.add(algos.get(algos.size()-2)); // type train or predict
            list.add(algos.get(algos.size()-1));  // text to predict if is a predict type
            agentMetier.addAgentToContainer(container, algos.get(i).toString(), "com.ml_sma.metier.AgentMetier.AlgoAgent", list);
        }
        algos.remove(algos.get(algos.size()-1));
        algos.remove(algos.get(algos.size()-1));
            System.err.println(algos);
        boolean stateAgent = false ;

        while (!stateAgent) {
            boolean wait = false ;
            for (Object algo : algos) {
                if (container.getAgent(algo.toString()).getState().getCode() != 4) {
                    wait = true;
                }
            }
            if (!wait)
                stateAgent = true;
        }
        container.kill();
        }catch (Exception ignored){}
        System.err.println(statisticRepository.findAll().get(0).getId());

        return statisticRepository.findAll().get(0);
    }
}
