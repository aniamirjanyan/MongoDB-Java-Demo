package com.example.mongoDemo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ValidationOptions;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Consumer;
@SpringBootApplication
public class MongoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDemoApplication.class, args);
		// creating MongoDB Java connection
		MongoClient mongoClient = new MongoClient();

		// ------ MongoClient mongoClient = MongoClients.create("mongodb://localhost");

		// creating/accessing database
		MongoDatabase database = mongoClient.getDatabase("database");
		mongoClient.listDatabaseNames()
				.forEach((Consumer<String>) System.out::println);

		// creating collection
		database.createCollection("customers");
		database.listCollectionNames().forEach((Consumer<String>)System.out::println);

		// or use getCollection, which will automatically create it if it doesn't exist
		MongoCollection<Document> collection = database.getCollection("testCollection");

		// creating capped collection of size 1MB
		database.createCollection("cappedCollection",
				new CreateCollectionOptions().capped(true).sizeInBytes(0x100000));

		// validate a document
		ValidationOptions collOptions = new ValidationOptions().validator(
				Filters.or(Filters.exists("email"), Filters.exists("phone")));
		database.createCollection("contacts",
				new CreateCollectionOptions().validationOptions(collOptions));

		// list collections
		for (String name : database.listCollectionNames()) {
			System.out.println(name);
		}

		// drop a collection
/*		MongoCollection<Document> collection2 = database.getCollection("contacts");
		collection.drop();
		*/

		// insert a document
		MongoCollection<BasicDBObject> coll = database.getCollection("mycoll", BasicDBObject.class);
		BasicDBObject document = new BasicDBObject("x", 1);
		coll.insertOne(document);
		document.append("x", 2).append("y", 3);
	}

}
