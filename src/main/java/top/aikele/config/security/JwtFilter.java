package top.aikele.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.aikele.entity.SysUser;
import top.aikele.service.SysUserService;
import top.aikele.utils.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.config.security
 * @className: JwtFilter
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/16 2:07
 * @version: 1.0
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    SysUserService sysUserService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //请求头中获取jwt信息
        String token = request.getHeader("token");
        if(token!=null){
            Integer userId = JwtUtils.getUserId(token);
            String username = JwtUtils.getUsername(token);
            SysUser user = sysUserService.getById(userId);
            List<String> perms = user.getPerms();
            //封装信息
            List<SimpleGrantedAuthority> list = new ArrayList<>();
            for (String s : perms) {
                list.add(new SimpleGrantedAuthority(s));
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null,list);
            authenticationToken.setDetails(user);
            //放入上下文
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            try {
                filterChain.doFilter(request,response);
            }finally {
                //清除信息
                SecurityContextHolder.clearContext();
            }
        }
        else {
            filterChain.doFilter(request,response);
        }

    }
}
