package bus.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import bus.dto.Driver;

@Service
public class DriverService {
	public String validateBeforeInsert(Driver driver) throws Exception {
		String s = driver.getName();
		if (StringUtils.isBlank(s))
			return "이름을 입력하세요.";

		s = driver.getBirth();
		if (StringUtils.isBlank(s))
			return "생일을 입력하세요.";

		s = driver.getJoin_date();
		if (StringUtils.isBlank(s))
			return "입사일자를 입력하세요.";

		s = driver.getState();
		if (StringUtils.isBlank(s))
			return "상태를 입력하세요.";

		return null;
	}
	
}
