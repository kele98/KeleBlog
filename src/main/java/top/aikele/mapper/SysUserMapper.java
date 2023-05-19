package top.aikele.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.aikele.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kele
 * @since 2023-04-14
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    @Override
    SysUser selectById(Serializable id);

}
