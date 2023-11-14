package ca.sheridancollege.assignment3mannatverma.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class TicketSecurity {

    private UserDetailsServiceImpl userDetailsService;

    /*@Bean
    public PasswordEncoder passwordEncoder(){
       PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       PasswordEncoder passwordEncoder) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http
                .authorizeHttpRequests((authz) -> authz


                        .requestMatchers(HttpMethod.GET, "/addTicket").permitAll()//hasRole("VENDOR")
                        .requestMatchers(HttpMethod.POST, "/add").permitAll()
                        .requestMatchers(HttpMethod.GET, "/registration").permitAll()//hasRole("GUEST")
                        .requestMatchers(HttpMethod.POST, "/registration").permitAll()//hasRole("GUEST")
                        .requestMatchers(HttpMethod.GET,"/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/images/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/images/background.jpg").permitAll()
                        .requestMatchers(HttpMethod.GET,"/css/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"view").permitAll()
                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers(HttpMethod.POST,"/editTicket").permitAll()//hasRole("VENDOR")
                        .requestMatchers(HttpMethod.GET,"/edit/{id}").permitAll()//hasRole("VENDOR")
                        .requestMatchers(HttpMethod.GET,"/delete/{id}").permitAll() //hasRole("VENDOR")

                        .anyRequest().authenticated()
                )
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/login")
                                .failureUrl("/login?failed")
                                .permitAll()
                )
                .logout((logout) ->
                        logout.deleteCookies("remove")
                                .invalidateHttpSession(false)
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .permitAll()
                )
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .accessDeniedPage("/accessDenied")
                );
        return http.build();
    }

}
