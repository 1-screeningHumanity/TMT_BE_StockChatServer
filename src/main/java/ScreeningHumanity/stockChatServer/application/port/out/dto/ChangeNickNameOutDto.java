package ScreeningHumanity.stockChatServer.application.port.out.dto;

import ScreeningHumanity.stockChatServer.application.port.in.dto.ChangeNickNameInDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeNickNameOutDto {

	private String beforeNickName;
	private String afterNickName;

	public static ChangeNickNameOutDto getChangeNickNameInDto(ChangeNickNameInDto dto) {
		return ChangeNickNameOutDto.builder()
				.afterNickName(dto.getAfterNickName())
				.beforeNickName(dto.getBeforeNickName())
				.build();
	}

}
