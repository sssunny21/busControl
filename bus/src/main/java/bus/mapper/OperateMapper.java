package bus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import bus.dto.BusStop;
import bus.dto.Operate;
import bus.dto.Route;

public interface OperateMapper {
	void insertOperate(@Param("allocateid") int allocateid,@Param("today") String today);
	List<BusStop> selectBusStop();
	BusStop selectStart();
	List<Operate> selectStatistics();
	List<Route> selectRoute();
	void updateOperate(Operate operate);
	Operate selectNewOperate(int allocateid);
	Operate selectBefore(int operateid);
}
