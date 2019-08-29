package life.majiang.community.dto;

import lombok.Data;

/**
 * Created by hp on 2019/8/29 9:32
 */
@Data
public class CommentDTO {
    private int parentId;
    private int type;
    private String content;

}
