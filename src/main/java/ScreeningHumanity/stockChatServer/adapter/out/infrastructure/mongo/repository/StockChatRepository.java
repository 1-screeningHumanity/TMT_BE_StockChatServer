package ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.repository;

import ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.entity.StockChatEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface StockChatRepository extends ReactiveMongoRepository<StockChatEntity, String>,
		CustomStockChatRepository {

	@Query(value = "{ 'stockCode': :#{#stockCode}, '_id': { '$lt': :#{#id} } }", sort = "{createAt: -1}")
	Flux<StockChatEntity> findByStockCodeAndIdLessThan(
			@Param("stockCode") String stockCode,
			Pageable pageable,
			@Param("id") ObjectId id);

	@Query(value = "{ 'stockCode': :#{#stockCode} }", sort = "{createAt: -1}")
	Flux<StockChatEntity> findByStockCodeAndIdLessThan(
			@Param("stockCode") String stockCode,
			Pageable pageable);
}
