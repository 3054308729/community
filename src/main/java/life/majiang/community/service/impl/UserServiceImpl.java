package life.majiang.community.service.impl;

import life.majiang.community.entity.User;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hp on 2019/8/19 11:31
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public User finByToken(String token) {
       return userMapper.finByToken(token);
    }
}
