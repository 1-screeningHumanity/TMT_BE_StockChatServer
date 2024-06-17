package ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.repository;

import ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.entity.StockChatEntity;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface StockChatRepository extends ReactiveMongoRepository<StockChatEntity, String> {
	@Query(value = "{ 'stockCode': ?0, '_id': { '$lt': ?1 } }", sort = "{createAt: -1}")
	Flux<StockChatEntity> findByStockCodeAndIdLessThan(
			String stockCode,
			ObjectId id,
			Pageable pageable);

	@Query(value = "{ 'stockCode': ?0 }", sort = "{createAt: -1}")
	Flux<StockChatEntity> findByStockCodeAndIdLessThan(
			String stockCode,
			Pageable pageable);
}
