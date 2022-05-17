package lt.bit.PrekiuSandelys.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
public class BasicAuthConfiguration extends WebSecurityConfigurerAdapter {

	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserBuilder users=User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication()
			.withUser(users.username("gediminas").password("testas").roles("admin"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors()
			.and()
				.authorizeRequests()
				.anyRequest().authenticated()
			.and()
				.httpBasic()
			.and()
				.csrf().disable()
			
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
		
	}
	
	

}