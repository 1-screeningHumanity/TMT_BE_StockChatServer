package ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.repository;

import ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.entity.StockChatEntity;
import ScreeningHumanity.stockChatServer.application.port.out.dto.ChangeNickNameOutDto;
import reactor.core.publisher.Flux;

public interface CustomStockChatRepository {
	void updateNickName(ChangeNickNameOutDto dto);

	Flux<StockChatEntity> getReactiveChat(String stockCode);
}
