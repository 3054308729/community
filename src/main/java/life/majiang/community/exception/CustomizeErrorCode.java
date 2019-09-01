package life.majiang.community.exception;

/**
 * Created by hp on 2019/8/26 9:51
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND(2001,"你找的问题不存在!"),
    COMMENT_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复!"),
    NO_LOGIN(2003,"当前操作需要登录,请登录重试!"),
    SYS_ERROR(2004,"你还不能休息哟,系统又出BUG了哟!!!"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在!"),
    COMMENT_NOT_FOUNT(2006,"回复的评论不存在!"),
    COMMENT_IS_EMPTY(2007,"输入的内容不能为空!"),
    FILE_UPLOAD_FAIL(2010,"图片上传失败!");

    private String message;
    private Integer code;

    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    CustomizeErrorCode(Integer code,String message){
        this.code=code;
        this.message = message;
    }
}
