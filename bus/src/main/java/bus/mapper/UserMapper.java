package bus.mapper;

import bus.dto.User;

public interface UserMapper {
	User selectById(String id);
}
