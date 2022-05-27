package it.uniroma3.siw.siwcatering.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static it.uniroma3.siw.siwcatering.model.Credentials.ADMIN_ROLE;;

@Configuration
@EnableWebSecurity
public class AuthenticationConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource datasource;
	
	/**
	 * Fornisce le configurazioni di autenticazione ed autorizzazione
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests() // Authorization paragraph
			.antMatchers(HttpMethod.GET, "/login", "/register", "/css/**", "/images/**").permitAll()
			.antMatchers(HttpMethod.POST, "/login", "/register").permitAll()
			.antMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
			.antMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
			.anyRequest().authenticated()
			
			.and().formLogin() // Login paragraph
			.loginPage("/login")
			.defaultSuccessUrl("/default", true)
			
			.and().logout() // Logout paragraph
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login?logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.clearAuthentication(true).permitAll();
		 	
	}
	
	/**
	 * Fornisce le query SQL per ottenere username e password
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication()
			.dataSource(datasource)
			.authoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username = ?")
			.usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username = ?");
		
	}
	
	/**
	 * Definisce un Bean usato per criptare e decriptare le password di Credentials
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
