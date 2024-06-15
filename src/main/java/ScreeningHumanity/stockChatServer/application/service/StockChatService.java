package ScreeningHumanity.stockChatServer.application.service;

import ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.entity.StockChatEntity;
import ScreeningHumanity.stockChatServer.application.port.in.dto.StockChatInDto;
import ScreeningHumanity.stockChatServer.application.port.in.usecase.StockChatUseCase;
import ScreeningHumanity.stockChatServer.application.port.out.LoadStockChatPort;
import ScreeningHumanity.stockChatServer.application.port.out.SaveStockChatPort;
import ScreeningHumanity.stockChatServer.application.port.out.dto.StockChatOutDto;
import ScreeningHumanity.stockChatServer.domain.StockChat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockChatService implements StockChatUseCase {

	private final SaveStockChatPort saveStockChatPort;
	private final LoadStockChatPort loadStockChatPort;

	@Override
	public Mono<StockChatInDto> sendChat(StockChatInDto dto) {
		return StockChatInDto.getStockChatOutDto(saveStockChatPort.saveStockChat(
				StockChatOutDto.getStockChat(StockChat.sendChat(dto))));
	}

	@Override
	public Flux<StockChatInDto> getChats(String stockCode) {
		return StockChatInDto.getStockChats(
				StockChat.getStockChats(loadStockChatPort.getChats(stockCode)));
	}
}
