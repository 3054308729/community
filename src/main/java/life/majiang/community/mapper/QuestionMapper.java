package life.majiang.community.mapper;

import java.util.List;
import life.majiang.community.entity.Question;
import org.apache.ibatis.annotations.Param;

public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    Question selectByPrimaryKey(Integer id);

    List<Question> selectAll(@Param("offset") Integer pn, @Param("size") Integer size);

    List<Question> selectAllById(@Param("userId") Integer userId,@Param("offset") Integer pn, @Param("size") Integer size);

    Integer count();

    Integer countByUserId(Integer userId);

    int updateByPrimaryKey(Question record);
}