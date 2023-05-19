package top.aikele.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.aikele.entity.Result;
import top.aikele.entity.SysUser;
import top.aikele.service.SysUserService;

import javax.validation.Valid;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.controller
 * @className: SysIndex
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/18 23:55
 * @version: 1.0
 */
@RestController
@RequestMapping("/index")
@CrossOrigin
public class SysIndexController {
    @Autowired
    SysUserService userService;
    @PostMapping("/signUp")
    //注册
    public Result signUp(@Valid SysUser sysUser){
        boolean save = userService.singUp(sysUser);
        if(save)
        return Result.success("注册成功");
        return Result.fail("注册失败");
    }
    @GetMapping("/info")
    public Result info(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = (SysUser) authentication.getDetails();
        sysUser.setPassword(null);
        return Result.success(sysUser);
    }
}
