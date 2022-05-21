//package mk.ukim.finki.booksreviews.config.filters;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import mk.ukim.finki.booksreviews.config.JwtAuthConstants;
//import mk.ukim.finki.booksreviews.model.dto.UserDto;
//import mk.ukim.finki.booksreviews.model.entity.User;
//import mk.ukim.finki.booksreviews.model.exceptions.PasswordsDoNotMatch;
//import org.hibernate.annotations.Filter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.json.JsonParseException;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.Date;
//
//public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//    private final UserDetailsService userDetailsService;
//    private final PasswordEncoder passwordEncoder;
//
//
//    public AuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
//        this.setAuthenticationManager(authenticationManager);
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        User creds =null;
//        try {
//            String text = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
//            creds= new ObjectMapper().readValue(text, User.class);
//        }
//        catch (JsonParseException e){
//            e.printStackTrace();
//        }
//        catch (JsonMappingException e){
//            e.printStackTrace();
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//        if (creds == null){
//            throw new UsernameNotFoundException("Invalid Credentials");
//        }
//
//        UserDetails userDetails= userDetailsService.loadUserByUsername(creds.getEmail());
//
//        if(!passwordEncoder.matches(creds.getPassword(),userDetails.getPassword())){
//            throw new PasswordsDoNotMatch();
//        }
//        return authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(userDetails.getUsername(),creds.getPassword(),userDetails.getAuthorities())
//        );
//
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//
//        this.generateJwt(response,authResult);
//        super.successfulAuthentication(request, response, chain, authResult);
//    }
//
//    public String generateJwt(HttpServletResponse response, Authentication authResult) throws JsonProcessingException {
//        User userDetails = (User) authResult.getPrincipal();
//        String token = JWT.create()
//                .withSubject(new ObjectMapper().writeValueAsString(UserDto.of(userDetails)))
//                .withExpiresAt(new Date(System.currentTimeMillis() + JwtAuthConstants.EXPIRATION_TIME))
//                .sign(Algorithm.HMAC256(JwtAuthConstants.SECRET.getBytes()));
//        response.addHeader(JwtAuthConstants.HEADER_STRING, JwtAuthConstants.TOKEN_PREFIX + token);
//        return JwtAuthConstants.TOKEN_PREFIX + token;
//    }
//
//    @Bean
//    AuthenticationFilter authenticationFilter() {
//        return new AuthenticationFilter(getAuthenticationManager(),userDetailsService,passwordEncoder);
//    }
//
//}
