package sh.library.module.permissions;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.library.commom.response.RestResult;

/**
 * @author huangqc
 * @date 2022/9/21
 */
@Slf4j
@Api(tags = "认证授权接口")
@RestController
@RequestMapping("")
public class PermissionsController {
    @Autowired
    PermissionsService accountService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public RestResult<Boolean> login(String username, String password) {
        return RestResult.<Boolean>builder().ok(accountService.login(username, password));
    }

    @ApiOperation(value = "未授权")
    @GetMapping("/unauth")
    public String unauth() {
        return "未授权没有访问权限";
    }
}
