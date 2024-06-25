package ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.repository;

import ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.entity.StockChatEntity;
import ScreeningHumanity.stockChatServer.application.port.out.dto.ChangeNickNameOutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

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
}
