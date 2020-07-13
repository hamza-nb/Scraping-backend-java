package com.ml_sma.service.datafetcher;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ml_sma.DAO.FileRepository;
import com.ml_sma.entity.fileProperties;
import com.ml_sma.metier.Metier;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

@Component
public class GuessTextDataFetcher implements DataFetcher<Object> {

    @Autowired
    private Metier metier ;
    @Autowired
    private FileRepository repository;
    @Override
    public Object get(DataFetchingEnvironment dataFetchingEnvironment) {
        String text = dataFetchingEnvironment.getArgument("text");
        Object ob = null;
        try {
            JSONObject js = new JSONObject();
            js.put("text",text);
            ob = metier.sendRequest(js.toString(), "textGuess");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ob;
    }
}



//    @Override
//    public Object get(DataFetchingEnvironment dataFetchingEnvironment) {
//        String text = dataFetchingEnvironment.getArgument("text");
//        Object ob = null;
//        try {
//            JSONObject js = new JSONObject();
//            js.put("text",text);
//            ob = metier.sendRequest(js.toString(), "textGuess");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ob;
//    }
