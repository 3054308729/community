package life.majiang.community.exception;

/**
 * Created by hp on 2019/8/26 9:51
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("数据库中没有该条数据,重新查一下吧!");



    private String message;

    @Override
    public String getMessage(){
        return message;
    }

    CustomizeErrorCode(String message){
        this.message = message;
    }
}
