package org.sweep.swiss;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception{
		/* auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("client").password("123").roles("CLIENT");
		auth.inMemoryAuthentication().withUser("super").password("123").roles("ADMIN", "SUPER_ADMIN");
		auth.inMemoryAuthentication().withUser("nettoy").password("123").roles("NETTOYEUR");	*/	
	auth.jdbcAuthentication()
	.dataSource(dataSource)
	.usersByUsernameQuery("select email as principal, password as credentials, statut, etat from admin where email = ? and statut = true and etat = true")
	.authoritiesByUsernameQuery("select email as principal, role as role from admin where username = ? and statut = true and etat = true")
	.rolePrefix("ROLE_");
	
	auth.jdbcAuthentication()
	.dataSource(dataSource)
	.usersByUsernameQuery("select email as principal, password as credentials, statut, etat from client where email = ? and statut = true and etat = true")
	.authoritiesByUsernameQuery("select email as principal, role as role from client where username = ? and statut = true and etat = true")
	.rolePrefix("ROLE_");
	
	auth.jdbcAuthentication()
	.dataSource(dataSource)
	.usersByUsernameQuery("select email as principal, password as credentials, statut, etat from nettoyeur where email = ? and statut = true and etat = true")
	.authoritiesByUsernameQuery("select email as principal, role as role from nettoyeur where email = ? and statut = true and etat = true")
	.rolePrefix("ROLE_");
	
	auth.jdbcAuthentication()
	.dataSource(dataSource)
	.usersByUsernameQuery("select phone as principal, password as credentials, statut, etat from client where phone = ? and statut = true and etat = true")
	.authoritiesByUsernameQuery("select phone as principal, role as role from client where phone = ? and statut = true and etat = true")
	.rolePrefix("ROLE_");
	
	auth.jdbcAuthentication()
	.dataSource(dataSource)
	.usersByUsernameQuery("select phone as principal, password as credentials, statut, etat from nettoyeur where phone = ? and statut = true and etat = true")
	.authoritiesByUsernameQuery("select phone as principal, role as role from nettoyeur where phone = ? and statut = true and etat = true")
	.rolePrefix("ROLE_");
	}	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/bootstrap/**", "/angularjs/**", "/font/**", "/images/**").permitAll()
				.anyRequest()
					.authenticated()
						.and()
			.formLogin()
				.loginPage("/login.html")
				.permitAll()
				.defaultSuccessUrl("/index.html");
	}
}
