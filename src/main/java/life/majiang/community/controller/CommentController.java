package life.majiang.community.controller;

import life.majiang.community.dto.CommentCreateDTO;
import life.majiang.community.dto.CommentDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.entity.Comment;
import life.majiang.community.entity.User;
import life.majiang.community.enums.CommentTypeEnum;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by hp on 2019/8/29 9:23
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 获取评论列表
     *
     * @param commentCreateDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody(required = false) CommentCreateDTO commentCreateDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if (commentCreateDTO == null || StringUtils.isAllBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);//输入内容不能为空
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

    /**
     * 获取二级评论
     *
     * @return
     */
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Integer id) {
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
