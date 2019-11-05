package com.intecsec.springboot.service;

import com.intecsec.springboot.entity.User;
import com.intecsec.springboot.entity.UserLog;
import com.intecsec.springboot.mapper.UserLogMapper;
import com.intecsec.springboot.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-10-28 18:46
 **/
@Service
@Configuration
@Slf4j
public class UserService {
    @Value("${server.port}")
    private int port;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserLogMapper userLogMapper;

    public User getUser(int id) {
        log.info("run on port:{}, profile:{}", port, profile);
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);

        UserLog userLog = new UserLog();
        userLog.setUserId(1);
        userLog.setDescription("age:" + user.getAge());
        userLogMapper.insert(userLog);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void batchInsertLog() {

        for (int i = 0; i < 10; i++) {
            if(i > 9) {
                throw new RuntimeException();
            }

            UserLog userLog = new UserLog();
            userLog.setUserId(1);
            userLog.setDescription("i:" + i);
            userLogMapper.insert(userLog);
        }
    }
}
