package life.majiang.community.service;

import life.majiang.community.entity.Comment;

public interface CommentService {
    int deleteByPrimaryKey(Integer id);

    void insert(Comment comment);

    int insertSelective(Comment comment);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment comment);

    int updateByPrimaryKey(Comment comment);
}