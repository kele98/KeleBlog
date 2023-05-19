package top.aikele.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import top.aikele.entity.Result;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.config.security
 * @className: MyExceptionTranslationFilter
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/19 2:22
 * @version: 1.0
 */
public class MyExceptionTranslationFilter extends ExceptionTranslationFilter {
    public MyExceptionTranslationFilter() {
        super(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                PrintWriter writer = null;
                try {
                    writer  = response.getWriter();
                    ObjectMapper mapper = new ObjectMapper();
                    String s = mapper.writeValueAsString(Result.fail("未登录"));
                    writer.println(s);
                    writer.flush();
//                    response.sendRedirect("/login");
                }finally {
                    writer.close();
                }
            }
        });
    }

    @Override
    protected void sendStartAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, AuthenticationException reason) throws ServletException, IOException {
        // SEC-112: Clear the SecurityContextHolder's Authentication, as the
        // existing Authentication is no longer considered valid
        SecurityContextHolder.getContext().setAuthentication(null);
        //需要保存到session的操作
        //this.requestCache.saveRequest(request, response);
        AuthenticationEntryPoint authenticationEntryPoint = getAuthenticationEntryPoint();
        authenticationEntryPoint.commence(request, response, reason);
    }
}
