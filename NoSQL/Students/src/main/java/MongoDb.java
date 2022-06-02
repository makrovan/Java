import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.*;

import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class MongoDb {
    // Создаем коллекцию
    private final MongoCollection<Document> collection;

    public MongoDb(ArrayList<Student> students) {
        MongoClientURI uri = new MongoClientURI("mongodb://docker:mongopw@localhost:55005");
        //MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017");
        MongoClient mongoClient = new MongoClient(uri);
        //mongoClient = new MongoClient("localhost", 55004);
        MongoDatabase database = mongoClient.getDatabase("local");
        collection = database.getCollection("Students");
        // Удалим все документы из коллекции
        collection.drop();
        students.forEach(student -> {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString;
            try {
                jsonString = objectMapper.writeValueAsString(student);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            Document doc = Document.parse(jsonString);
            collection.insertOne(doc);
        });
    }


    public long getStudentsCount() {
        return collection.countDocuments();
    }

    public long getStudentCountOlder40(){
        BsonDocument query = BsonDocument.parse("{age: {$gt: 40}}");
        return collection.countDocuments(query);
        /*BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("$gt", 40);*/
        /*FindIterable<Document> cursor = collection.find(query);
        cursor.forEach((Consumer<Document>) System.out::println);
        int i = 0;
        // Getting the iterator
        Iterator it = cursor.iterator();
        while (it.hasNext()) {
            it.next();
            i++;
        }
        return i;*/
    }

    public String getYoungestStudentName(){
        Document doc = collection.find().sort(ascending("age")).first();
        assert doc != null;
        return (String) doc.get("name");
    }

    public ArrayList<String> getOldestStudentCourses(){
        //если самых старых студентов несколько, берем первого из них
        //в условии говорится, что надо вывести список одного самого старого студента!!!
        Document doc = collection.find().sort(descending("age")).first();
        assert doc != null;
        return (ArrayList<String>) doc.get("courses");
    }
}