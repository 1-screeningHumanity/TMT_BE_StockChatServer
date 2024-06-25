package ScreeningHumanity.stockChatServer.adapter.out.infrastructure.mongo.repository;

import ScreeningHumanity.stockChatServer.application.port.out.dto.ChangeNickNameOutDto;

public interface CustomStockChatRepository {
	void updateNickName(ChangeNickNameOutDto dto);
}
