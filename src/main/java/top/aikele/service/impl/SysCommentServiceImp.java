package top.aikele.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import top.aikele.entity.SysComment;
import top.aikele.entity.SysCommentVo;
import top.aikele.entity.SysUser;
import top.aikele.mapper.SysCommentMapper;
import top.aikele.service.SysCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kele
 * @since 2023-04-24
 */
@Service
public class SysCommentServiceImp extends ServiceImpl<SysCommentMapper, SysComment> implements SysCommentService {

    @Override
    public List<SysCommentVo> getComments(Integer articleID) {
        List<SysCommentVo> sysComments = baseMapper.getComments(articleID);
        List<SysCommentVo> commentTree = buildTree(sysComments, 0,false);
        return commentTree;
    }
    /*添加评论*/
    @Override
    public boolean addComment(SysComment comment) {
        //是root评论
        if(comment.getPId().equals(0)&&comment.getTopId().equals(0)){
            boolean save = save(comment);
            return save;
        }
        //检测评论的root对象是否存在
        SysComment root = getById(comment.getTopId());
        if(root==null){
            return false;
        }
        //检测评论的p对象是否存在
        SysComment p = getById(comment.getPId());
        if(p==null){
            return false;
        }
        //添加评论
        boolean save = save(comment);
        return save;

    }
    //删除评论
    @Override
    public boolean delComment(Integer id) {
        //获取用户对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser user = (SysUser) authentication.getDetails();
        //只能删除自己的评论
        boolean remove = remove(new LambdaQueryWrapper<SysComment>().eq(SysComment::getId, id).eq(SysComment::getUserId, user.getId()));
        return remove;
    }

    /**
     * 传入当前文章的所有评论 返回多个由root评论组成的树
     * @param Pid 顶层id
     * @param deepTree 是否生成递归树 false生成二级树
    * */
    public List<SysCommentVo> buildTree(List<SysCommentVo> rawComment,Integer Pid,boolean deepTree){
        //生成递归树
        ArrayList<SysCommentVo> list = new ArrayList<>();
        Map<Integer, List<SysCommentVo>> map;
        if(deepTree!=false){
            map = rawComment.stream().collect(Collectors.groupingBy(SysCommentVo::getPId));
        }else {
            map = rawComment.stream().collect(Collectors.groupingBy(SysCommentVo::getTopId));
        }
        for (SysCommentVo sysComment : rawComment) {
            if(sysComment.getPId()==Pid){
                list.add(sysComment);
                sysComment.setChildren(map.get(sysComment.getId()));
            }
            sysComment.setChildren(map.get(sysComment.getId()));
        }
        return list;

    }

}
