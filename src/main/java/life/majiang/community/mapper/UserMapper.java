package life.majiang.community.mapper;

import life.majiang.community.entity.User;

/**
 * Created by hp on 2019/8/19 9:59
 */
public interface UserMapper {
    void insert(User user);
    User finByToken(String token);
    User findById(Integer id);
    User findByAccountId(String accountId);
    void update(User dbUser);
}
