package me.cmx.aicodegenerator.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import me.cmx.aicodegenerator.model.dto.user.UserQueryRequest;
import me.cmx.aicodegenerator.model.entity.User;
import me.cmx.aicodegenerator.model.vo.LoginUserVO;
import me.cmx.aicodegenerator.model.vo.UserVO;

import java.util.List;

/**
 * 用户 服务层。
 *
 * @author smuca
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);


    /**
     *  对用户密码进行加密
     *
     * @param userPassword 用户传入的密码
     * @return 加密后的密码
     */
    String getEncryptPassword(String userPassword);

    /**
     * 获取当前登录用户脱敏后的信息
     * @param user 数据库返回的用户信息
     * @return 用户脱敏后的信息
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 用户登录
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param request 请求对象
     * @return 用户脱敏后的信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     * @param request
     * @return 用户信息
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 用户登出
     * @param request 请求对象
     * @return 用户是否登出 true || false
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取用户脱敏后的信息
     * @param user 查询的用户对象
     * @return 用户对象脱敏后的信息
     */
    UserVO getUserVO(User user);

    /**
     * 获取用户脱敏后的信息列表
     * @param userList 查询的用户列表
     * @return 用户列表脱敏后的信息
     */
    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 将用户查询请求转换为 QueryWrapper
     * @param userQueryRequest 用户查询请求
     * @return
     */
    QueryWrapper getQueryWrapper(UserQueryRequest userQueryRequest);

}
