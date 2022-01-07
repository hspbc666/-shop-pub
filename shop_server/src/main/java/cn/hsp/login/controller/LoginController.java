package cn.hsp.login.controller;

import cn.hsp.login.bean.Role;
import cn.hsp.login.bean.UserDetail;
import cn.hsp.login.bean.response.LoginResp;
import cn.hsp.login.bean.response.Resp;
import cn.hsp.login.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    public Resp<LoginResp> login(@RequestBody UserDetail user) {
        return authService.login(user.getUsername(), user.getPassword());
    }

    @PostMapping(value = "/register")
    public Resp<String> register(@RequestBody UserDetail user) {
        int defaultRoleId = 1;
        UserDetail userDetail = new UserDetail(user.getUsername(), user.getPassword(), new Role(defaultRoleId));
        return authService.register(userDetail);
    }

}
