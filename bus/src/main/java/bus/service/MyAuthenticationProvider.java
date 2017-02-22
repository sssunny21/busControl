package bus.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import bus.dto.User;
import bus.mapper.UserMapper;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired UserMapper userMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = authentication.getCredentials().toString();
        return authenticate(id, password);
    }

    public Authentication authenticate(String id, String password) throws AuthenticationException {
        User user = userMapper.selectById(id);
        if (user == null) return null;
        if (user.getPassword().equals(encryptPasswd(password)) == false) return null;

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_1"));
        return new MyAuthenticaion(id, password, grantedAuthorities, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public static String encryptPasswd(String password) {
    	String SHA = "";
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(password.getBytes());
            byte[] passBytes = sh.digest();
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < passBytes.length; i++)
                sb.append(Integer.toString((passBytes[i]&0xff) + 0x100, 16).substring(1));
            SHA = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
        	SHA = null;
        }
        return SHA;
    }

    public class MyAuthenticaion extends UsernamePasswordAuthenticationToken {
        private static final long serialVersionUID = 1L;
        User user;

        public MyAuthenticaion (String id, String password, List<GrantedAuthority> grantedAuthorities, User user) {
            super(id, password, grantedAuthorities);
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}