package top.aikele.config.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.aikele.entity.SysUser;
import top.aikele.service.SysUserService;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.config.security
 * @className: MyUserDetailService
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/14 19:36
 * @version: 1.0
 */
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    SysUserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        if(user!=null){
            return new MyUserDetail(user);
        }
        return null;
    }
}
