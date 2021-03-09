package com.dbank.service.impl;

import com.dbank.dao.UserDao;
import com.dbank.domain.User;
import com.dbank.service.UserService;
import com.dbank.util.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDao userDao = SqlSessionUtil.getCurrentSqlSession().getMapper(UserDao.class);

    @Override
    public boolean registerUser(User user) {
        return userDao.addUser(user) == 1;
    }

    @Override
    public boolean deleteUserByUserUUID(String userUUID) {
        return userDao.deleteOne(userUUID) == 1;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.selectAll();
    }

    @Override
    public int updateUserByUserUUIDAndReturn(User preUser) {
        return userDao.updateUser(preUser);
    }

    @Override
    public User userLoginByUserNameAndPassword(String userName, String password, String identity) {
        return userDao.login(userName,password,identity);
    }

    @Override
    public User getUserByUserUUID(String userUUID) {
        return userDao.selectOneByUserUUID(userUUID);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.selectOneByUserName(userName);
    }

    @Override
    public String getUserNameByUserUUID(String userUUID) {
        return userDao.getUserName(userUUID);
    }

    @Override
    public String getUserUUIDByUserName(String userName) {
        return userDao.getUserUUID(userName);
    }

    public Map<String, Object> getPageByCondition(Map<String, Object> conditionMap) {
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("total", userDao.getTotalByCondition(conditionMap));
        pageMap.put("dataList", userDao.getDataListByCondition(conditionMap));
        return pageMap;
    }
}
