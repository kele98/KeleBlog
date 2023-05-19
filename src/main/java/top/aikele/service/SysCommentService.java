package top.aikele.service;

import top.aikele.entity.SysComment;
import com.baomidou.mybatisplus.extension.service.IService;
import top.aikele.entity.SysCommentVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kele
 * @since 2023-04-24
 */
public interface SysCommentService extends IService<SysComment> {

    List<SysCommentVo> getComments(Integer articleID);

    boolean addComment(SysComment comment);

    boolean delComment(Integer id);
}
