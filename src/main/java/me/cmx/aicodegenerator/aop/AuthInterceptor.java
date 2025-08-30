package me.cmx.aicodegenerator.aop;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import me.cmx.aicodegenerator.annotation.AuthCheck;
import me.cmx.aicodegenerator.exception.BusinessException;
import me.cmx.aicodegenerator.exception.ErrorCode;
import me.cmx.aicodegenerator.model.entity.User;
import me.cmx.aicodegenerator.model.enums.UserRoleEnum;
import me.cmx.aicodegenerator.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component // Aspect没有用Component封装 所以要加Component把它放进容器 不放进容器你怎么生效啊？
public class AuthInterceptor {

    @Resource
    private UserService userService;

    @Around("@annotation(authCheck)")
    public Object doIntercept(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        // 获取这个方法需要的权限
        String mustRole = authCheck.mustRole();
        // 获取当前请求 spring ioc容器里就有这个方法可以去拿到request - javaweb serveltRequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 当前用户具有的权限
        User loginUser = userService.getLoginUser(request);
        String loginUserRole = loginUser.getUserRole();
        UserRoleEnum loginUserRoleEnum = UserRoleEnum.getEnumByValue(loginUserRole);
        // 判断权限
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        // 不需要权限 放行
        if (mustRoleEnum == null) {
            return joinPoint.proceed();
        }
        // 当前用户没有任何权限 直接拒绝
        if (loginUserRoleEnum == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 当前方法要求有管理员权限 但是当前用户没有 拒绝
        if (UserRoleEnum.ADMIN.equals(mustRoleEnum) && !UserRoleEnum.ADMIN.equals(loginUserRoleEnum)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 通过权限 放行
        return joinPoint.proceed();
    }
}
