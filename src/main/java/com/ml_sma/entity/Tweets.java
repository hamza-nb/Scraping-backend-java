package com.ml_sma.entity;

import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@ToString
public class Tweets implements Serializable {
    @Id
    private String id;
    private String tweets;
    private String hashtags;
    private Integer likes;
    private Long date;
    private Integer retweets;
    private String target;

    public Tweets(String tweets, String hashtags, Integer likes, Long date, Integer retweets, String target) {
        this.tweets = tweets;
        this.hashtags = hashtags;
        this.likes = likes;
        this.date = date;
        this.retweets = retweets;
        this.target = target;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTweets() {
        return tweets;
    }

    public void setTweets(String tweets) {
        this.tweets = tweets;
    }

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getRetweets() {
        return retweets;
    }

    public void setRetweets(Integer retweets) {
        this.retweets = retweets;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
