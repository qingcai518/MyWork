package jp.bctech.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.bctech.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/images/**",
                "/css/**",
                "/javascript/**",
                "/webjars/**"
				);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// no login settings.
		http.authorizeRequests()
		.antMatchers("/", "/login", "/register").permitAll()
		.anyRequest().authenticated();
		
		// login settings.
		http.formLogin()
		.loginProcessingUrl("/login")
		.loginPage("/login")
		.failureHandler(new SampleAuthenticationFailureHandler())
		.defaultSuccessUrl("/menu")
		.usernameParameter("email")
		.passwordParameter("password");
		
		// logout settings.
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
		.logoutSuccessUrl("/login");
	}
	
	@Configuration
	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
		@Autowired
		UserDetailsServiceImpl userDetailsService;
		
		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
		}
	}
}
