package bus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import bus.dto.Allocate;

public interface AllocateMapper {
	List<Allocate> selectAllocate();
	List<Allocate> selectFinishAllocate();
	List<Allocate> monitoring(String today);
	List<Allocate> selectAllo_date(String allo_date);
	List<Allocate> selectName_Bus_num(@Param("name") String name, @Param("bus_num") String bus_num);
	List<Allocate> selectByBusid(int busid);
	List<Allocate> selectByDriverid(int driverid);
	Allocate selectNewAllocate();
	void insertAllocate(@Param("busid") Integer busid,@Param("driverid") Integer driverid,@Param("today") String today);
	void updateCheck(int allocateid);
	void deleteAllocate(int driverid);
}
