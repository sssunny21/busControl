package bus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import bus.dto.Allocate;

public interface AllocateMapper {
	//배차테이블 전체 select
	List<Allocate> selectAllocate();
	//배차 완료 목록 
	List<Allocate> selectFinishAllocate();
	//운행할 배차 완료 목록
	List<Allocate> monitoring(String today);
	//배차 일자 검색
	List<Allocate> selectAllo_date(String allo_date);
	//기사 이름으로 배차 검색
	List<Allocate> searchName(String name);
	//차량번호로 배차 검색
	List<Allocate> searchBus_num(String bus_num);
	//버스정보 수정용 id select
	List<Allocate> selectByBusid(int busid);
	//방금 배차한 id select
	Allocate selectNewAllocate();
	
	//배차 목록 추가
	void insertAllocate(@Param("busid") Integer busid,@Param("driverid") Integer driverid,@Param("today") String today);
	//재배정 시 update
	void updateAllocate(@Param("busid") Integer busid,@Param("driverid") Integer driverid,@Param("today") String today,@Param("allocateid") Integer allocateid);
	//배차 취소
	void cancelAllocate(int allocateid);
	//기사 수정에 의한 배차 취소
	void cancelByDriver(int driverid);
	//기사 삭제에 의한 배차 취소
	void deleteByDriver(int driverid);
	//버스 수정에 의한 배차 취소
	void cancelByBus(int busid);
	//버스 삭제에 의한 배차 취소
	void deleteByBus(int busid);
}
