package bus.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bus.dto.Allocate;
import bus.dto.BusStop;
import bus.dto.Operate;
import bus.dto.Route;
import bus.dto.User;
import bus.mapper.AllocateMapper;
import bus.mapper.OperateMapper;
import bus.service.UserService;

@Controller
public class OperateController {
	@Autowired AllocateMapper allocateMapper;
	@Autowired OperateMapper operateMapper;
	@Autowired UserService userService;
	
	@RequestMapping(value="/operate/operateList.gnt", method=RequestMethod.POST, params="cmd=start")
	public String start(@RequestParam(value="allocateid") int allocateid, Model model) throws Exception {
		allocateMapper.updateCheck(allocateid);
		Operate operate = operateMapper.selectNewOperate(allocateid);
		
		Date d = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Allocate> allocateList = allocateMapper.monitoring(sdf.format(d));
		List<BusStop> busStopList = operateMapper.selectBusStop();
		BusStop start = operateMapper.selectStart();
		List<Route> routeList = operateMapper.selectRoute();
		
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		
		model.addAttribute("operate", operate);
		model.addAttribute("allocateList", allocateList);
		model.addAttribute("busStopList", busStopList);
		model.addAttribute("start", start);
		model.addAttribute("routeList", routeList);

		return "operate/operateList";
	}
	
	@RequestMapping(value="/operate/operateList.gnt", method=RequestMethod.GET)
    public String operateList(Model model) throws Exception {
		Date d = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Allocate> allocateList = allocateMapper.monitoring(sdf.format(d));
		List<BusStop> busStopList = operateMapper.selectBusStop();
		BusStop start = operateMapper.selectStart();
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		model.addAttribute("allocateList", allocateList);
		model.addAttribute("busStopList", busStopList);
		model.addAttribute("start", start);
		return "operate/operateList";
    }

	@RequestMapping(value="/operate/operateList.gnt", method=RequestMethod.POST)
    public String operateList(@RequestParam(value="operateid") int operateid, @RequestParam(value="oper_count") int oper_count, 
    		@RequestParam(value="accu_passenger") int accu_passenger, Model model) {
		Operate before = operateMapper.selectBefore(operateid);
		Operate operate = new Operate();
		operate.setOperateid(operateid);
		operate.setOper_count(before.getOper_count() + oper_count);
		operate.setAccu_passenger(before.getAccu_passenger() + accu_passenger);
		operateMapper.updateOperate(operate);
		return "operate/operateList";
    }
	
	@RequestMapping(value="/operate/operateStatistics.gnt")
    public String operateStatistics(Model model) throws Exception {
		List<Operate> operateList = operateMapper.selectStatistics();
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		model.addAttribute("operateList", operateList);
		return "operate/operateStatistics";
    }

}
