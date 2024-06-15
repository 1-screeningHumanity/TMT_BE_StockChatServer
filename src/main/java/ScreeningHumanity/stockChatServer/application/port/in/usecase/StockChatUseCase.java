package ScreeningHumanity.stockChatServer.application.port.in.usecase;

import ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.entity.StockChatEntity;
import ScreeningHumanity.stockChatServer.application.port.in.dto.StockChatInDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StockChatUseCase {
	Mono<StockChatInDto> sendChat(StockChatInDto dto);

	Flux<StockChatInDto> getChats(String stockCode);
}
