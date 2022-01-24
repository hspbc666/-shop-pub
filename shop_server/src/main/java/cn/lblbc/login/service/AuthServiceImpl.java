package cn.lblbc.login.service;

import cn.lblbc.login.bean.UserDetail;
import cn.lblbc.login.bean.response.LoginResp;
import cn.lblbc.login.bean.response.Resp;
import cn.lblbc.login.mapper.AuthMapper;
import cn.lblbc.login.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static cn.lblbc.login.bean.response.Resp.ERROR;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUtils jwtUtils;

    @Autowired
    private AuthMapper authMapper;

    @Override
    public Resp<String> register(UserDetail userDetail) {
        final String username = userDetail.getUsername();
        if (authMapper.findUserByName(username) != null) {
            return new Resp<>(ERROR, "用户已存在");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userDetail.getPassword();
        userDetail.setPassword(encoder.encode(rawPassword));
        authMapper.insert(userDetail);
        int roleId = userDetail.getRole().getId();
        authMapper.insertRole(userDetail.getId(), roleId);
        return new Resp<>();
    }

    @Override
    public Resp<LoginResp> login(String username, String password) {
        //用户验证
        final Authentication authentication = authenticate(username, password);

        Resp<LoginResp> resp;
        if (authentication != null) {
            //生成token
            final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
            final String token = jwtUtils.generateAccessToken(userDetail);
            resp = new Resp<>();
            resp.setData(new LoginResp(userDetail.getId(), token));
        } else {
            resp = new Resp<>(ERROR,"用户名或密码错误");
        }
        return resp;
    }

    private Authentication authenticate(String username, String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            return null;
        }
    }
}
