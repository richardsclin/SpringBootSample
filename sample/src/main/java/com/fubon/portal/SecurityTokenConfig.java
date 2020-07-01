//package com.fubon.portal;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.fubon.security.JwtConfig;
//import com.fubon.security.JwtTokenAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private JwtConfig jwtConfig;
// 
//	@Override
//  	protected void configure(HttpSecurity http) throws Exception {
////		http.authorizeRequests().anyRequest().permitAll()  
////        .and().csrf().disable();
//		
//    	http
//		.csrf().disable()
//		    // make sure we use stateless session; session won't be used to store user's state.
//	 	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 	
//		.and()
//		    // handle an authorized attempts 
//		    .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)) 	
//		.and()
//		   // Add a filter to validate the tokens with every request
//		   .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
//		.cors().and()
//	     // authorization requests config
//		.authorizeRequests()
//		   // allow all who are accessing "auth" service
//		   .antMatchers(HttpMethod.GET, "/portal/api/sys/**").permitAll()
//		   .antMatchers(HttpMethod.GET, "/**/actuator/**").permitAll()
//		   .antMatchers(HttpMethod.POST, "/**/actuator/**").permitAll()
//		   // must be an admin if trying to access admin area (authentication is also required here)
//		   //.antMatchers("/gallery" + "/admin/**").hasRole("ADMIN")
//		   // Any other request must be authenticated
//		   .anyRequest().authenticated();
//	}
//	
//	@Bean
//  	public JwtConfig jwtConfig() {
//    	   return new JwtConfig();
//  	}
//}