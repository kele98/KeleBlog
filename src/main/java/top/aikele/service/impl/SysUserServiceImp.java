package top.aikele.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import top.aikele.entity.SysUser;
import top.aikele.mapper.SysUserMapper;
import top.aikele.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kele
 * @since 2023-04-14
 */
@Service
@Transactional
public class SysUserServiceImp extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    //用户注册
    public boolean singUp(SysUser sysUser) {
        SysUser test = baseMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, sysUser.getUsername()));
        if(test!=null)
            return false;
        String password = sysUser.getPassword();
        String encode = bCryptPasswordEncoder.encode(password);
        sysUser.setPassword(encode);
        int i = baseMapper.insert(sysUser);
        return i!=0?true:false;
    }

    @Override
    public Map<String,List<String>> getUserPerms(Integer id) {
        List<String> userPerms = baseMapper.selectById(id).getPerms();
        Map map = new HashMap();
        map.put("perms",userPerms);
        return map;
    }

    @Override
    public boolean updateById(SysUser sysUser) {
        //1获取当前登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser loginUser = (SysUser) authentication.getDetails();
//        loginUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
        loginUser.setIcon(sysUser.getIcon());
        int i = baseMapper.updateById(loginUser);
        return i!=0?true:false;
    }
}
