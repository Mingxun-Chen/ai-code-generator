package me.cmx.aicodegenerator.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import me.cmx.aicodegenerator.model.dto.chathistory.ChatHistoryQueryRequest;
import me.cmx.aicodegenerator.model.entity.ChatHistory;
import me.cmx.aicodegenerator.model.entity.User;

import java.time.LocalDateTime;

/**
 * 对话历史 服务层。
 *
 * @author smuca
 */
public interface ChatHistoryService extends IService<ChatHistory> {

    /**
     * 添加聊天消息到数据库
     * @param appId 应用 id
     * @param message 聊天消息
     * @param messageType 聊天消息类型(ai / user)
     * @param userId 用户 id
     * @return 插入数据库的结果
     */
    boolean addChatMessage(Long appId, String message, String messageType, Long userId);

    /**
     * 根据应用 ID 删除 app 相关联的消息
     * @param appId 应用ID
     * @return 删除结果
     */
    boolean deleteByAppId(Long appId);

    /**
     * 对 QueryRequest 进行封装
     * @param chatHistoryQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(ChatHistoryQueryRequest chatHistoryQueryRequest);

    /**
     * 查询 app 的 聊天历史
     * @param appId 应用 ID
     * @param pageSize 每页大小
     * @param lastCreateTime 最后创建时间(用于游标查询)
     * @param loginUser 登录用户
     * @return 返回聊天历史
     */
    Page<ChatHistory> listAppChatHistoryByPage(Long appId, int pageSize,
                                               LocalDateTime lastCreateTime,
                                               User loginUser);
}
