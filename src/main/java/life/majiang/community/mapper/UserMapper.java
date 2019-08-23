package life.majiang.community.mapper;

import java.util.List;
import life.majiang.community.entity.User;

public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User user);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User finByToken(String token);

    User findByAccountId(String accountId);
}