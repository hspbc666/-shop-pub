package cn.hsp.note.mapper;

import cn.hsp.note.bean.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
 * 编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
 */
@Repository
public interface NoteMapper {
    @Select(value = "select * from note where id = #{id}")
    Note query(@Param("id") long id);

    @Select(value = "select * from note where userId = #{userId} order by lastUpdateTime desc")
    List<Note> queryByUserId(@Param("userId") long userId);

    @Insert(value = "insert into note(userId,content) values (#{userId}, #{content})")
    void add(@Param("userId") long userId, @Param("content") String content);

    @Update(value = "update note set content=#{content},lastUpdateTime=now() where id = #{id}")
    void modify(@Param("id") long id, @Param("content") String content);

    @Delete("delete from note where id = #{id}")
    void del(@Param("id") long id);
}
