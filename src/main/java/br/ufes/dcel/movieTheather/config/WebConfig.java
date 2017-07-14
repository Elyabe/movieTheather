package br.ufes.dcel.movieTheather.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter
{
	@Override
	public void addViewControllers( ViewControllerRegistry registry )
	{
		// Solução do problema no login após o logout
		registry.addRedirectViewController("/", "/movieTheather");
	}
}
