package bus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import bus.dto.Allocate;

public interface AllocateMapper {
	List<Allocate> selectAllocate();
	List<Allocate> selectFinishAllocate();
	List<Allocate> monitoring(String today);
	List<Allocate> selectAllo_date(String allo_date);
	List<Allocate> searchName(String name);
	List<Allocate> searchBus_num(String bus_num);
	List<Allocate> selectByBusid(int busid);
	List<Allocate> selectByDriverid(int driverid);
	Allocate selectNewAllocate();
	void insertAllocate(@Param("busid") Integer busid,@Param("driverid") Integer driverid,@Param("today") String today);
	void updateAllocate(@Param("busid") Integer busid,@Param("driverid") Integer driverid,@Param("today") String today,@Param("allocateid") Integer allocateid);
	void cancelAllocate(int allocateid);
	void cancelByDriver(int driverid);
	void deleteByDriver(int driverid);
	void cancelByBus(int busid);
	void deleteByBus(int busid);
	void deleteAllocate(int driverid);
}
