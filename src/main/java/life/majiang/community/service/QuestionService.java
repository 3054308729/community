package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.entity.Question;


/**
 * Created by hp on 2019/8/19 18:33
 */
public interface QuestionService {
    int insert(Question record);
    PaginationDTO<QuestionDTO> list(Integer pn, Integer size);
    PaginationDTO<QuestionDTO> list(Integer userId, Integer pn, Integer size);
    QuestionDTO getById(Integer id);

    void createOrUpdate(Question question);
}
