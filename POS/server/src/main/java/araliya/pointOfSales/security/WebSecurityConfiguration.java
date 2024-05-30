package araliya.pointOfSales.security;


import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import araliya.pointOfSales.repository.UserRepository;
import araliya.pointOfSales.security.jwt.AuthEntryPoint;
import araliya.pointOfSales.security.jwt.AuthTokenFilter;
import araliya.pointOfSales.service.UserService;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {
    @Autowired
    private AuthEntryPoint unauthorizedHandler;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public UserDetailsService userDetailsService(){
        return userDetailsService;        
    }

    @Bean
    public AuthTokenFilter authenticationJwAuthTokenFilter(){
        return new AuthTokenFilter();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)throws Exception{
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
       
       if(userRepository.count()==0){
        httpSecurity.csrf(csrf ->csrf.disable())
        .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedHandler))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth.requestMatchers("auth/login/**","/createUser","/isUserEmpty").permitAll()
        .anyRequest().authenticated());

        httpSecurity.authenticationProvider(authenticationProvider());

        httpSecurity.addFilterBefore(authenticationJwAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);
         return httpSecurity.build();

/*"/loadItems","/loadCategories","/updateItems","/loadSuppliersByItem",
        "loadSupplier_Item/{id}","/supplierDoesntProvideThisItem","/findSupplierByName/**","saveSupplier_Item/**","/addNewSupplier"
        ,"/saveItem","/saveCategory","/loadSuppliers","/loadStocks","/updateStock","/loadCustomers","/saveTransaction","/loadTransactions" */
    }else{  httpSecurity.csrf(csrf ->csrf.disable())
        .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedHandler))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth.requestMatchers("auth/login/**","/isUserEmpty").permitAll()
        .anyRequest().authenticated());

        httpSecurity.authenticationProvider(authenticationProvider());

        httpSecurity.addFilterBefore(authenticationJwAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);
         return httpSecurity.build();}}


}
