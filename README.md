# MongoDB-Java-Demo
MongoDB Java CRUD Example

* Connect to MongoDB
`MongoClient mongoClient = new MongoClient();`

* Creating or Accessing a Database
`MongoDatabase database = mongoClient.getDatabase("database");`

* Creating a collection
`database.createCollection("collection");`

* Or use `getCollection`, which will automatically create a collection if it doesn't exist
`MongoCollection<Document> collection = database.getCollection("testCollection");`

* Create Capped Collection (1MB size) 
`		database.createCollection("cappedCollection",
				new CreateCollectionOptions().capped(true).sizeInBytes(0x100000));`



[MongoDB Documentation](https://mongodb.github.io/mongo-java-driver/3.6/driver/tutorials/databases-collections/)
