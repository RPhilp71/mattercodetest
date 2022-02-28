package com.matter.test.interfaces.repositories;

import com.matter.test.models.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface IQuoteRepository extends MongoRepository<Quote, UUID> {
    void deleteBySymbol(String symbol);
    Stream<Quote> findBySymbol(String symbol);
}
