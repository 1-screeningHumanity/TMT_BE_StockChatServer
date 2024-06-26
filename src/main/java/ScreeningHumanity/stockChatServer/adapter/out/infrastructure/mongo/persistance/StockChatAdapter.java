package ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.persistance;

import ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.entity.StockChatEntity;
import ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.repository.StockChatRepository;
import ScreeningHumanity.stockChatServer.application.port.out.LoadStockChatPort;
import ScreeningHumanity.stockChatServer.application.port.out.SaveStockChatPort;
import ScreeningHumanity.stockChatServer.application.port.out.dto.ChangeNickNameOutDto;
import ScreeningHumanity.stockChatServer.application.port.out.dto.StockChatOutDto;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StockChatAdapter implements LoadStockChatPort, SaveStockChatPort {

	private final StockChatRepository stockChatRepository;

	@Override
	public Flux<StockChatOutDto> getChatsPagination(String stockCode, int pageSize, String lastId) {
		Pageable pageable = PageRequest.of(0, pageSize);

		if (lastId == null || lastId.isEmpty()) {
			return StockChatOutDto.getStockChatEntityFlux(
					stockChatRepository.findByStockCodeAndIdLessThan(stockCode, pageable));
		} else {
			return StockChatOutDto.getStockChatEntityFlux(
					stockChatRepository.findByStockCodeAndIdLessThan(stockCode, pageable, new ObjectId(lastId)));
		}
	}

	@Override
	public Mono<StockChatOutDto> saveStockChat(StockChatOutDto dto) {
		return StockChatOutDto.getStockChatEntityMono(
				stockChatRepository.save(StockChatEntity.getStockChatOutDto(dto)));
	}

	@Override
	public void updateNickName(ChangeNickNameOutDto dto) {
		stockChatRepository.updateNickName(dto);
	}
}
