package bus.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bus.dto.Allocate;
import bus.dto.Bus;
import bus.dto.Driver;
import bus.dto.User;
import bus.mapper.AllocateMapper;
import bus.mapper.BusMapper;
import bus.mapper.DriverMapper;
import bus.mapper.OperateMapper;
import bus.service.DriverListToExcelFile;
import bus.service.DriverService;
import bus.service.UserService;

@Controller
public class DriverController {
	@Autowired DriverMapper driverMapper;
	@Autowired AllocateMapper allocateMapper;
	@Autowired BusMapper busMapper;
	@Autowired OperateMapper operateMapper;
	@Autowired DriverService driverService;
	@Autowired UserService userService;

	@RequestMapping(value="/driver/driverList.gnt", method=RequestMethod.GET)
    public String driverList(Model model) throws Exception {
		List<Driver> driverList = driverMapper.selectDriverList();
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		model.addAttribute("driverList", driverList);
        return "driver/driverList";
    }
	
	@RequestMapping(value="/driver/driverList.gnt", method=RequestMethod.POST, params="cmd=selectByState")
	public String busList2(@RequestParam("state") String state, Model model) throws Exception {
		List<Driver> driverList = driverMapper.selectByState(state);
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		model.addAttribute("state", state);
		model.addAttribute("driverList", driverList);
		return "driver/driverList";
	}
	
	@RequestMapping(value="/driver/driverList.gnt", method=RequestMethod.POST, params="cmd=nameSearch")
	public String busList3(@RequestParam("name") String name, Model model) throws Exception {
		List<Driver> driverList = driverMapper.searchByName(name);
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		model.addAttribute("driverList", driverList);
		model.addAttribute("name", name);
		return "driver/driverList";
	}
	
	@RequestMapping(value="/driver/driverList.gnt", method=RequestMethod.POST, params="cmd=excel")
	public String busList4(Driver driver, Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Driver> driverList = driverMapper.selectDriverList();
		DriverListToExcelFile.driverListToFile("driverList.xlsx", driverList, request, response);
		model.addAttribute("driverList", driverList);
		return "driver/driverList";
	}
	
	@RequestMapping(value="/driver/driverCreate.gnt", method=RequestMethod.GET)
    public String create(Model model) throws Exception {
        model.addAttribute("driver", new Driver());
        if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
        return "driver/driverCreate";
    }
	
	@RequestMapping(value="/driver/driverCreate.gnt", method=RequestMethod.POST)
	public String create(Driver driver, Model model, RedirectAttributes redirectAttributes) throws Exception{
		String message = driverService.validateBeforeInsert(driver);
		if (message == null){
			driverMapper.insertDriver(driver);
			redirectAttributes.addFlashAttribute("errorMsg", "저장했습니다.");
			return "redirect:/driver/driverList.gnt";
		}else{
			model.addAttribute("errorMsg", message);
			return "driver/driverCreate";
		}
	}
	
	@RequestMapping(value="/driver/driverEdit.gnt", method=RequestMethod.GET)
    public String edit(@RequestParam("driverid") Integer driverid, Model model) throws Exception {
    	Driver driver = driverMapper.selectByDriverId(driverid);
    	if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		model.addAttribute("driver", driver);
        return "driver/driverEdit";
    }
	
	@RequestMapping(value="/driver/driverEdit.gnt", method=RequestMethod.POST)
	public String edit(Driver driver, Model model, RedirectAttributes redirectAttributes) throws Exception{
		String message = driverService.validateBeforeInsert(driver);
		if (message == null){
			driverMapper.updateDriver(driver);
	    	if(!driver.getState().equals("근무")){
	    		allocateMapper.cancelByDriver(driver.getDriverid());
	    	}
			redirectAttributes.addFlashAttribute("errorMsg", "저장했습니다.");
			return "redirect:/driver/driverList.gnt";
		}else{
			model.addAttribute("errorMsg", message);
			return "driver/driverEdit";
		}
	}
	
	@RequestMapping("/driver/driverDelete.gnt")
    public String delete(Model model, @RequestParam("driverid") int driverid,RedirectAttributes redirectAttributes) throws Exception{
		driverMapper.deleteDriver(driverid);
		allocateMapper.deleteByDriver(driverid);
		redirectAttributes.addFlashAttribute("errorMsg", "삭제가 완료되었습니다.");
        return "redirect:/driver/driverList.gnt";
    }
	
	@RequestMapping(value="/driver/workingList.gnt", method=RequestMethod.GET)
    public String workingList(@RequestParam("busid") Integer busid, Model model) throws Exception {
    	List<Driver> driverList = driverMapper.selectByWorking();
    	if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
    	Date d = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    List<Driver> workingDriver = new ArrayList<>();
    	for(Driver dr : driverList){
    		if(!dr.getAllo_date().equals(sdf.format(d).toString())){
    			workingDriver.add(dr);
    		}else if(dr.getAllo_date().equals(sdf.format(d).toString()) && dr.isCancel_check() == true){
    			workingDriver.add(dr);
    		}
    	}
		model.addAttribute("driverList", workingDriver);
		model.addAttribute("busid", busid);
        return "driver/workingList";
    }
	
	@RequestMapping(value="/driver/cancel.gnt", method=RequestMethod.GET)
    public String cancel(@RequestParam("id") Integer allocateid, Model model) throws Exception {
    	allocateMapper.cancelAllocate(allocateid);
    	List<Bus> busList = busMapper.selectBusList();
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		model.addAttribute("busList", busList);
        return "bus/busList";
    }
	
	@RequestMapping(value="/driver/selectionDriver.gnt")
	public String selectionDriver2(@RequestParam("b_id") int busid, @RequestParam("d_id") int driverid,@RequestParam("c") String today, Model model){
		allocateMapper.insertAllocate(busid, driverid, today);
		Allocate allocate = allocateMapper.selectNewAllocate();
		operateMapper.insertOperate(allocate.getAllocateid(),today);
		return "redirect:/bus/busList.gnt";
	}
}
