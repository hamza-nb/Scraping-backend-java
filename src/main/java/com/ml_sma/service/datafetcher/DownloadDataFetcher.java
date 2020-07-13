package com.ml_sma.service.datafetcher;

import com.ml_sma.DAO.TweetsRepository;
import com.ml_sma.entity.Tweets;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DownloadDataFetcher implements DataFetcher<List<Tweets>> {

    @Autowired
    private TweetsRepository tweetsRepository;
    @Override
    public List<Tweets> get(DataFetchingEnvironment dataFetchingEnvironment) {

        return tweetsRepository.findAll();
    }
}
