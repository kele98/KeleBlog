package top.aikele.config.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import top.aikele.entity.Result;
import top.aikele.utils.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.config.security
 * @className: MyUsernamePasswordAuthenticationFilter
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/14 19:19
 * @version: 1.0
 */
@Slf4j
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //修改默认的认证策略
    public MyUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy) {
        super(authenticationManager);
        super.setSessionAuthenticationStrategy(compositeSessionAuthenticationStrategy);
    }

    public MyUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/index/login",
                "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            String username = request.getParameter("username");
            username = (username != null) ? username : "";
            username = username.trim();
            String password = request.getParameter("password");
            password = (password != null) ? password : "";
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }catch (Exception e){
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = null;
            try {
                writer  = response.getWriter();
                ObjectMapper mapper = new ObjectMapper();
                String s = mapper.writeValueAsString(Result.fail("登录发生错误"));
                writer.println(s);
                writer.flush();
//                    response.sendRedirect("/login");
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } finally {
                writer.close();
            }
            return null;
        }

    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        MyUserDetail UserDetail = (MyUserDetail) authResult.getPrincipal();
        String token = JwtUtils.generateToken(UserDetail.getUser().getId(), UserDetail.getUser().getUsername());
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = null;
        try {
            writer  = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            Map map = new HashMap();
            map.put("token",token);
            String s = mapper.writeValueAsString(Result.success("登录成功",map));
            writer.println(s);
            writer.flush();
//                            response.sendRedirect("/login");
        }finally {
            writer.close();
        }
        log.info("用户："+UserDetail.getUser().getUsername()+"登录，Token为："+token);
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("登录验证成功");
        super.unsuccessfulAuthentication(request, response, failed);
    }

    @Override
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
        System.out.println("登录验证失败");
        super.setAuthenticationSuccessHandler(successHandler);
    }
}
