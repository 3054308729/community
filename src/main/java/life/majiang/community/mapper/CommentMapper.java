package life.majiang.community.mapper;

import life.majiang.community.dto.CommentDTO;
import life.majiang.community.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment comment);

    int insertSelective(Comment comment);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment comment);

    int updateByPrimaryKey(Comment comment);

    List<Comment> selectAllById(@Param("id") Integer id, @Param("type")Integer type);
}