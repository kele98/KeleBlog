package top.aikele.keleblog;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import top.aikele.controller.SysArticleController;
import top.aikele.entity.*;
import top.aikele.service.SysCommentService;
import top.aikele.service.SysUserService;

import java.sql.Types;
import java.util.*;

@SpringBootTest
class KeleBlogApplicationTests {
    @Autowired
    SysCommentService commentService;
    @Autowired
    SysUserService userService;
    @Autowired
    SysArticleController sysArticleController;
    @Test
    void contextLoads() {
        long timeMillis = System.currentTimeMillis();
        List<SysCommentVo> comments = commentService.getComments(18);
        System.out.println(comments);
        System.out.println(System.currentTimeMillis()-timeMillis);
    }

}
