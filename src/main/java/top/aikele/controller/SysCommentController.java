package top.aikele.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.aikele.entity.Result;
import top.aikele.entity.SysComment;
import top.aikele.entity.SysCommentVo;
import top.aikele.service.SysCommentService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kele
 * @since 2023-04-24
 */
@RestController
@RequestMapping("/sysComment")
@Api(tags = "评论Controller")
public class SysCommentController {
    @Autowired
    SysCommentService service;
    /*
    * 获取文章的所有评论*/
    @GetMapping("/{id}")
    @ApiOperation("获取文章的所有评论")
    @ApiImplicitParam(name = "id",value = "文章ID",paramType = "path",dataType = "Integer")
    public Result<List<SysCommentVo>> getComments(@NotNull @PathVariable Integer id){
        List<SysCommentVo> list =  service.getComments(id);
        return Result.success(list);
    }
    /**
     * 添加评论
    * */
    @PostMapping
    @ApiOperation("添加评论")
    public Result<Boolean> addComment(@NotNull @RequestBody SysComment comment){
      boolean ok =   service.addComment(comment);
      return ok==true?Result.success():Result.fail();
    }
    /**
     * 删除评论
     * */
    @DeleteMapping("/{id}")
    @ApiOperation("删除评论")
    public Result<Boolean> delComment(Integer id){
        boolean ok =   service.delComment(id);
        return ok==true?Result.success():Result.fail();
    }
}
