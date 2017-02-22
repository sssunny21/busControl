package bus.mapper;

import bus.dto.Bus;
import java.util.List;

public interface BusMapper {
	Bus selectByBusId(int busid);
	Bus selectByBusNum(String bus_num);
	List<Bus> selectBusList();
	List<Bus> selectByState(String state);
	List<Bus> searchByBusNum(String bus_num);
	void insertBus(Bus bus);
	void updateBus(Bus bus);
	void deleteBus(int busid);
}
