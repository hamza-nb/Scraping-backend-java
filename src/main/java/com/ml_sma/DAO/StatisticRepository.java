package com.ml_sma.DAO;

import com.ml_sma.entity.Statistic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StatisticRepository extends MongoRepository<Statistic, String> {
}
