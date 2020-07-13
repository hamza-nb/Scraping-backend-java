package com.ml_sma.DAO;

import com.ml_sma.entity.fileProperties;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FileRepository  extends MongoRepository<fileProperties,String> {
}
