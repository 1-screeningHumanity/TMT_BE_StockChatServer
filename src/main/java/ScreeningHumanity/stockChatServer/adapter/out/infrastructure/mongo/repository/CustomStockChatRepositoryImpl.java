package ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.repository;

import ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.entity.StockChatEntity;
import ScreeningHumanity.stockChatServer.application.port.out.dto.ChangeNickNameOutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.bson.Document;
import reactor.core.publisher.Flux;


@Repository
@RequiredArgsConstructor
public class CustomStockChatRepositoryImpl implements CustomStockChatRepository {

	private final ReactiveMongoTemplate mongoTemplate;

	@Override
	public void updateNickName(ChangeNickNameOutDto dto) {
		Query query = new Query(Criteria.where("nickName").is(dto.getBeforeNickName()));
		Update nickName = new Update().set("nickName", dto.getAfterNickName());

		mongoTemplate.updateMulti(query, nickName, StockChatEntity.class).subscribe();
	}

	@Override
	public Flux<StockChatEntity> getReactiveChat(String stockCode) {
		ChangeStreamOptions options = ChangeStreamOptions.builder()
				.filter(Aggregation.newAggregation(
						Aggregation.match(Criteria.where("stockCode").is(stockCode))
				))
				.build();

		return mongoTemplate.changeStream("stock_chat", options, Document.class)
				.mapNotNull(ChangeStreamEvent::getBody)
				.map(StockChatEntity::getReactiveStockChatEntity);
	}

}
