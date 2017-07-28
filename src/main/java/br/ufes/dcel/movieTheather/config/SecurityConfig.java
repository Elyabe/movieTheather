package br.ufes.dcel.movieTheather.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.ufes.dcel.movieTheather.model.MyUserDetailService;
import br.ufes.dcel.movieTheather.repository.users;

@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private users users;
	
	@Autowired
    private MyUserDetailService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//Para toda requisição, é necessário estar autenticado
		http
			.csrf()
			.and()
				.authorizeRequests()
				//.antMatchers("/beer/novo/**").hasRole("CADASTRAR_CERVEJA") Role movida para o controller
					.anyRequest().authenticated()
				.and()
					.formLogin().loginPage("/login").permitAll()
				.and().
					logout().logoutRequestMatcher(new AntPathRequestMatcher("/movieTheather/logout"))
				.and()
					.rememberMe();
		
	}
	
/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(new UserDetailsService(){

				@Override
				public UserDetails loadUserByUsername(String username) 
										throws UsernameNotFoundException {
					return users.findOne(username);
				}
				
			});
		auth.inMemoryAuthentication()
			.withUser("elly").password("123").roles("ADMIN")
			.and()
			.withUser("maria").password("456").roles("");
	}*/
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/stylesheets/**")
			.antMatchers("/javascripts/**");
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}