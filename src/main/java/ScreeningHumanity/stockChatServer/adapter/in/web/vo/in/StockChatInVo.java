package ScreeningHumanity.stockChatServer.adapter.in.web.vo.in;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class StockChatInVo {
	@NotNull
	private String stockCode;
	private String message;
	@NotNull
	private String nickName;
}