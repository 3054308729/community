package life.majiang.community.dto;

import life.majiang.community.entity.User;
import lombok.Data;

/**
 * 页面返回DTO
 * Created by hp on 2019/8/30 11:23
 */
@Data
public class CommentDTO {
    private int id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private int likeCount;
    private String content;
    private User user;
}
