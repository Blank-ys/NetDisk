package com.dbank.dao;

import com.dbank.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {

    /**
     * 添加用户信息到数据库
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 用户/管理员 登录
     * @param userName
     * @param password
     * @return
     */
    User login(String userName, String password,String identity);

    /**
     * 更新用户信息
     * @param preUser
     * @return
     */
    int updateUser(User preUser);

    /**
     * 查找用户信息
     * @return
     */
    User selectOneByUserUUID(String userUUID);

    /**
     *
     * @param UserName
     * @return
     */
    User selectOneByUserName(String UserName);

    /**
     * 查找所有用户信息
     * @return
     */
    List<User> selectAll();

    /**
     * 删除数据库中的用户信息
     * @param userUUID
     * @return
     */
    int deleteOne(String userUUID);

    /**
     *
     * @param   userUUID
     * @return  userName
     */
    String getUserName(String userUUID);

    /**
     *
     * @param   userName
     * @return  UserUUID
     */
    String getUserUUID(String userName);

    /**
     * 得到符合查询条件的总页数
     * @param conditionMap
     * @return
     */
    Long getTotalByCondition(Map<String, Object> conditionMap);

    /**
     * 得到符合查询条件的用户信息列表
     * @param conditionMap
     * @return
     */
    List<User> getDataListByCondition(Map<String, Object> conditionMap);
}
