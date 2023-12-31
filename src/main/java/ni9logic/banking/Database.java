package ni9logic.banking;

// Mongo DB

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import io.github.cdimascio.dotenv.Dotenv;
import com.mongodb.client.*;
import com.mongodb.client.model.Sorts;

// Encryption / Decryption
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2;

// Date time
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Json Document in which we can store key value pairs
import org.bson.Document;

public class Database {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String connectionUrl = dotenv.get("DB_CONNECTION_STRING");
    private static final String databaseName = dotenv.get("DATABASE_NAME");
    private static final String CollectionUserDb = dotenv.get("DATABASE_COLLECTION_USER");
    private static final String CollectionTransactionsDb = dotenv.get("DATABASE_COLLECTION_TRANSACTIONS");
    private static final Argon2 argon2 = Argon2Factory.create();

    private static final MongoClient mongoClient;

    static {
        assert connectionUrl != null;
        mongoClient = MongoClients.create(connectionUrl);
    }

    private static final MongoDatabase database;

    static {
        assert databaseName != null;
        database = mongoClient.getDatabase(databaseName);
    }

    private static final MongoCollection<Document> collectionUser;

    static {
        assert CollectionUserDb != null;
        collectionUser = database.getCollection(CollectionUserDb);
    }

    private static final MongoCollection<Document> collectionTransaction;

    static {
        assert CollectionTransactionsDb != null;
        collectionTransaction = database.getCollection(CollectionTransactionsDb);
    }

    public static Document findUserByName(String Username) {
        Document query = new Document("Username", Username);
        boolean isFound = collectionUser.find(query).iterator().hasNext();

        if (isFound)
            return collectionUser.find(query).first();
        else
            return null;
    }

    public static List<Document> getRecentTransactionsByUsername(String username, int limit) {
        MongoCursor<Document> cursor = getRecentTransactionsCursorByUsername(username, limit);
        List<Document> transactions = new ArrayList<>();

        while (cursor.hasNext()) {
            Document transaction = cursor.next();
            transactions.add(transaction);
        }

        cursor.close();
        return transactions;
    }

    private static MongoCursor<Document> getRecentTransactionsCursorByUsername(String username, int limit) {
        MongoCollection<Document> collection = database.getCollection("Transactions");
        return collection.find(eq("Username", username))
                .sort(descending("_id"))
                .limit(limit)
                .iterator();
    }

    public static void createTransaction(String fromUser, String toUser, String transactionType,
                                         double transactedAmount) {
        // Formatting createdAt in the right format
        LocalDateTime createdAt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedCreatedAt = createdAt.format(formatter);

        // Creating a document out of the data
        Document userTransaction;
        if (toUser.equals("")) {

            // We are not appending to User in cases for withdraw and deposit
            userTransaction = new Document()
                    .append("Username", fromUser)
                    .append("Transaction Type", transactionType)
                    .append("Transaction At", formattedCreatedAt)
                    .append("Transaction Amount", transactedAmount);

        } else if (transactionType.equals("Transfer-Receive")) {
            userTransaction = new Document()
                    .append("Username", fromUser)
                    .append("From User", toUser)
                    .append("Transaction Type", transactionType)
                    .append("Transaction At", formattedCreatedAt)
                    .append("Transaction Amount", transactedAmount);
        } else {
            // We are not appending to User in cases for withdraw and deposit
            userTransaction = new Document()
                    .append("Username", fromUser)
                    .append("To User", toUser)
                    .append("Transaction Type", transactionType)
                    .append("Transaction At", formattedCreatedAt)
                    .append("Transaction Amount", transactedAmount);

            // Uploading the document in the respective collection
        }
        collectionTransaction.insertOne(userTransaction);
    }

    public static void createUser(String username, String password, String accountType, boolean isAdmin, String dob,
                                  String bankBal) {
        // Hashing password
        try {
            final String hashedPassword = argon2.hash(10, 65536, 1, password.toCharArray());
            // Formatting createdAt in the right format
            LocalDateTime createdAt = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedCreatedAt = createdAt.format(formatter);

            // Creating a document representing the user data
            Document userDocument = new Document().append("Username", username).append("Password", hashedPassword)
                    .append("Account_type", accountType).append("is_Admin", isAdmin).append("Date-Of-Birth", dob)
                    .append("Created-At", formattedCreatedAt).append("Balance", bankBal);

            // Inserting the user document into the collectionUser
            collectionUser.insertOne(userDocument);

            // Print the inserted document ID
        } finally {
            argon2.wipeArray(password.toCharArray());
        }
    }

    public static Document validateUser(String Username, String Password) {
        Document isFind = findUserByName(Username);
        if (isFind == null)
            return null;
        else if (argon2.verify(isFind.get("Password").toString(), Password.toCharArray()))
            return isFind;
        else
            return null;
    }

    public static boolean deleteUser(String Username) {
        DeleteResult deleteResult = collectionUser.deleteOne(eq("Username", Username));
        return deleteResult.getDeletedCount() > 0;
    }

    public static boolean updateUser(String Username, String Field_to_update, String New_Value) {
        // Getting Username and checking if the user exists or not
        Document User = findUserByName(Username);

        // If User is null it means that this user doesn't exist
        if (User == null)
            return false;

        // Creating Query
        Document query = new Document("Username", User.get("Username"));

        // This Updates the User
        Document update = new Document("$set", new Document(Field_to_update, New_Value));

        // Committing + getting call back
        UpdateResult result = collectionUser.updateOne(query, update);

        // Checking if it was modified or not
        return result.getModifiedCount() > 0;
    }

    public static FindIterable<Document> getAllUsers() {
        return collectionUser.find();
    }
}