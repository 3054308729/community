package life.majiang.community.service;

import life.majiang.community.entity.User;

/**
 * Created by hp on 2019/8/19 11:30
 */
public interface UserService {

    User finByToken(String token);

    void createOrUpdate(User user);
}
