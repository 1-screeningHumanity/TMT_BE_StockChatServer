package ScreeningHumanity.stockChatServer.application.port.in.dto;

import ScreeningHumanity.stockChatServer.adapter.in.web.vo.StockChatVo;
import ScreeningHumanity.stockChatServer.application.port.out.dto.StockChatOutDto;
import ScreeningHumanity.stockChatServer.domain.StockChat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Getter
public class StockChatInDto {
	private String id;
	private String stockCode;
	private String message;
	private String sender;  // sender uuid
	private ZonedDateTime createAt;

	@Builder
	public StockChatInDto(String id, String stockCode, String message, String sender, ZonedDateTime createAt) {
		this.id = id;
		this.stockCode = stockCode;
		this.message = message;
		this.sender = sender;
		this.createAt = createAt;
	}

	public static StockChatInDto getStockChatVo(StockChatVo vo, String senderUuid) {
		return StockChatInDto.builder()
				.stockCode(vo.getStockCode())
				.message(vo.getMessage())
				.sender(senderUuid)
				.build();

	}

	public static StockChatInDto getStockChat(StockChat stockChat) {
		return StockChatInDto.builder()
				.id(stockChat.getId())
				.stockCode(stockChat.getStockCode())
				.message(stockChat.getMessage())
				.sender(stockChat.getSender())
				.createAt(stockChat.getCreateAt().atZone(ZoneId.of("Asia/Seoul")))
				.build();
	}

	public static Flux<StockChatInDto> getStockChats(Flux<StockChat> stockChat) {
		return stockChat.map(StockChatInDto::getStockChat);
	}

	public static Mono<StockChatInDto> getStockChatOutDto(Mono<StockChatOutDto> stockChatOutDtoMono) {
		return stockChatOutDtoMono.map(dto -> StockChatInDto.builder()
				.id(dto.getId())
				.stockCode(dto.getStockCode())
				.message(dto.getMessage())
				.sender(dto.getSender())
				.createAt(dto.getCreateAt().atZone(ZoneId.of("Asia/Seoul")))    // Instant to ZonedDateTime
				.build());
	}
}
