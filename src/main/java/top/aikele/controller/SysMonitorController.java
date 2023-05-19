package top.aikele.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.aikele.entity.Result;
import top.aikele.server.ServerOS;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.controller
 * @className: MonitorController
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/26 5:01
 * @version: 1.0
 */
@RestController
@RequestMapping("/monitor")
public class SysMonitorController {
    @GetMapping("/server/info")
    public Result info() throws Exception {
        return  Result.success(ServerOS.getInfo());
    }
}
