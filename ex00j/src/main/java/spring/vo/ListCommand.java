package spring.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ListCommand {
	// 뷰에서 from, to 에 해당하는 데이터를 커맨드 객체에 담아서 컨트롤러로 전송
	// 문자열 => (기본타입) 으로 변환할 때는 자동으로 형변환이 일어난다.
	//         Date => 기본타입이 아니다.  => 형변환 문제가 발생
	//     스프링에서 제공하는 방식으로 형변환이 필요
	
	@DateTimeFormat(pattern="yyyyMMddHH")
	private Date from;
	@DateTimeFormat(pattern="yyyyMMddHH")
	private Date to;
	
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	
	
}
