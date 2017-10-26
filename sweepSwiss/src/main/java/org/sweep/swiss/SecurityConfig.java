package org.sweep.swiss;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.sweep.swiss.divers.Roles;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth) throws Exception{
		/* auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("client").password("123").roles("CLIENT");
		auth.inMemoryAuthentication().withUser("super").password("123").roles("ADMIN", "SUPER_ADMIN");
		auth.inMemoryAuthentication().withUser("nettoy").password("123").roles("NETTOYEUR");	*/	
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select email, password, activer from user where email = ?")
		.authoritiesByUsernameQuery("select email, role from user where email = ?")
		.rolePrefix("ROLE_");
	
	}	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/*").permitAll().antMatchers("/root/*").hasRole(Roles.Admin.toString()).antMatchers("/customer/*").hasRole(Roles.Client.toString()).antMatchers("/prestataire/*").hasRole(Roles.Nettoyeur.toString())
				.anyRequest()
					.authenticated()
						.and()
			.formLogin()
				.loginPage("/login.html")
				.usernameParameter("email").passwordParameter("password")
				.permitAll()
				.defaultSuccessUrl("/root/index.html");
	}
}
