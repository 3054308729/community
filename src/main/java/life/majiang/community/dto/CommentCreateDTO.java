package life.majiang.community.dto;

import lombok.Data;

/**
 * 页面传输DTO
 * Created by hp on 2019/8/29 9:32
 */
@Data
public class CommentCreateDTO {
    private int parentId;
    private int type;
    private String content;

}
