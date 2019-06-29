package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	// connecting to the database
	@Bean
	public AuthenticationProvider authProvider() {
		// responsible to talk to database
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		// configuration talks to service and service talks to dao
		provider.setUserDetailsService(userDetailsService);
		// password encoder
		// provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;

	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/login", "/buy").permitAll().and().authorizeRequests()
				.antMatchers("/profile").authenticated().and().authorizeRequests().antMatchers("/admin/**")
				.hasAuthority("ADMIN").and().authorizeRequests().antMatchers("/employer/**").hasAuthority("EMPLOYER")
				.and().authorizeRequests().antMatchers("/affiliate/**").hasAuthority("AFFILIATE").and()
				.authorizeRequests().antMatchers("/customer/**").hasAuthority("CUSTOMER").and().formLogin()
				.loginPage("/login").permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logouts")).logoutSuccessUrl("/logout-success")
				.permitAll();
	}

}
