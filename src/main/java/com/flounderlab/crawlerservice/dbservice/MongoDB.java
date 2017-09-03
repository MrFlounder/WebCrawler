package com.flounderlab.crawlerservice.dbservice;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;

/**
 * Created by kzang on 2017/8/31.
 *
 */
public class MongoDB {

    public MongoDatabase database;

    MongoDB() {
        connect();
    }

    public void connect() {
        // Creating a Mongo client
        MongoClient mongo = new MongoClient( "localhost" , 27017 );

        // Creating Credentials
        MongoCredential credential;
        credential = MongoCredential.createCredential("flounder", "leetcode",
                "1234".toCharArray());
        System.out.println("Connected to the database successfully");

        // Accessing the database
        database = mongo.getDatabase("leetcode");
        System.out.println("Credentials ::"+ credential);
    }
}
