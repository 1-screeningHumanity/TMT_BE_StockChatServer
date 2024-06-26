package ScreeningHumanity.stockChatServer.application.service;

import ScreeningHumanity.stockChatServer.application.port.in.dto.ChangeNickNameInDto;
import ScreeningHumanity.stockChatServer.application.port.in.dto.StockChatInDto;
import ScreeningHumanity.stockChatServer.application.port.in.usecase.StockChatUseCase;
import ScreeningHumanity.stockChatServer.application.port.out.LoadStockChatPort;
import ScreeningHumanity.stockChatServer.application.port.out.SaveStockChatPort;
import ScreeningHumanity.stockChatServer.application.port.out.dto.ChangeNickNameOutDto;
import ScreeningHumanity.stockChatServer.application.port.out.dto.StockChatOutDto;
import ScreeningHumanity.stockChatServer.domain.StockChat;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockChatService implements StockChatUseCase {

	private final SaveStockChatPort saveStockChatPort;
	private final LoadStockChatPort loadStockChatPort;

	@Override
	public Mono<StockChatInDto> sendChat(StockChatInDto dto) {
		return Mono.fromCallable(() -> StockChat.sendChat(dto))
				.flatMap(stockChat -> StockChatInDto.getStockChatOutDto(
						saveStockChatPort.saveStockChat(StockChatOutDto.getStockChat(stockChat))));
	}

	@Override
	public Flux<StockChatInDto> getReactiveChats(String stockCode) {
		return StockChatInDto.getStockChatOutDtoFlux(loadStockChatPort.getReactiveChats(stockCode));
	}

	@Override
	public Flux<StockChatInDto> getChatsPagination(String stockCode, int pageSize, String lastId) {
		return StockChatInDto.getStockChats(
				StockChat.getStockChats(loadStockChatPort.getChatsPagination(stockCode, pageSize, lastId)));
	}

	@Override
	public void changeNickName(ChangeNickNameInDto dto) {
		saveStockChatPort.updateNickName(ChangeNickNameOutDto.getChangeNickNameInDto(dto));
	}
}
