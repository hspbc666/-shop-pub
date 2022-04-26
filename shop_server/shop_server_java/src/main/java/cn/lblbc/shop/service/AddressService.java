package cn.lblbc.shop.service;

import cn.lblbc.shop.bean.Address;
import cn.lblbc.shop.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
@Service
public class AddressService {
    @Autowired
    private AddressMapper addressMapper;

    public List<Address> query(int userId) {
        return addressMapper.query(userId);
    }

    public Address queryById(String addressId) {
        return addressMapper.queryById(addressId);
    }

    public Address queryDefaultAddress(int userId) {
        List<Address> addressList = query(userId);
        Address result = null;
        for (Address address : addressList) {
            if (address.isDefaultAddress()) {
                result = address;
                break;
            }
        }
        if (!addressList.isEmpty()) {
            result = addressList.get(0);
        }
        return result;
    }

    public void add(int userId, Address address) {
        if (address.isDefaultAddress()) {
            addressMapper.modifyAsUnDefault(userId);
        }
        addressMapper.add(address.getId(), userId, address.getName(), address.getPhone(), address.getAddress(), address.isDefaultAddress());
    }

    public void modify(int userId, Address address) {
        if (address.isDefaultAddress()) {
            addressMapper.modifyAsUnDefault(userId);
        }
        addressMapper.modify(address.getId(), address.getName(), address.getPhone(), address.getAddress(), address.isDefaultAddress());
    }

    public void delete(String id) {
        addressMapper.delete(id);
    }
}
