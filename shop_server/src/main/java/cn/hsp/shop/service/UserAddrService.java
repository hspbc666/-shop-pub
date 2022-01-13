package cn.hsp.shop.service;

import cn.hsp.shop.bean.CartItem;
import cn.hsp.shop.bean.CartSimpleItem;
import cn.hsp.shop.bean.UserAddr;
import cn.hsp.shop.mapper.CartMapper;
import cn.hsp.shop.mapper.UserAddrMapper;
import cn.hsp.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
@Service
public class UserAddrService {
    @Autowired
    private UserAddrMapper userAddrMapper;

    public List<UserAddr> query(int userId) {
        return userAddrMapper.query(userId);
    }

    public void add(UserAddr userAddr) {
        userAddrMapper.add(userAddr.getId(), userAddr.getName(), userAddr.getPhone(), userAddr.getAddress(), userAddr.getIndex());
    }

    public void update(UserAddr userAddr) {
        userAddrMapper.update(userAddr.getId(), userAddr.getName(), userAddr.getPhone(), userAddr.getAddress(), userAddr.getIndex());
    }
}
