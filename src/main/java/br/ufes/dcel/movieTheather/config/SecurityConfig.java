package br.ufes.dcel.movieTheather.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//Para toda requisição, é necessário estar autenticado
		http.csrf()
			.and()
				.authorizeRequests()
				.antMatchers("/movieTheather/addMovie/**").hasRole("CADASTRAR_CERVEJA") 
					.anyRequest().authenticated()
				.and()
					.formLogin().loginPage("/login").permitAll()
				.and().
					logout().logoutRequestMatcher(new AntPathRequestMatcher("/movieTheather/logout"))
				.and()
					.rememberMe();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("elly").password("123").roles("CADASTRAR_CERVEJA")
			.and()
			.withUser("maria").password("123").roles("");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/stylesheets/**")
			.antMatchers("/javascripts/**");
	}
	
}