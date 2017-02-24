package bus.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bus.dto.Allocate;
import bus.dto.BusStop;
import bus.dto.Operate;
import bus.dto.Sequence;
import bus.dto.User;
import bus.mapper.AllocateMapper;
import bus.mapper.OperateMapper;
import bus.service.OperateStatisticsToExcelFile;
import bus.service.UserService;

@Controller
public class OperateController {
	@Autowired AllocateMapper allocateMapper;
	@Autowired OperateMapper operateMapper;
	@Autowired UserService userService;
	
	//운행할 배차완료 버스 리스트, 지도 정보
	@RequestMapping(value="/operate/operateList.gnt")
    public String operateList(Model model) throws Exception {
		Date d = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Allocate> allocateList = allocateMapper.monitoring(sdf.format(d));
		List<BusStop> busStopList = operateMapper.selectBusStop();
		BusStop start = operateMapper.selectStart();
		List<Sequence> sequenceList = operateMapper.selectSequence();
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		model.addAttribute("allocateList", allocateList);
		model.addAttribute("busStopList", busStopList);
		model.addAttribute("start", start);
		model.addAttribute("sequenceList", sequenceList);
		return "operate/operateList";
    }

	//운행 후 정보 저장
	@RequestMapping(value="/operate/operateList.gnt", method=RequestMethod.POST)
    public String operateList(@RequestParam(value="operateid") int operateid, @RequestParam(value="oper_count") int oper_count, 
    		@RequestParam(value="accu_passenger") int accu_passenger, Model model) {
		Operate before = operateMapper.selectBefore(operateid);
		Operate operate = new Operate();
		operate.setOperateid(operateid);
		operate.setOper_count(before.getOper_count() + oper_count);
		operate.setAccu_passenger(before.getAccu_passenger() + accu_passenger);
		System.out.println("카운트:"+before.getOper_count()+","+"누적"+before.getAccu_passenger());
		operateMapper.updateOperate(operate);
		return "operate/operateList";
    }
	
	//운행 현황 통계표
	@RequestMapping(value="/operate/operateStatistics.gnt")
    public String operateStatistics(Model model) throws Exception {
		List<Operate> operateList = operateMapper.selectStatistics();
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		for(Operate o : operateList){
			o.setMoney(o.getAccu_passenger()*1250);
		}
		model.addAttribute("operateList", operateList);
		return "operate/operateStatistics";
    }
	
	//엑셀 다운로드(운행 현황)
	@RequestMapping(value="/operate/operateStatistics.gnt", method=RequestMethod.POST, params="cmd=excel")
	public String operateStatistics(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<Operate> operateList = operateMapper.selectStatistics();
		for(Operate o : operateList){
			o.setMoney(o.getAccu_passenger()*1250);
		}
		OperateStatisticsToExcelFile.operateStatisticsToFile("operateStatistics.xlsx", operateList, request, response);
        model.addAttribute("operateList", operateList);
		return "bus/busList";
	}

}
