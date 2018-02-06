package com.trading.repository;

import com.trading.entity.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface CurrencyRepository extends MongoRepository<Currency, String> {
    List<Currency> findByName(String name);

    //@Query("select c from Currency u where c.name = :name and c.unixTime = :unixTime")
    @Query("{name : ?0, unixTime : ?1}")
    Currency findByNameAndUnixTime(String name, Long unixTime);

    @Query("{name : ?0, 'unixTime' : {$gt : ?1, $lt : ?2}}")
    Page<Currency> findByNameAndUnixTime(String name, Long startUnixTime, Long finishUnixTime, Pageable pageableRequest);

    @Query("{name : ?0}")
    Page<Currency> findByName(String name, Pageable pageableRequest);
}