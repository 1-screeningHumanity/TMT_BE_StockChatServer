package ScreeningHumanity.stockChatServer.adapter.in.kafka.vo;

import lombok.Getter;

@Getter
public class ChangeNickNameVo {
	private String beforeNickName;
	private String afterNickName;
}
