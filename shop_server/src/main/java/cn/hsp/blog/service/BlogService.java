package cn.hsp.blog.service;

import cn.hsp.blog.bean.Blog;
import cn.hsp.blog.mapper.BlogMapper;
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
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;

    public Blog query(long blogId) {
        return blogMapper.query(blogId);
    }

    public void add(long userId,String title, String content) {
        blogMapper.add(userId, title, content);
    }

    public void modify(long id, String title, String content) {
        blogMapper.modify(id, title, content);
    }

    public void del(long id) {
        blogMapper.del(id);
    }

    public List<Blog> queryByUserId(long userId) {
        return blogMapper.queryByUserId(userId);
    }
}
