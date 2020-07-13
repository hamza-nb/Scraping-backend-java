package com.ml_sma.DAO;

import com.ml_sma.entity.Tweets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TweetsRepository extends MongoRepository<Tweets, String> {
}
