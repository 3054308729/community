package life.majiang.community.enums;

/**
 * 父类回复标题
 * Created by hp on 2019/8/29 11:41
 */
public enum CommentTypeEnum {
    QUESTION(1),//问题问题
    COMMENT(2);//回复评论

    private Integer type;

    public static boolean isExist(Integer type) {
        for ( CommentTypeEnum commentTypeEnum: CommentTypeEnum.values()) {
            if (commentTypeEnum.getType() == type){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
