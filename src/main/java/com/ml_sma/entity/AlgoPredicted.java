package com.ml_sma.entity;


import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@ToString
public class AlgoPredicted  implements Serializable {

    private String algorithm ;
    private Float predict;

    public AlgoPredicted(String algorithm, Float predict) {
        this.algorithm = algorithm;
        this.predict = predict;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public Float getPredict() {
        return predict;
    }

    public void setPredict(Float predict) {
        this.predict = predict;
    }
}
