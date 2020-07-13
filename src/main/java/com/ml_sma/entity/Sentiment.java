package com.ml_sma.entity;


import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
@ToString
public class Sentiment implements Serializable {
    @Id
    private String id;
    private Float negative;
    private Float neutral;
    private Float positive;
    private String target;


    public Sentiment(Float negative, Float neutral, Float positive, String target) {
        this.negative = negative;
        this.neutral = neutral;
        this.positive = positive;
        this.target = target;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getNegative() {
        return negative;
    }

    public void setNegative(Float negative) {
        this.negative = negative;
    }

    public Float getNeutral() {
        return neutral;
    }

    public void setNeutral(Float neutral) {
        this.neutral = neutral;
    }

    public Float getPositive() {
        return positive;
    }

    public void setPositive(Float positive) {
        this.positive = positive;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

}