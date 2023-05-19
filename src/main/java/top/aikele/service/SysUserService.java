package top.aikele.service;

import top.aikele.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kele
 * @since 2023-04-14
 */
public interface SysUserService extends IService<SysUser> {
    boolean singUp(SysUser sysUser);
    //根据用户id获取用户权限
    Map<String,List<String>> getUserPerms(Integer id);

}
