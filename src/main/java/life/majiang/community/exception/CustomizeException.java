package life.majiang.community.exception;

/**
 * Created by hp on 2019/8/26 9:30
 */
public class CustomizeException  extends RuntimeException{
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
