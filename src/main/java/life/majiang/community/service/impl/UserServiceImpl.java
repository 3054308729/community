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

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    //校验用户是否存在
    @Override
    public void createOrUpdate(User user) {
        System.out.println(user);
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        System.out.println(dbUser);
        if (dbUser == null) {
            //新增
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            dbUser.setGmtModified(System.currentTimeMillis());//当前时间戳
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
        }
    }
}
