package life.majiang.community.service;

import life.majiang.community.dto.CommentDTO;
import life.majiang.community.entity.Comment;

import java.util.List;

public interface CommentService {
    int deleteByPrimaryKey(Integer id);

    void insert(Comment comment);

    int insertSelective(Comment comment);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment comment);

    int updateByPrimaryKey(Comment comment);

    List<CommentDTO> listByQuestionId(Integer id);
}