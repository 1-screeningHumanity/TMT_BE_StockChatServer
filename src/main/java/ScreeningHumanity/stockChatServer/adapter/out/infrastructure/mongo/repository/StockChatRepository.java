package ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.repository;

import ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.entity.StockChatEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface StockChatRepository extends ReactiveMongoRepository<StockChatEntity, String> {
	Flux<StockChatEntity> findByStockCode(String stockCode);
}
