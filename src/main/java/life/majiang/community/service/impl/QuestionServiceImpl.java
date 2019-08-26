package life.majiang.community.service.impl;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.entity.Question;
import life.majiang.community.entity.User;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2019/8/19 18:37
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(Question question) {
        return questionMapper.insert(question);
    }

    //显示首页分页
    @Override
    public PaginationDTO<QuestionDTO> list(Integer pn, Integer size) {
        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<>();
        Integer totalPage;
        Integer totalCount = questionMapper.count();//总数
        if(totalCount % size == 0){
            totalPage = totalCount /size;
        }else {
            totalPage = totalCount / size + 1;
        }

        if(pn < 1){
            pn = 1;
        }
//        if(pn > totalPage){
//            pn = totalPage;
//        }

        paginationDTO.setPagination(totalPage,pn);

        Integer offset = size * (pn - 1);
        List<Question> questions = questionMapper.selectAll(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        for ( Question question : questions ){
            User user =  userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();//复制对象
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    //显示我的问题
    @Override
    public PaginationDTO list(Integer userId, Integer pn, Integer size) {
        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<>();

        Integer totalPage;

        Integer totalCount = questionMapper.countByUserId(userId);//总数
        if(totalCount % size == 0){
            totalPage = totalCount /size;
        }else {
            totalPage = totalCount / size + 1;
        }

        if(pn < 1){
            pn = 1;
        }
//        if(pn > totalPage){
//            pn = totalPage;
//        }

        paginationDTO.setPagination(totalPage,pn);

        Integer offset = size * (pn - 1);
        List<Question> questions = questionMapper.selectAllById(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        for ( Question question : questions ){
            User user =  userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();//复制对象
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    //显示问题详情
    @Override
    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null){
            throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user =  userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    @Override
    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else {
            question.setGmtModified(question.getGmtCreate());
            int updated = questionMapper.updateByPrimaryKey(question);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
