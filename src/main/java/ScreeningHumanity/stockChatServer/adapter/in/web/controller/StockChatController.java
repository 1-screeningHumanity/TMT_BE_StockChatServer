package ScreeningHumanity.stockChatServer.adapter.in.web.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import ScreeningHumanity.stockChatServer.adapter.in.web.vo.in.StockChatInVo;
import ScreeningHumanity.stockChatServer.adapter.in.web.vo.out.StockChatOutVo;
import ScreeningHumanity.stockChatServer.application.port.in.dto.StockChatInDto;
import ScreeningHumanity.stockChatServer.application.port.in.usecase.StockChatUseCase;
import ScreeningHumanity.stockChatServer.global.common.token.DecodingToken;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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

	@GetMapping(value = "/reactive-chat/{stockCode}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<StockChatOutVo> getReactiveChats(
			@PathVariable String stockCode) {
		return StockChatOutVo.getStockChatInDto(stockChatUseCase.getReactiveChats(stockCode))
				.onErrorResume(e -> {
					log.error("Error is SSE stream ", e);
					return Flux.empty();
				} );
	}

	@GetMapping(value = "/chat/{stockCode}/{pageSize}")
	public Flux<StockChatOutVo> getChatsPagination(
			@PathVariable("stockCode") String stockCode,
			@PathVariable("pageSize") @Min(value = 1) Integer pageSize,
			@RequestParam(required = false) String lastId
	) {
		return StockChatOutVo.getStockChatInDto(
				stockChatUseCase.getChatsPagination(stockCode, pageSize, lastId));
	}
}