package top.aikele.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import top.aikele.config.security.JwtFilter;
import top.aikele.config.security.MyExceptionTranslationFilter;
import top.aikele.config.security.MyUserDetailService;
import top.aikele.config.security.MyUsernamePasswordAuthenticationFilter;
import top.aikele.entity.Result;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.config
 * @className: SercurityConifg
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/14 18:39
 * @version: 1.0
 */
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //使用session控制并发登录时需要
//    @Bean
//    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
//        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
//    }
    //跨域设置bean
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/webjars/**","/swagger-ui.html/**","/v2/**","/swagger-resources/**","/favicon.ico","/index/signUp").permitAll();
        http.authorizeRequests().antMatchers("/**").authenticated();
//        http.authorizeRequests().antMatchers("/**").permitAll();
        http.securityContext().disable();
        http.cors(Customizer.withDefaults());
        http.csrf().disable();
        http.formLogin().disable();
        http.logout().disable();
        http.sessionManagement().disable();
        http.requestCache().disable();
        http.exceptionHandling().disable();
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                        .maximumSessions(1)
//                                .maxSessionsPreventsLogin(true);
        http.addFilterBefore(new MyUsernamePasswordAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtFilter, MyUsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new MyExceptionTranslationFilter(), FilterSecurityInterceptor.class);
    }
//    @Autowired
//    SessionRegistry sessionRegistry;
//    @Autowired
//    RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy;
//    @Autowired
//    ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy;
//    @Autowired
//    CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy;
    @Autowired
    JwtFilter jwtFilter;
    @Bean
    public MyUserDetailService myUserDetailService(){
        return new MyUserDetailService();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy(){
//        return new RegisterSessionAuthenticationStrategy(sessionRegistry);
//    }
//    @Bean
//    public ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy(){
//        return new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
//    }
// 由于禁止了session 这里的类就用不到了 如果需要session进行并发控制的话就需要注册到MyUsernamePasswordAuthenticationFilter中去
//    @Bean
//    public CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy(){
//        List list = new ArrayList();
//        list.add(registerSessionAuthenticationStrategy);
//        list.add(concurrentSessionControlAuthenticationStrategy);
//        return new CompositeSessionAuthenticationStrategy(list);
//    }
//    @Bean
//    public SessionRegistryImpl sessionRegistryImpl(){
//        return new SessionRegistryImpl();
//    }
}
