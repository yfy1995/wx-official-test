package xin.dayukeji.wxofficial.repository;

import xin.dayukeji.common.repository.BaseRepository;
import xin.dayukeji.wxofficial.entity.pojo.User;

/**
 * @Author: yfy
 * @Date: 2019-11-25 14:57
 * @Version 1.0
 * @description 描述
 */
public interface UserRepository extends BaseRepository<User, Long> {
    User findByOpenId(String openId);
}
