package cn.lblbc.shop.service;

import cn.lblbc.shop.bean.UserAddr;
import cn.lblbc.shop.mapper.UserAddrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
@Service
public class UserAddrService {
    @Autowired
    private UserAddrMapper userAddrMapper;

    public List<UserAddr> query(int userId) {
        return userAddrMapper.query(userId);
    }

    public UserAddr queryById(String userAddrId) {
        return userAddrMapper.queryById(userAddrId);
    }

    public UserAddr queryDefaultAddress(int userId) {
        List<UserAddr> addrList = query(userId);
        UserAddr result = null;
        for (UserAddr userAddr : addrList) {
            if (userAddr.isDefaultAddress()) {
                result = userAddr;
                break;
            }
        }
        if (!addrList.isEmpty()) {
            result = addrList.get(0);
        }
        return result;
    }

    public void add(int userId, UserAddr userAddr) {
        if (userAddr.isDefaultAddress()) {
            userAddrMapper.modifyAsUnDefault(userId);
        }
        userAddrMapper.add(userAddr.getId(), userId, userAddr.getName(), userAddr.getPhone(),userAddr.getRegion(), userAddr.getAddress(), userAddr.isDefaultAddress(),userAddr.getAddrType());
    }

    public void modify(int userId, UserAddr userAddr) {
        if (userAddr.isDefaultAddress()) {
            userAddrMapper.modifyAsUnDefault(userId);
        }
        userAddrMapper.modify(userAddr.getId(), userAddr.getName(), userAddr.getPhone(),userAddr.getRegion(), userAddr.getAddress(), userAddr.isDefaultAddress(),userAddr.getAddrType());
    }

    public void delete(String id) {
        userAddrMapper.delete(id);
    }
}
