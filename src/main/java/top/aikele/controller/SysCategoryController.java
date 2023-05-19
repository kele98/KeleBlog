package top.aikele.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.aikele.entity.Result;
import top.aikele.entity.SysCategory;
import top.aikele.service.SysCategoryService;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kele
 * @since 2023-04-27
 */
@RestController
@RequestMapping("/sysCategory")
public class SysCategoryController {
    @Autowired
    SysCategoryService service;
    //新增分类
    @PostMapping
    public Result save(SysCategory category){
        boolean save = service.save(category);
        return save==true?Result.success("添加分类成功"):Result.fail("添加分类失败");
    }
    //删除分类
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        boolean save = service.removeById(id);
        return save==true?Result.success("删除分类成功"):Result.fail("删除分类失败");
    }
    //获取分类列表
    @GetMapping
    public Result getList(Integer index,Integer size){
        Page<SysCategory> page = service.page(new Page<SysCategory>(index, size));
        Map map = new HashMap();
        map.put("total",page.getTotal());
        map.put("list",page.getRecords());
        return Result.success(map);
    }
}
