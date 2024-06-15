package ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.persistance;

import ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.entity.StockChatEntity;
import ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.repository.StockChatRepository;
import ScreeningHumanity.stockChatServer.application.port.out.LoadStockChatPort;
import ScreeningHumanity.stockChatServer.application.port.out.SaveStockChatPort;
import ScreeningHumanity.stockChatServer.application.port.out.dto.StockChatOutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StockChatAdapter implements LoadStockChatPort, SaveStockChatPort {
	private final StockChatRepository stockChatRepository;

	@Override
	public Flux<StockChatOutDto> getChats(String stockCode) {
		return null;
	}

	@Override
	public Mono<StockChatOutDto> saveStockChat(StockChatOutDto dto) {
		return StockChatOutDto.getStockChatEntity(
				stockChatRepository.save(StockChatEntity.getStockChatOutDto(dto)));
	}
}
