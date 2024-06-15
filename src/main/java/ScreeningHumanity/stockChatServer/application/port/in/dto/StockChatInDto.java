package ScreeningHumanity.stockChatServer.application.port.in.dto;

import ScreeningHumanity.stockChatServer.adapter.in.web.vo.StockChatVo;
import ScreeningHumanity.stockChatServer.application.port.out.dto.StockChatOutDto;
import ScreeningHumanity.stockChatServer.domain.StockChat;
import lombok.Builder;
import lombok.Getter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Getter
public class StockChatInDto {
	private String stockCode;
	private String message;
	private String sender;

	@Builder
	public StockChatInDto(String stockCode, String message, String sender) {
		this.stockCode = stockCode;
		this.message = message;
		this.sender = sender;
	}

	public static StockChatInDto getStockChatVo(StockChatVo vo) {
		return StockChatInDto.builder()
				.stockCode(vo.getStockCode())
				.message(vo.getMessage())
				.sender(vo.getSender())
				.build();

	}

	public static StockChatInDto getStockChat(StockChat stockChat) {
		return StockChatInDto.builder()
				.stockCode(stockChat.getStockCode())
				.message(stockChat.getMessage())
				.sender(stockChat.getSender())
				.build();
	}

	public static Flux<StockChatInDto> getStockChats(Flux<StockChat> stockChat) {
		return stockChat.map(StockChatInDto::getStockChat);
	}

	public static Mono<StockChatInDto> getStockChatOutDto(
			Mono<StockChatOutDto> stockChatOutDtoMono) {return stockChatOutDtoMono.map(dto -> StockChatInDto.builder()
				.stockCode(dto.getStockCode())
				.message(dto.getMessage())
				.sender(dto.getSender())
				.build());
	}
}
