package ScreeningHumanity.stockChatServer.application.port.out;

import ScreeningHumanity.stockChatServer.application.port.out.dto.StockChatOutDto;
import reactor.core.publisher.Mono;

public interface SaveStockChatPort {
	// Mono<StockChatOutDto>
	Mono<StockChatOutDto> saveStockChat(StockChatOutDto dto);
}
