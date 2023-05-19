package top.aikele.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.aikele.entity.SysComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.aikele.entity.SysCommentVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kele
 * @since 2023-04-24
 */
@Mapper
public interface SysCommentMapper extends BaseMapper<SysComment> {
   List<SysCommentVo> getComments(Integer id);
}
