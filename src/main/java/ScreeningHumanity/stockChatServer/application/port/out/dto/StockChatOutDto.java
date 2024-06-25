package ScreeningHumanity.stockChatServer.application.port.out.dto;

import ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.entity.StockChatEntity;
import ScreeningHumanity.stockChatServer.domain.StockChat;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Getter
public class StockChatOutDto {
	private String id;
	private String stockCode;
	private String message;
	private String sender;
	private String nickName;
	private Instant createAt;

	@Builder
	public StockChatOutDto(String id, String stockCode, String message, String sender, String nickName, Instant createAt) {
		this.id = id;
		this.stockCode = stockCode;
		this.message = message;
		this.sender = sender;
		this.nickName = nickName;
		this.createAt = createAt;
	}

	public static StockChatOutDto getStockChat(StockChat stockChat) {
		return StockChatOutDto.builder()
				.stockCode(stockChat.getStockCode())
				.message(stockChat.getMessage())
				.sender(stockChat.getSender())
				.nickName(stockChat.getNickName())
				.createAt(stockChat.getCreateAt())
				.build();
	}

	public static Mono<StockChatOutDto> getStockChatEntityMono(Mono<StockChatEntity> stockChatEntity) {
		return stockChatEntity.map(entity -> StockChatOutDto.builder()
				.id(entity.getId())
				.stockCode(entity.getStockCode())
				.message(entity.getMessage())
				.sender(entity.getSender())
				.nickName(entity.getNickName())
				.createAt(entity.getCreateAt())
				.build());
	}

	public static Flux<StockChatOutDto> getStockChatEntityFlux(Flux<StockChatEntity> stockChatEntity) {
		return stockChatEntity.map(entity -> StockChatOutDto.builder()
				.id(entity.getId())
				.stockCode(entity.getStockCode())
				.message(entity.getMessage())
				.sender(entity.getSender())
				.nickName(entity.getNickName())
				.createAt(entity.getCreateAt())
				.build());
	}
}
