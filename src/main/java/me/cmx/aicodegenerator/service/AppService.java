package me.cmx.aicodegenerator.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import me.cmx.aicodegenerator.model.dto.app.AppQueryRequest;
import me.cmx.aicodegenerator.model.entity.App;
import me.cmx.aicodegenerator.model.entity.User;
import me.cmx.aicodegenerator.model.vo.AppVO;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * 应用 服务层。
 *
 * @author smuca
 */
public interface AppService extends IService<App> {

    /**
     * 对 APP 信息进行脱敏
     * @param app
     * @return
     */
    AppVO getAppVO(App app);

    /**
     * 对 App Query进行封装 封装成 QueryWrapper用于查询
     * @param appQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(AppQueryRequest appQueryRequest);

    /**
     * 返回脱敏后的 APP 列表
     * @param appList
     * @return
     */
    List<AppVO> getAppVOList(List<App> appList);

    /**
     * 根据 prompt 生成代码
     * @param appId 应用 ID
     * @param message 提示词消息
     * @param LoginUser 登录用户
     * @return AI 工具生成的回复流
     */
    Flux<String> chatToGenCode(Long appId, String message, User LoginUser);

    /**
     * 部署应用
     * @param appId 应用 ID
     * @param loginUser 登录用户
     * @return 应用部署的网址 (用于给人浏览)
     */
    String deployApp(Long appId, User loginUser);
}
