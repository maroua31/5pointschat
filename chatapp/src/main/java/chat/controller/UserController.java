package chat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import chat.repository.UserRepository;
import chat.model.User;



public class UserController {

	@Autowired
	private UserRepository utilisateurRepository;

    @Autowired
    AuthenticationManager authenticationManager;
	
	@RequestMapping(value="/authentifier",method=RequestMethod.GET)	
	public User authentification(@RequestParam String login, @RequestParam String psw){
		User user = utilisateurRepository.findByLoginAndPsw(login, psw);
		if(user!=null) {
		 Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                		login,             psw ));
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		return user ;
	 
	}
	
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "login";
	}

}
