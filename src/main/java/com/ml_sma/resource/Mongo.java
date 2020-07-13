package com.ml_sma.resource;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Mongo {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        DB database = mongoClient.getDB("Scraping");
//        DBCollection collection = database.getCollection(nameCollect);
    }
}
