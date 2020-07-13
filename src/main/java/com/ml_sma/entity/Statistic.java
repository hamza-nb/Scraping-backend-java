package com.ml_sma.entity;

import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
@ToString
public class Statistic implements Serializable {
    @Id
    private String id;
    private Float nbrTrain;
    private Float nbrTest;
    private Float nbrBeforeProcess;
    private Float nbrAfterProcess;
    private Float nbrFeatures;
    private String target;
    private List<AlgoPredicted> algoPredicted;
    private List<Sentiment> sentiments;

    public Statistic(Float nbrTrain, Float nbrTest, Float nbrBeforeProcess, Float nbrAfterProcess, Float nbrFeatures, String target, List<AlgoPredicted> algoPredicted, List<Sentiment> sentiments) {
        this.nbrTrain = nbrTrain;
        this.nbrTest = nbrTest;
        this.nbrBeforeProcess = nbrBeforeProcess;
        this.nbrAfterProcess = nbrAfterProcess;
        this.nbrFeatures = nbrFeatures;
        this.target = target;
        this.algoPredicted = algoPredicted;
        this.sentiments = sentiments;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getNbrTrain() {
        return nbrTrain;
    }

    public void setNbrTrain(Float nbrTrain) {
        this.nbrTrain = nbrTrain;
    }

    public Float getNbrTest() {
        return nbrTest;
    }

    public void setNbrTest(Float nbrTest) {
        this.nbrTest = nbrTest;
    }

    public Float getNbrBeforeProcess() {
        return nbrBeforeProcess;
    }

    public void setNbrBeforeProcess(Float nbrBeforeProcess) {
        this.nbrBeforeProcess = nbrBeforeProcess;
    }

    public Float getNbrAfterProcess() {
        return nbrAfterProcess;
    }

    public void setNbrAfterProcess(Float nbrAfterProcess) {
        this.nbrAfterProcess = nbrAfterProcess;
    }

    public Float getNbrFeatures() {
        return nbrFeatures;
    }

    public void setNbrFeatures(Float nbrFeatures) {
        this.nbrFeatures = nbrFeatures;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<AlgoPredicted> getAlgoPredicted() {
        return algoPredicted;
    }

    public void setAlgoPredicted(List<AlgoPredicted> algoPredicted) {
        this.algoPredicted = algoPredicted;
    }

    public List<Sentiment> getSentiments() {
        return sentiments;
    }

    public void setSentiments(List<Sentiment> sentiments) {
        this.sentiments = sentiments;
    }
}
