package br.ufes.dcel.movieTheather.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufes.dcel.movieTheather.repository.users;

@Service
public class MyUserDetailService implements UserDetailsService  {
	private final users repository;

	@Autowired
	public MyUserDetailService( users repository) {
		this.repository = repository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	User curruser = repository.findByUsername(username);
    	
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPassword(), true, 
        		true, true, true, AuthorityUtils.createAuthorityList(String.valueOf(curruser.getRole())));
        
        System.out.println("ROLE: " + curruser.getRole());
        
        return user;
    }
    
}