package life.majiang.community.entity;

import lombok.Data;

@Data
public class Comment {
    private int id;

    private Integer parentId;

    private Integer type;

    private Integer commentator;

    private Long gmtCreate;

    private Long gmtModified;

    private int likeCount;

    private String content;

    private int commentCount;
}