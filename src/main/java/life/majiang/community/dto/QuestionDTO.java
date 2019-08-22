package life.majiang.community.dto;

import life.majiang.community.entity.User;
import lombok.Data;

/**
 * Created by hp on 2019/8/20 16:37
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;//标题
    private String description;//内容
    private Long gmtCreate;
    private String gmtModified;
    private Integer creator;//发布人
    private Integer commentCount;//点赞数
    private Integer viewCount;//浏览数
    private Integer likeCount;//喜欢数
    private String tag;//标签
    private String avatarUrl;//头像
    private User user;
}
