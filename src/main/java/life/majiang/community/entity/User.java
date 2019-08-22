package life.majiang.community.entity;

import lombok.Data;

/**
 * Created by hp on 2019/8/19 10:02
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;
}
