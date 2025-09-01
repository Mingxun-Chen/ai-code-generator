package me.cmx.aicodegenerator.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import me.cmx.aicodegenerator.model.dto.app.AppQueryRequest;
import me.cmx.aicodegenerator.model.entity.App;
import me.cmx.aicodegenerator.model.vo.AppVO;

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
}
