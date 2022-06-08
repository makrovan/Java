import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Sorts.ascending;
import static java.util.Arrays.asList;

public class MongoDB
{
    private final MongoDatabase database;

    public MongoDB ()
    {
        MongoClient mongoClient = MongoClients.create("mongodb://docker:mongopw@localhost:55000");
        database = mongoClient.getDatabase("local");
        //database.getCollection("Shops").drop();
        //database.getCollection("Products").drop();
    }
    public void addShop(String name) {
        MongoCollection<Document> collection = database.getCollection("Shops");
        try {
            InsertOneResult result = collection.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("name", name));
            System.out.println("Success! Inserted document id: " + result.getInsertedId());
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
    }

    public void addProduct(String name, int price)
    {
        MongoCollection<Document> collection = database.getCollection("Products");
        try {
            InsertOneResult result = collection.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("name", name)
                    .append("price", price));
            System.out.println("Success! Inserted document id: " + result.getInsertedId());
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
    }

    public void addProductToShop(String shopName, String productName)
    {
        MongoCollection<Document> collection = database.getCollection("Shops");

        collection.updateOne(
                Filters.eq("name", shopName),
                new Document().append(
                        "$push",
                        new Document("products", productName)
                )
        );
        //то же самое, что и: db.Shops.updateOne( { "name" : shopName }, { "$push" : { "products" : productName } });
        // { "_id" : 1, "arr" : [ { "v" : 1 } ] }
        //System.out.println(collection.find(eq("name", shopName)).first());
    }

    public void getFullStatic() {
        MongoCollection<Document> shops = database.getCollection("Shops");

        //shops.aggregate(asList()).forEach(System.out::println);
        //products.aggregate(asList()).forEach(System.out::println);

        /*System.out.println("");
        System.out.println("Товары дешевле 300: ");
        products.aggregate(asList(match(lt("price: ", 300)))).forEach(System.out::println);//eq, gt*/

        System.out.println();
        System.out.println("Статистика по магазинам:");
        Bson pipeline = lookup("Products", "products", "name", "productList");
        Bson unwindProducts = unwind("$productList");
        Bson groupName;

        System.out.println();
        groupName = group("$name", sum("Общее количество наименований товаров", 1));
        shops.aggregate(asList(pipeline, unwindProducts, groupName)).forEach(System.out::println);

        System.out.println();
        groupName = group("$name", avg("Cредняя цена товаров", "$productList.price"));
        shops.aggregate(asList(pipeline, unwindProducts, groupName)).forEach(System.out::println);

        System.out.println();
        Bson sortPrice = sort(ascending("productList.price: "));
        groupName = group("$name", last("Самый дорогой товар", "$productList.price"));
        shops.aggregate(asList(pipeline, unwindProducts, sortPrice, groupName)).forEach(System.out::println);

        System.out.println();
        groupName = group("$name", first("Самый дешевый товар", "$productList.price"));
        shops.aggregate(asList(pipeline, unwindProducts, sortPrice, groupName)).forEach(System.out::println);

        System.out.println();
        Bson matchPrice = match(lt("productList.price", 100));
        groupName = group("$name", sum("Количество товаров дешевле 100 рублей", 1));
        shops.aggregate(asList(pipeline, unwindProducts, matchPrice, groupName)).forEach(System.out::println);

        System.out.println();
        /*MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }*/
    }
}
