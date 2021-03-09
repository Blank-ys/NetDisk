package com.dbank.service;

import com.dbank.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 注册用户信息
     * @param user
     * @return
     */
    boolean registerUser(User user);

    /**
     * 删除用户信息
     * @param userUUID
     * @return
     */
    boolean deleteUserByUserUUID(String userUUID);

    /**
     * 查找所有用户信息
     * @return
     */
    List<User> getAllUsers();

    /**
     * 用户修改信息，服务器将修改后的用户对象返回
     * @param   preUser
     * @return
     */
    int updateUserByUserUUIDAndReturn(User preUser);

    /**
     * 用户 / 管理员 登录
     * @param   userName
     * @param   password
     * @return  User
     */
    User userLoginByUserNameAndPassword(String userName, String password,String identity);

    /**
     * 通过userUUID得到用户信息
     * @param   userUUID
     * @return  User
     */
    User getUserByUserUUID(String userUUID);

    /**
     * 通过userName得到用户信息
     * @param   userName
     * @return  User
     */
    User getUserByUserName(String userName);

    /**
     * 通过UserUUID得到userName
     * @param   userUUID
     * @return  userName
     */
    String getUserNameByUserUUID(String userUUID);

    /**
     * 通过userName得到userUUID
     * @param   userName
     * @return  userUUID
     */
    String getUserUUIDByUserName(String userName);

    /**
     * 通过条件查询用户信息(分页)
     * @param conditionMap
     * @return
     */
    Map<String, Object> getPageByCondition(Map<String, Object> conditionMap);
}
