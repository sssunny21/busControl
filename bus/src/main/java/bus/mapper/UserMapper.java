package bus.mapper;

import bus.dto.User;

public interface UserMapper {
	//관리자 정보 조회
	User selectById(String id);
}
