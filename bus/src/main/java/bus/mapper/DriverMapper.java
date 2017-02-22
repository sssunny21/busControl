package bus.mapper;

import java.util.List;

import bus.dto.Driver;

public interface DriverMapper {
	Driver selectByDriverId(int driverid);
	List<Driver> selectDriverList();
	List<Driver> selectByState(String state);
	List<Driver> selectByName(String name);
	List<Driver> selectByWorking();
	void insertDriver(Driver driver);
	void updateDriver(Driver driver);
	void deleteDriver(int driverid);
}
