package bus.mapper;

import java.util.List;

import bus.dto.Driver;

public interface DriverMapper {
	//기사 정보 수정용 id 조회
	Driver selectByDriverId(int driverid);
	//기사 정보 목록
	List<Driver> selectDriverList();
	//상태에 의한 검색
	List<Driver> selectByState(String state);
	//이름에 의한 검색
	List<Driver> searchByName(String name);
	//근무중인 기사 목록
	List<Driver> selectByWorking();
	
	//기사 생성
	void insertDriver(Driver driver);
	//기사 수정
	void updateDriver(Driver driver);
	//기사 삭제
	void deleteDriver(int driverid);
}
