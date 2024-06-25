package ScreeningHumanity.stockChatServer.application.port.in.dto;

import ScreeningHumanity.stockChatServer.adapter.in.kafka.vo.ChangeNickNameVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeNickNameInDto {
	private String beforeNickName;
	private String afterNickName;

	public static ChangeNickNameInDto getChangeNickNameVo(ChangeNickNameVo vo) {
		return ChangeNickNameInDto.builder()
				.afterNickName(vo.getAfterNickName())
				.beforeNickName(vo.getBeforeNickName())
				.build();
	}
}
