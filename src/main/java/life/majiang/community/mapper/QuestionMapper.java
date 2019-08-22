package life.majiang.community.mapper;

import life.majiang.community.entity.Question;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by hp on 2019/8/19 18:33
 */
public interface QuestionMapper {
    void create(Question question);
    List<Question> list(@RequestParam("offset") Integer offset,@RequestParam("size") Integer size);
    Integer count();
    List<Question> listByUserId(@RequestParam("userId") Integer userId,@RequestParam("offset") Integer offset,@RequestParam("size") Integer size);
    Integer countByUserId(Integer userId);
    Question getById(Integer id);
    void update(Question question);
}
