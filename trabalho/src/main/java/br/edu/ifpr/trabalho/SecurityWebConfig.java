package br.edu.ifpr.trabalho;



import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	  public void configure(AuthenticationManagerBuilder builder) throws Exception {
	    builder.inMemoryAuthentication()
	        .withUser("admin").password("123").roles("USUARIO");
	  }
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable().authorizeRequests()
	    .anyRequest().authenticated()
        .and()
        .httpBasic();
	  }
}