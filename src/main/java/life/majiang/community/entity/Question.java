package life.majiang.community.entity;

import lombok.Data;

/**
 * Created by hp on 2019/8/19 18:33
 */
@Data
public class Question {
    private Integer id;
    private String title;//标题
    private String description;//内容
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;//发布人
    private int commentCount;//回复数
    private int viewCount;//浏览数
    private int likeCount;//喜欢数
    private String tag;//标签
    private String avatarUrl;//头像
}
