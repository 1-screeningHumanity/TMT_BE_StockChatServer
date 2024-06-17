package ScreeningHumanity.stockChatServer.application.port.out;

import ScreeningHumanity.stockChatServer.application.port.out.dto.StockChatOutDto;
import reactor.core.publisher.Flux;

public interface LoadStockChatPort {
	Flux<StockChatOutDto> getChatsPagination(String stockCode, int pageSize, String lastId);
}
