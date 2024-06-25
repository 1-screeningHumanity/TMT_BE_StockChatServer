package ScreeningHumanity.stockChatServer.application.port.out;

import ScreeningHumanity.stockChatServer.application.port.out.dto.ChangeNickNameOutDto;
import ScreeningHumanity.stockChatServer.application.port.out.dto.StockChatOutDto;
import reactor.core.publisher.Mono;

public interface SaveStockChatPort {
	Mono<StockChatOutDto> saveStockChat(StockChatOutDto dto);

	void updateNickName(ChangeNickNameOutDto changeNickNameInDto);
}
