package life.majiang.community.dto;

import lombok.Data;

/**
 * Created by hp on 2019/8/16 10:53
 */
@Data
public class AccessTokenDTO {
    private String  client_id;  //GitHub收到的GitHub应用程序的客户端ID
    private String  client_secret;  //GitHub收到的GitHub应用程序的客户机密
    private String  code;   //收到的代码作为对第1步的回复
    private String  redirect_uri;   //应用程序中的URL，用于在授权后发送用户
    private String  state;  //您在步骤1中提供的不可思议的随机字符串。
}
