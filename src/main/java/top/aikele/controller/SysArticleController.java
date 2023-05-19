package top.aikele.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import top.aikele.entity.Result;
import top.aikele.entity.SysArticle;
import top.aikele.entity.SysUser;
import top.aikele.service.SysArticleService;
import top.aikele.utils.OssUtils;
import top.aikele.utils.RedisUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kele
 * @since 2023-04-21
 */
@RestController
@RequestMapping("/sysArticle")
@Api(tags = "文章操作")
public class SysArticleController {
        @Autowired
        SysArticleService articleService;
        //上传文章
        @PostMapping
        @ApiOperation("上传文章")
        public Result updateArticle(@RequestBody SysArticle sysArticle){
            SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
            sysArticle.setUserId(user.getId());
            boolean save = articleService.save(sysArticle);
            return save==true?Result.success():Result.fail();
        }
        //修改文章
        @PutMapping
        public Result modifyArticle(@RequestBody SysArticle sysArticle){
            boolean save =  articleService.updateById(sysArticle);
            return save==true?Result.success("修改文章成功"):Result.fail("修改文章成功");
        }

        //删除文章
        @DeleteMapping("/{id}")
        public Result deleteArticle(@PathVariable Integer id){
            boolean save =  articleService.removeById(id);
            return save==true?Result.success("删除文章成功"):Result.fail("删除文章成功");
        }
        //上传文章图片
        @PostMapping("/uploadImage")
        public Result uploadImage(HttpServletRequest request){
            MultipartHttpServletRequest fileRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = fileRequest.getFileMap();
          List urls = new ArrayList();
            for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                System.out.println("文件名:"+entry.getKey());
                MultipartFile file = entry.getValue();
                System.out.println("原始文件名:"+file.getOriginalFilename());
                try {
                    String originalFilename = file.getOriginalFilename();
                    originalFilename = originalFilename.substring(originalFilename.indexOf('.'));
                    String imgs = OssUtils.upload(UUID.randomUUID().toString() + originalFilename, "imgs", file.getBytes());
//                    map.put(entry.getKey(),imgs);
                    urls.add(imgs);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return Result.success(urls);
        }
        //获取所有文章
        @GetMapping
        @ApiImplicitParams({
                @ApiImplicitParam(name = "index",value = "页号"),
                @ApiImplicitParam(name = "size",value = "页大小")
        })
        @Secured("ROLE_admin")
        public Result<Map<String,Objects>> getArticle(@NotNull Integer index,@NotNull Integer size){
            Page<SysArticle> page = articleService.page(new Page<SysArticle>(index, size),new LambdaQueryWrapper<SysArticle>().orderByDesc(SysArticle::getCreateTime));
            Map map = new HashMap();
            map.put("total",page.getTotal());
            map.put("list",page.getRecords());
            return Result.success(map);
        }
        //获取单个文章
        @GetMapping("/{id}")
        public Result<SysArticle> getArticle(@PathVariable @NotNull Integer id){
            SysArticle article = articleService.getById(id);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            SysUser user = (SysUser) authentication.getDetails();
            //设置点击量
            if(article!=null){
                //先判断一个key存在不存在 不存在就新建并设置过期时间
                if(RedisUtils.sGet(article.getId()+"").size()==0){
                    RedisUtils.sSet(article.getId()+"",".");//这里只是创建key value无意义
                    RedisUtils.expire(article.getId()+"",RedisUtils.getRemainSecondsOneDay(new Date()));
                }
                //判断当前用户在不在这个set里
                if (RedisUtils.sSet(article.getId()+"",user.getId())!=0){
                    //用户今天第一次访问 增加views
                    article.setViews(article.getViews()+1);
                    articleService.updateById(article);
                }
                return Result.success(article);
            }
            return Result.fail("获取失败");

        }

}
