package me.cmx.aicodegenerator.constant;

/**
 * 接口存常量 → 早期常见，但现在被认为是反模式。
 * 推荐 → final class + public static final，或根据业务用 enum / 配置文件。
 */
public interface UserConstant {

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "user_login";

    //  region 权限

    /**
     * 默认角色
     */
    String DEFAULT_ROLE = "user";

    /**
     * 管理员角色
     */
    String ADMIN_ROLE = "admin";
    
    // endregion
}
