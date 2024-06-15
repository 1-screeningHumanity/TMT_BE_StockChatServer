package ScreeningHumanity.stockChatServer.adapter.in.web.controller;

import ScreeningHumanity.stockChatServer.adapter.in.web.vo.StockChatVo;
import ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.entity.StockChatEntity;
import ScreeningHumanity.stockChatServer.application.port.in.dto.StockChatInDto;
import ScreeningHumanity.stockChatServer.application.port.in.usecase.StockChatUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("/stockItem")
@RequiredArgsConstructor
public class StockChatController {

	private final StockChatUseCase stockChatUseCase;

	@PostMapping("/chat")
	public Mono<StockChatInDto> sendChat(
			@RequestBody StockChatVo vo
	) {
		return stockChatUseCase.sendChat(StockChatInDto.getStockChatVo(vo));
	}

}


