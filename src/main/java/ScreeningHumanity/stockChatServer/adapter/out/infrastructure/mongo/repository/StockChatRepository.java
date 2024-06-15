package ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.repository;

import ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.entity.StockChatEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StockChatRepository extends ReactiveMongoRepository<StockChatEntity, String> {

}
