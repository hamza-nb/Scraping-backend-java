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

import java.util.List;

@Component
public class PcaDataFetcher implements DataFetcher<Object> {

    @Autowired
    private Metier metier ;
    @Autowired
    private FileRepository repository;

    @Override
    public Object get(DataFetchingEnvironment dataFetchingEnvironment) {
        Object ob = null;
        JsonObject jsonObject = null;
        try {
            List<fileProperties> files = repository.findAll();
            JSONObject js = new JSONObject();
            js.put("fileName",files.get(files.size() - 1).getNameFile());
            ob =  metier.sendRequest(js.toString() ,"pca") ;
            jsonObject = new JsonParser().parse(ob.toString()).getAsJsonObject();



            System.err.println(ob.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
