//package mk.ukim.finki.booksreviews.config;
//
//import lombok.AllArgsConstructor;
//import mk.ukim.finki.booksreviews.config.filters.AuthenticationFilter;
//import mk.ukim.finki.booksreviews.config.filters.AuthorizationFilter;
//import mk.ukim.finki.booksreviews.service.AuthorService;
//import mk.ukim.finki.booksreviews.service.ReviewerService;
//
//import mk.ukim.finki.booksreviews.service.UserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
//@AllArgsConstructor
//@Order(200)
//@Profile("jwt")
//public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final PasswordEncoder passwordEncoder;
//
//
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                .authorizeRequests()
//                .antMatchers( "/", "/home", "/assets/**", "/register", "/products", "/api/**").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .addFilter(new AuthenticationFilter(authenticationManager(),userDetailsService,passwordEncoder))
//                .addFilter(new AuthorizationFilter(authenticationManager(),userDetailsService))
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////
////        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
////    }
////
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
//}
