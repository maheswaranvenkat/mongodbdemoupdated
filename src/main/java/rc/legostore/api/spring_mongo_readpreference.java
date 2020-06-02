package rc.legostore.api;

import com.mongodb.DBCollection;
import com.mongodb.ReadPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import rc.legostore.model.LegoSet;

import java.util.Arrays;
import java.util.List;

@Configuration
public class spring_mongo_readpreference {
    @Autowired
    private MongoTemplate mongoTemplate;
    public static final List<Class> coll = Arrays.asList(LegoSet.class);

    public void method() {
        coll.forEach(collection -> {
            DBCollection dbCollection = (DBCollection) mongoTemplate.getCollection(mongoTemplate.getCollectionName(collection));
            dbCollection.setReadPreference(ReadPreference.secondaryPreferred());
        });
    }
}
