package tr.org.lkd.lyk2015.camp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/* @formatter:off */
		http.authorizeRequests()

			// authentication
			.antMatchers("/admins/create","/basvuru", "/resources/**", "/applications/validate/**").permitAll()

			// authorization
			.antMatchers("/admins/**").hasAuthority("ADMIN")
			.antMatchers("/courses/create/**").hasAuthority("ADMIN")// courses (list) will be seen by any authenticated user
																	// but only admin will be able to create new course
			.antMatchers("/instructors/**").hasAnyAuthority("ADMIN", "INSTRUCTOR")
			.antMatchers("/instructors/create/**").hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
				.logout()

				// setting default path again (/login?logout) just to enable `permitAll`
				// explicitly. See http://stackoverflow.com/q/20532737/878361
				.logoutSuccessUrl("/login?logout").permitAll()
			.and()
				.formLogin()
				.loginPage("/login")
				.permitAll();
			/* @formatter:on */
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService);
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(this.userDetailsService);
		authProvider.setPasswordEncoder(this.passwordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.authProvider());
	}
}