package ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.entity;

import ScreeningHumanity.stockChatServer.application.port.out.dto.StockChatOutDto;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "stock_chat")
public class StockChatEntity {
	@Id
	private String id;
	private String stockCode;
	private String message;
	private String sender;
	private Instant createAt;

	@Builder
	public StockChatEntity(String stockCode, String message, String sender, Instant createAt) {
		this.stockCode = stockCode;
		this.message = message;
		this.sender = sender;
		this.createAt = createAt;
	}

	public static StockChatEntity getStockChatOutDto(StockChatOutDto dto) {
		return StockChatEntity.builder()
				.stockCode(dto.getStockCode())
				.message(dto.getMessage())
				.sender(dto.getSender())
				.createAt(dto.getCreateAt())
				.build();
	}
}
