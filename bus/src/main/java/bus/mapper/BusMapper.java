package bus.mapper;

import bus.dto.Bus;
import java.util.List;

public interface BusMapper {
	//버스id에 의한 select
	Bus selectByBusId(int busid);
	//차량번호에 의한 select
	Bus selectByBusNum(String bus_num);
	//차량 정보 목록
	List<Bus> selectBusList();
	//상태에 의한 검색
	List<Bus> selectByState(String state);
	//차량번호에 의한 검색
	List<Bus> searchByBusNum(String bus_num);
	
	//차량 생성
	void insertBus(Bus bus);
	//차량 수정
	void updateBus(Bus bus);
	//차량 삭제
	void deleteBus(int busid);
}
