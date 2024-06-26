package ScreeningHumanity.stockChatServer.application.port.in.usecase;

import ScreeningHumanity.stockChatServer.application.port.in.dto.ChangeNickNameInDto;
import ScreeningHumanity.stockChatServer.application.port.in.dto.StockChatInDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StockChatUseCase {
	Mono<StockChatInDto> sendChat(StockChatInDto dto);

	Flux<StockChatInDto> getReactiveChats(String stockCode);

	Flux<StockChatInDto> getChatsPagination(String stockCode, int pageSize, String lastId);

	void changeNickName(ChangeNickNameInDto dto);
}
