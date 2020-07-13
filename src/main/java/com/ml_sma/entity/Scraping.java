package com.ml_sma.entity;

import lombok.ToString;

import java.util.List;

@ToString
public class Scraping {

    private List<String> tags ;
    private String beginDate ;
    private String endDate ;

    public Scraping(List<String> tags, String beginDate, String endDate) {
        this.tags = tags;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
