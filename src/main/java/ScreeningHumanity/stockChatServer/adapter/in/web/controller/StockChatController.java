package ScreeningHumanity.stockChatServer.adapter.in.web.controller;

import static org.springframework.http.HttpHeaders.*;

import ScreeningHumanity.stockChatServer.adapter.in.web.vo.StockChatVo;
import ScreeningHumanity.stockChatServer.application.port.in.dto.StockChatInDto;
import ScreeningHumanity.stockChatServer.application.port.in.usecase.StockChatUseCase;
import ScreeningHumanity.stockChatServer.global.common.token.DecodingToken;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("/stockItem")
@RequiredArgsConstructor
public class StockChatController {

	private final StockChatUseCase stockChatUseCase;
	private final DecodingToken decodingToken;

	@PostMapping("/chat")
	public Mono<StockChatInDto> sendChat(
			@RequestHeader(AUTHORIZATION) String accessToken,
			@RequestBody StockChatVo vo
	) {
		String uuid = decodingToken.getUuid(accessToken);

		return stockChatUseCase.sendChat(StockChatInDto.getStockChatVo(vo, uuid));
	}

	@GetMapping(value = "/chat/{stockCode}",  produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<StockChatInDto> getChatsPagination(
			@PathVariable String stockCode,
			@RequestParam(required = false) String lastId
	) {
		return stockChatUseCase.getChatsPagination(stockCode, 5, lastId);
	}
}


