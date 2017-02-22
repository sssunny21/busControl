package bus.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import bus.dto.Bus;
@Service
public class BusService {
	public String validateBeforeInsert(Bus bus) throws Exception {
		String s = bus.getBus_num();
		if (StringUtils.isBlank(s))
			return "차량번호를 입력하세요.";
		
		int d = bus.getLimit_passenger();
		if (d == 0)
			return "탑승인원을 입력하세요.";

		d = bus.getIntro_year();
		if (d == 0)
			return "도입년도를 입력하세요.";
		
		if (d < 1950 || d > 2017)
			return "1950년 이후 도입만 등록이 가능합니다.";

		s = bus.getState();
		if (StringUtils.isBlank(s))
			return "상태를 입력하세요.";
		return null;
	}
	
	public String validateBeforeUpdate(Bus bus) throws Exception {
		String s = bus.getBus_num();
		if (StringUtils.isBlank(s))
			return "차량번호를 입력하세요.";

		int d = bus.getLimit_passenger();
		if (d == 0)
			return "탑승인원을 입력하세요.";

		d = bus.getIntro_year();
		if (d == 0)
			return "도입년도를 입력하세요.";
		
		if (d < 1950 || d > 2017)
			return "1950년 이후 도입만 등록이 가능합니다.";

		s = bus.getState();
		if (StringUtils.isBlank(s))
			return "상태를 입력하세요.";

		return null;
	}
}
