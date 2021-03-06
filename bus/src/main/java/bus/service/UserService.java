package bus.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bus.dto.User;

@Service
public class UserService {
	
	//세션 정보 print
	public String printAuth(User user) throws Exception {
		   String id = user.getId();
		   String print=null;
		   {print=id+" 관리자님 안녕하세요.";}
 
		   return print;
	}
	
	//현재 사용자 정보
	public static User getCurrentUser() { 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		if (authentication instanceof MyAuthenticationProvider.MyAuthenticaion) 
			return ((MyAuthenticationProvider.MyAuthenticaion) authentication).getUser(); 
		return null; 
	} 

	public static void setCurrentUser(User user) {
		((MyAuthenticationProvider.MyAuthenticaion) 
				SecurityContextHolder.getContext().getAuthentication()).setUser(user); 

	}
}
