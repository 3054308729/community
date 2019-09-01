package life.majiang.community.dto;

import lombok.Data;

/**
 * 文件传输层
 * Created by hp on 2019/9/1 12:06
 */
@Data
public class FileDTO {
    private int success;
    private String message;
    private String url;
}
