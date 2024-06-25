package ScreeningHumanity.stockChatServer.adapter.in.kafka.listener;

import ScreeningHumanity.stockChatServer.adapter.in.kafka.vo.ChangeNickNameVo;
import ScreeningHumanity.stockChatServer.application.port.in.dto.ChangeNickNameInDto;
import ScreeningHumanity.stockChatServer.application.port.in.usecase.StockChatUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockChatListener {

	private final StockChatUseCase stockChatUseCase;
	private final ObjectMapper mapper;

	@KafkaListener(topics = "member-subscribe-changenickname")
	public void listenNickNameChange(String kafkaMessage) {
		try {
			ChangeNickNameVo vo = mapper.readValue(kafkaMessage, new TypeReference<>() {
			});

			stockChatUseCase.changeNickName(ChangeNickNameInDto.getChangeNickNameVo(vo));

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}