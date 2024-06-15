package ScreeningHumanity.stockChatServer.domain;

import ScreeningHumanity.stockChatServer.application.port.in.dto.StockChatInDto;
import ScreeningHumanity.stockChatServer.application.port.out.dto.StockChatOutDto;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockChat {
	private String id;
	private String stockCode;
	private String message;
	private String sender;
	private Instant createAt;

	public static StockChat sendChat(StockChatInDto dto) {
		return StockChat.builder()
				.stockCode(dto.getStockCode())
				.message(dto.getMessage())
				.sender(dto.getSender())
				.createAt(Instant.now())
				.build();
	}

	private static StockChat getStockChat(StockChatOutDto dto) {
		return StockChat.builder()
				.stockCode(dto.getStockCode())
				.message(dto.getMessage())
				.sender(dto.getSender())
				.createAt(dto.getCreateAt())
				.build();
	}

	public static Flux<StockChat> getStockChats(Flux<StockChatOutDto> dto) {
		return dto.map(StockChat::getStockChat);
	}
}
