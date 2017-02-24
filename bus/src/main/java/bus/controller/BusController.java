package bus.controller;

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
import bus.dto.User;
import bus.mapper.AllocateMapper;
import bus.mapper.BusMapper;
import bus.mapper.DriverMapper;
import bus.mapper.OperateMapper;
import bus.service.BusService;
import bus.service.UserService;
import bus.service.AllocateListToExcelFile;
import bus.service.BusListToExcelFile;

@Controller
public class BusController {
	@Autowired BusMapper busMapper;
	@Autowired DriverMapper driverMapper;
	@Autowired BusService busService;
	@Autowired AllocateMapper allocateMapper;
	@Autowired OperateMapper operateMapper;
	@Autowired UserService userService;
	
	//버스 리스트
	@RequestMapping(value="/bus/busList.gnt", method=RequestMethod.GET)
    public String busList(Model model) throws Exception {
		List<Bus> busList = busMapper.selectBusList();
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		model.addAttribute("busList", busList);
        return "bus/busList";
    }
	
	//상태 검색
	@RequestMapping(value="/bus/busList.gnt", method=RequestMethod.POST, params="cmd=selectByState")
	public String busList2(@RequestParam("state") String state, Model model) throws Exception {
		List<Bus> busList = busMapper.selectByState(state);
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		model.addAttribute("busList", busList);
		model.addAttribute("state", state);
		return "bus/busList";
	}
	
	//차량 번호 검색
	@RequestMapping(value="/bus/busList.gnt", method=RequestMethod.POST, params="cmd=busSearch")
	public String busList3(@RequestParam("bus_num") String bus_num, Model model) throws Exception {
		List<Bus> busList = busMapper.searchByBusNum(bus_num);
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		model.addAttribute("busList", busList);
		model.addAttribute("bus_num", bus_num);
		return "bus/busList";
	}
	
	//엑셀 다운로드(버스)
	@RequestMapping(value="/bus/busList.gnt", method=RequestMethod.POST, params="cmd=excel")
	public String busList4(Bus bus, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<Bus> busList = busMapper.selectBusList();
        BusListToExcelFile.busListToFile("busList.xlsx", busList, request, response);
        model.addAttribute("busList", busList);
		return "bus/busList";
	}

	//차량 생성
	@RequestMapping(value="/bus/busCreate.gnt", method=RequestMethod.GET)
    public String create(Model model) throws Exception {
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
        model.addAttribute("bus", new Bus());
        return "bus/busCreate";
    }
	
	@RequestMapping(value="/bus/busCreate.gnt", method=RequestMethod.POST)
	public String busCreate(Bus bus, Model model, RedirectAttributes redirectAttributes) throws Exception{
		Bus bus2=busMapper.selectByBusNum(bus.getBus_num());
		if(bus2==null){
			String message = busService.validateBeforeInsert(bus);
			if (message == null){
				busMapper.insertBus(bus);
				redirectAttributes.addFlashAttribute("errorMsg", "저장했습니다.");
				return "redirect:/bus/busList.gnt";
				
			}else{
				model.addAttribute("errorMsg", message);
			}
		}else{
			model.addAttribute("errorMsg", "해당 버스가 존재합니다");
		}
		return "bus/busCreate";
	}
	
	//차량 정보 수정
	@RequestMapping(value="/bus/busEdit.gnt", method=RequestMethod.GET)
    public String edit(@RequestParam("busid") Integer busid, Model model) throws Exception {
    	Bus bus = busMapper.selectByBusId(busid);
    	if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		model.addAttribute("bus", bus);
        return "bus/busEdit";
    }
	
	@RequestMapping(value="/bus/busEdit.gnt", method=RequestMethod.POST)
	public String edit(Bus bus, Model model, RedirectAttributes redirectAttributes) throws Exception{
		String message = busService.validateBeforeUpdate(bus);
		if (message == null){
			busMapper.updateBus(bus);
	    	if(!bus.getState().equals("운행")){
	    		allocateMapper.cancelByBus(bus.getBusid());
	    	}
			redirectAttributes.addFlashAttribute("errorMsg", "저장했습니다.");
			return "redirect:/bus/busList.gnt";
		}else{
			model.addAttribute("errorMsg", message);
			return "bus/busEdit";
		}
	}
	
	//차량 삭제
	@RequestMapping("/bus/busDelete.gnt")
    public String delete(Model model, @RequestParam("busid") int busid,RedirectAttributes redirectAttributes) throws Exception {
		busMapper.deleteBus(busid);
		allocateMapper.deleteByBus(busid);
		redirectAttributes.addFlashAttribute("errorMsg", "삭제가 완료되었습니다.");
        return "redirect:/bus/busList.gnt";
    }
	
	//배차 완료 목록 리스트
	@RequestMapping(value="/bus/allocateList.gnt", method=RequestMethod.GET)
    public String allocateList(Model model) throws Exception {
		List<Allocate> allocateList = allocateMapper.selectFinishAllocate();
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		model.addAttribute("allocateList", allocateList);
        return "bus/allocateList";
    }
	
	//배차 일자 검색
	@RequestMapping(value="/bus/allocateList.gnt", method=RequestMethod.POST, params="cmd=allocateSearch")
    public String allocateList(@RequestParam("selection") String selection, @RequestParam("search") String search, Model model) throws Exception {
		List<Allocate> allocateList;
		allocateList = allocateMapper.selectAllo_date(search);
		
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		model.addAttribute("selection", selection);
		model.addAttribute("search", search);
		model.addAttribute("allocateList", allocateList);
        return "bus/allocateList";
    }
	
	//배차 차량번호, 기사이름 검색
	@RequestMapping(value="/bus/allocateList.gnt", method=RequestMethod.POST, params="cmd=allocateSearch2")
    public String allocateList2(@RequestParam("selection") String selection, @RequestParam("search2") String search2, Model model) throws Exception {
		List<Allocate> allocateList;
		if(selection.equals("name")){
			allocateList = allocateMapper.searchName(search2);
		}else{
			allocateList = allocateMapper.searchBus_num(search2);
		}
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		model.addAttribute("selection", selection);
		model.addAttribute("search2", search2);
		model.addAttribute("allocateList", allocateList);
        return "bus/allocateList";
    }
	
	//배차 완료 목록 엑셀 다운로드
	@RequestMapping(value="/bus/allocateList.gnt", method=RequestMethod.POST, params="cmd=excel")
    public String allocateList2(Allocate allocate, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<Allocate> allocateList = allocateMapper.selectFinishAllocate();
		AllocateListToExcelFile.allocateListToFile("allocateList.xlsx", allocateList, request, response);
		model.addAttribute("allocateList", allocateList);
        return "bus/allocateList";
    }
	
}
