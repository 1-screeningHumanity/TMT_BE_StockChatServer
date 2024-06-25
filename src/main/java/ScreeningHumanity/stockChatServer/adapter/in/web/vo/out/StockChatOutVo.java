package ScreeningHumanity.stockChatServer.adapter.in.web.vo.out;

import ScreeningHumanity.stockChatServer.application.port.in.dto.StockChatInDto;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockChatOutVo {
	private String id;
	private String stockCode;
	private String message;
	private String sender;  // sender uuid
	private String nickName;
	private ZonedDateTime createAt;


	public static StockChatOutVo getStockChatInDto(StockChatInDto dto) {
		return StockChatOutVo.builder()
				.id(dto.getId())
				.stockCode(dto.getStockCode())
				.message(dto.getMessage())
				.sender(dto.getSender())
				.nickName(dto.getNickName())
				.createAt(dto.getCreateAt())
				.build();
	}

	public static Mono<StockChatOutVo> getStockChatInDto(Mono<StockChatInDto> dto) {
		return dto.map(StockChatOutVo::getStockChatInDto);
	}

	public static Flux<StockChatOutVo> getStockChatInDto(Flux<StockChatInDto> dto) {
		return dto.map(StockChatOutVo::getStockChatInDto);
	}
}
