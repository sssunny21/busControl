package bus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bus.dto.User;
import bus.service.UserService;

@Controller
public class HomeController {
	@Autowired UserService userService;

	@RequestMapping(value="/home/index.gnt")
	public String index(Model model) throws Exception {
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		return "home/index";
	}
	@RequestMapping(value="/home/login.gnt", method=RequestMethod.GET)
	public String login(Model model) {
		return "home/login";
	}

	@RequestMapping("/include/menu.gnt") 
	public String menu(Model model) throws Exception{
		if(UserService.getCurrentUser()!=null){
			User u = (User)UserService.getCurrentUser();
			String id = userService.printAuth(u);
			model.addAttribute("id",id);
		}
		return "include/menu";
	}

}
