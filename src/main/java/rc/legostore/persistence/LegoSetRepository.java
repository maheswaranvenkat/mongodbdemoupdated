package rc.legostore.persistence;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.Meta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import rc.legostore.model.LegoSet;
import rc.legostore.model.LegoSetDifficulty;

import java.util.Collection;

import static org.springframework.data.mongodb.core.query.Meta.CursorOption.SLAVE_OK;

@Repository
public interface LegoSetRepository extends MongoRepository<LegoSet, String>, QuerydslPredicateExecutor<LegoSet> {
    Collection<LegoSet> findAllByThemeContains(String theme, Sort sort);
    Collection<LegoSet> findAllByDifficultyAndNameStartsWith(LegoSetDifficulty difficulty, String name);
    @Meta(comment = "find by name", flags = { SLAVE_OK })
    Collection<LegoSet> findAllBy(TextCriteria textCriteria);

    @Meta(comment = "find by name", flags = { SLAVE_OK })
    @Query("{'delivery.deliveryFee' : {$lt : ?0}}")
    Collection<LegoSet> findAllByDeliveryPriceLessThan(int price);

    @Meta(comment = "find by name", flags = { SLAVE_OK })
    @Query("{'reviews.rating' : {$eq : 10}}")
    Collection<LegoSet> findAllByGreatReviews();

    @Meta(comment = "find by name", flags = { SLAVE_OK })
    @Query("{'paymentOptions.id' : ?0}")
    Collection<LegoSet> findByPaymentOptionId(String id);
}
