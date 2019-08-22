package life.majiang.community.dto;

import lombok.Data;

/**
 * Created by hp on 2019/8/16 11:23
 */
@Data
public class GithubUser {

    private String name;
    private Long id;
    private String bio;
    private String avatar_url;

}
