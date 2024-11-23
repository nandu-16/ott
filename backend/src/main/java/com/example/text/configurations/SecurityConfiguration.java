package com.example.text.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;



import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import jakarta.servlet.http.HttpServletResponse;

import com.example.text.Utils.RSAKeyProperties;

import static org.springframework.security.config.Customizer.withDefaults;

import com.example.text.Services.TokenBlacklistService;

@Configuration
public class SecurityConfiguration {


    private final RSAKeyProperties keys;
    
    private final TokenBlacklistService tokenBlacklistService;

    public SecurityConfiguration(RSAKeyProperties keys, TokenBlacklistService tokenBlacklistService) {
        this.keys = keys;
        this.tokenBlacklistService = tokenBlacklistService; //private varible  config assign class constructer
    }


//    public SecurityConfiguration(RSAKeyProperties keys){
//        this.keys = keys;
//    }


    @Bean
    public PasswordEncoder passwordEncoder(){  //public acess modifier
        return new BCryptPasswordEncoder(); //function inbuilt spring
    }

    @Bean
    public AuthenticationManager authManager(UserDetailsService detailsService){
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(detailsService); //class obj daoprovider
        daoProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoProvider);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{  //auth auth
        http
            .csrf(csrf -> csrf.disable()) //csrf token divices is correct ayth
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/auth/**").permitAll();
                
//            	 auth.requestMatchers("/auth/**").authenticated();
                auth.requestMatchers("/admin/**").permitAll();
                auth.requestMatchers("/adminmovies/**").permitAll();
                auth.requestMatchers("/Movies/**").permitAll();
                auth.requestMatchers("/Logout/**").authenticated();
              
//                auth.requestMatchers("/Movies/**").permitAll();
                auth.requestMatchers("/Plans/**").permitAll();
                auth.requestMatchers("/uploads/**").permitAll();
//                auth.requestMatchers("/admin/**").hasRole("ADMIN");
//                auth.requestMatchers("/user/**").hasAnyRole("ADMIN","USER");
                auth.anyRequest().authenticated();
            });
           
        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                .and()
                .jwt().decoder(jwtDecoder()).and()
                .authenticationEntryPoint((request, response, authException) -> {
                	String token;
                	if(request.getHeader("Authorization")!=null)
                	{
                		token = request.getHeader("Authorization").replace("Bearer ", "");
                	}
                	else
                	{
                		token="";
                	}
                    System.out.println("jd");
                    if (tokenBlacklistService.isTokenBlacklisted(token)) {
                   
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write("Token is blacklisted");
                        return; 
                    }
//                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
                });
        http.sessionManagement(   //security  login logout token  session
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
               
        return http.build();
    }


    @Bean
    public JwtDecoder jwtDecoder(){  //jwt  encripted token
        return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder(){    //jwk object class name
        JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();  
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtConverter;
    }
   
}