package com.matter.test.interfaces.repositories;

import com.matter.test.models.TradeResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITradeResultRepository extends MongoRepository<TradeResult, UUID> {
}
