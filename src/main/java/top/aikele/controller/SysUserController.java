package top.aikele.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.aikele.entity.Result;
import top.aikele.entity.SysUser;
import top.aikele.service.SysUserService;
import top.aikele.springAopTest.MyAop;
import top.aikele.utils.OssUtils;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kele
 * @since 2023-04-14
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    SysUserService userService;
    //获取用户权限列表
    @GetMapping("/getUserPerms/{id}")
    public Result getUserPerms(@PathVariable Integer id){
        Map<String, List<String>> userPerms = userService.getUserPerms(id);
        return userPerms!=null?Result.success(userPerms):Result.fail();
    }
    //删除用户
    @DeleteMapping("{id}")
    public Result delUser(@PathVariable Integer id){
        boolean remove = userService.removeById(id);
        return remove==true?Result.success("删除成功"):Result.fail("删除失败");
    }
    @PutMapping()
    public Result updateUser(@RequestBody SysUser user){
        boolean b = userService.updateById(user);
        return b==true?Result.success("用户信息更新成功"):Result.fail("用户信息更新失败");
    }
    //上传头像
    @PostMapping("/uploadImage")
    public Result upload(MultipartFile file){
        String imgs;
        try {
            String originalFilename = file.getOriginalFilename();
            originalFilename = originalFilename.substring(originalFilename.indexOf('.'));
            imgs= OssUtils.upload(UUID.randomUUID()+ originalFilename, "imgs", file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Result.success((Object) imgs);
    }

}
