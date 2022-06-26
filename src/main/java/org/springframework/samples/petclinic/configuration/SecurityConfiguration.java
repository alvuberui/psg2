package org.springframework.samples.petclinic.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String ADMIN = "admin";
	private static final String OWNER = "owner";
	private static final String CLINICOWNER = "clinicOwner";
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**","/webjars/**","/h2-console/**").permitAll()
				.antMatchers(HttpMethod.GET, "/","/oups","/support").permitAll()
				.antMatchers("/users/new").permitAll()
				.antMatchers("/custumer-agreement/**").permitAll()
				.antMatchers(HttpMethod.GET, "/","/oups","/changelog").permitAll()
				.antMatchers("/users/clinicOwner/new").permitAll()
				.antMatchers("/admin/**").hasAnyAuthority(ADMIN)
				.antMatchers("/owners/**").hasAnyAuthority(OWNER,ADMIN)
				.antMatchers("/petHotel/**").hasAnyAuthority(OWNER,ADMIN)
				.antMatchers("/adoptionRequest/**").hasAnyAuthority(OWNER,ADMIN)
				.antMatchers("/adoptionApplication/**").hasAnyAuthority(OWNER,ADMIN)
				.antMatchers("/pets/**").hasAnyAuthority(ADMIN)
				.antMatchers("/visits/**").hasAnyAuthority(ADMIN)
				.antMatchers("/pets/new").authenticated()
				.antMatchers("/vets/**").authenticated()
				.antMatchers("/causes/new/**").not().hasAnyAuthority(ADMIN)
				.antMatchers("/causes").hasAnyAuthority(OWNER,ADMIN)
				.antMatchers("/causes/**").hasAnyAuthority(OWNER,ADMIN)
				.antMatchers("/donations/new/**").not().hasAnyAuthority(ADMIN)
				.antMatchers("/donations").hasAnyAuthority(OWNER,ADMIN)
				.antMatchers("/donations/**").hasAnyAuthority(OWNER,ADMIN)
				.antMatchers("/api/**").authenticated()
				.antMatchers("/clinicOwner/plan").hasAnyAuthority(CLINICOWNER,ADMIN)
				.antMatchers("/user/updatePlan/{userId}/{plan}").hasAnyAuthority(CLINICOWNER,ADMIN)
				.antMatchers("/clinic/**").hasAnyAuthority(CLINICOWNER)
				.anyRequest().denyAll()
				.and()
				 	.formLogin()
				 	/*.loginPage("/login")*/
				 	.failureUrl("/login-error")
				.and()
					.logout()
						.logoutSuccessUrl("/"); 
                // Configuración para que funcione la consola de administración 
                // de la BD H2 (deshabilitar las cabeceras de protección contra
                // ataques de tipo csrf y habilitar los framesets si su contenido
                // se sirve desde esta misma página.
                http.csrf().ignoringAntMatchers("/h2-console/**");
                http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(
	       "select username,password,enabled "
	        + "from users "
	        + "where username = ?")
	      .authoritiesByUsernameQuery(
	       "select username, authority "
	        + "from authorities "
	        + "where username = ?")	      	      
	      .passwordEncoder(passwordEncoder());	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {	    
		return NoOpPasswordEncoder.getInstance();
	}
	
}


