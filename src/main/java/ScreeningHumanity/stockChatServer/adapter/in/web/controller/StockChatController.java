package ScreeningHumanity.stockChatServer.adapter.in.web.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import ScreeningHumanity.stockChatServer.adapter.in.web.vo.in.StockChatInVo;
import ScreeningHumanity.stockChatServer.adapter.in.web.vo.out.StockChatOutVo;
import ScreeningHumanity.stockChatServer.application.port.in.dto.StockChatInDto;
import ScreeningHumanity.stockChatServer.application.port.in.usecase.StockChatUseCase;
import ScreeningHumanity.stockChatServer.global.common.token.DecodingToken;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
	public Mono<StockChatOutVo> sendChat(
			@RequestHeader(AUTHORIZATION) String accessToken,
			@Valid @RequestBody StockChatInVo vo
	) {
		String uuid = decodingToken.getUuid(accessToken);

		return StockChatOutVo.getStockChatInDto(
				stockChatUseCase.sendChat(StockChatInDto.getStockChatVo(vo, uuid)));
	}

	@GetMapping(value = "/chat/{stockCode}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<StockChatOutVo> getChatsPagination(
			@PathVariable String stockCode,
			@RequestParam(required = false) String lastId
	) {
		return StockChatOutVo.getStockChatInDto(
				stockChatUseCase.getChatsPagination(stockCode, 5, lastId));
	}
}


