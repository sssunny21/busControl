package bus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import bus.dto.BusStop;
import bus.dto.Operate;
import bus.dto.Sequence;

public interface OperateMapper {
	//차고지 정보 조회
	BusStop selectStart();
	//방금 생성된 운행 id 조회
	Operate selectNewOperate(int allocateid);
	//운행id로 운행 테이블 조회
	Operate selectBefore(int operateid);
	
	//정류장 정보 조회
	List<BusStop> selectBusStop();
	//운행 현황 통계 조회
	List<Operate> selectStatistics();
	//루트 정보 조회
	List<Sequence> selectSequence();
	
	//운행 정보 insert
	void insertOperate(@Param("allocateid") int allocateid,@Param("today") String today);
	//운행 정보 update
	void updateOperate(Operate operate);
}
