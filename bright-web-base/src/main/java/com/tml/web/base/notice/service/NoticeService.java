package com.tml.web.base.notice.service;

import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.tml.api.base.enterprise.service.IEnterpriseService;
import com.tml.api.base.system.entity.SysUser;
import com.tml.api.base.system.entity.SysUserEnterprise;
import com.tml.api.base.system.service.ISysUserEnterpriseService;
import com.tml.api.base.system.service.ISysUserService;
import com.tml.api.notice.entity.SysNotice;
import com.tml.api.notice.service.ISysNoticeService;
import com.tml.common.core.constant.WebsocketConstant;
import com.tml.web.base.websocket.CommonWebSocket;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author JacksonTu
 * @version 1.0
 *  系统消息服务
 * @date 2020/11/22 16:24
 */
public class NoticeService {

    @Resource
    private CommonWebSocket webSocket;

    @DubboReference
    private ISysUserService userService;
    @DubboReference
    private ISysUserEnterpriseService userEnterpriseService;
    @DubboReference
    private IEnterpriseService enterpriseService;
    @DubboReference
    private ISysNoticeService noticeService;


    /**
     * 消息推送
     *
     * @param msgType     通告对象类型（USER:指定用户，ALL:全体用户）
     * @param userIds     指定用户
     * @param msgCategory 消息类型 (1:通知公告2:系统消息)
     * @param priority    优先级（L低，M中，H高）
     * @param title       标题
     * @param msgContent  内容
     * @param createUser  发布人
     */
    public void pushNotice(String msgType, String userIds, String msgCategory, String priority, String title, String msgContent, String createUser) {
        SysNotice notice = new SysNotice();
        notice.setMsgType(msgType);
        notice.setUserIds(userIds);
        notice.setMsgCategory(msgCategory);
        notice.setPriority(priority);
        notice.setTitle(title);
        notice.setMsgAbstract(msgContent);
        notice.setMsgContent(msgContent);
        notice.setCreateUser(createUser);
        Date currentDate = new Date();
        notice.setCreateTime(currentDate);
        notice.setUpdateUser(createUser);
        notice.setUpdateTime(currentDate);
        notice.setStartTime(currentDate);
        notice.setEndTime(currentDate);
        notice.setDelFlag("0");
        notice.setSendStatus("1");
        notice.setSendTime(currentDate);

        boolean flag = noticeService.save(notice);
        if (flag) {
            // 1.全局推送
            if (notice.getMsgType().equals(WebsocketConstant.MSG_TYPE_ALL)) {
                JSONObject obj = new JSONObject();
                obj.put(WebsocketConstant.MSG_CMD, WebsocketConstant.CMD_TOPIC);
                obj.put(WebsocketConstant.MSG_ID, notice.getId());
                obj.put(WebsocketConstant.MSG_TITLE, notice.getTitle());
                obj.put(WebsocketConstant.MSG_TXT, notice.getMsgAbstract());
                webSocket.sendAllMessage(obj.toJSONString());
            } else {
                // 2.插入用户通告阅读标记表记录
                String userId = notice.getUserIds();
                String[] userIdArr = userId.substring(0, (userId.length() - 1)).split(",");
                JSONObject obj = new JSONObject();
                obj.put(WebsocketConstant.MSG_CMD, WebsocketConstant.CMD_USER);
                obj.put(WebsocketConstant.MSG_ID, notice.getId());
                obj.put(WebsocketConstant.MSG_TITLE, notice.getTitle());
                obj.put(WebsocketConstant.MSG_TXT, notice.getMsgAbstract());
                webSocket.sendMoreMessage(userIdArr, obj.toJSONString());
            }
        }
    }

    /**
     * 消息推送
     *
     * @param enterpriseId 企业ID
     * @param msgCategory  消息类型 (1:通知公告2:系统消息)
     * @param priority     优先级（L低，M中，H高）
     * @param title        标题
     * @param msgContent   内容
     * @param createUser   发布人
     */
    public void pushNoticeByEnterpriseId(String enterpriseId, String msgCategory, String priority, String title, String msgContent, String createUser) {
        Set<Long> userIdSet = Sets.newHashSet();
        Map<String, Object> columnMap = Maps.newHashMap();
        if (StringUtils.isNoneBlank(enterpriseId)) {
            columnMap.put("enterprise_Id", enterpriseId);
        }
        List<SysUser> users = userService.listByMap(columnMap);
        if (users != null) {
            users.forEach(user -> {
                userIdSet.add(user.getId());
            });
        }
        columnMap.clear();
        if (StringUtils.isNoneBlank(enterpriseId)) {
            columnMap.put("enterprise_Id", enterpriseId);
        }
        List<SysUserEnterprise> userEnterprises = userEnterpriseService.listByMap(columnMap);
        if (userEnterprises != null) {
            userEnterprises.forEach(userEnterprise -> {
                userIdSet.add(userEnterprise.getUserId());
            });
        }
        if (userIdSet != null && userIdSet.size() > 0) {
            StringBuffer sb = new StringBuffer();
            int i = 0;
            for (Long userId : userIdSet) {
                sb.append(userId);
                if (i < userIdSet.size() - 1) {
                    sb.append(",");
                }
                i++;
            }
            String userIds = sb.toString();

            SysNotice notice = new SysNotice();
            notice.setMsgType("USER");
            notice.setUserIds(userIds);
            notice.setMsgCategory(msgCategory);
            notice.setPriority(priority);
            notice.setTitle(title);
            notice.setMsgAbstract(msgContent);
            notice.setMsgContent(msgContent);
            notice.setCreateUser(createUser);
            Date currentDate = new Date();
            notice.setCreateTime(currentDate);
            notice.setUpdateUser(createUser);
            notice.setUpdateTime(currentDate);
            notice.setStartTime(currentDate);
            notice.setEndTime(currentDate);
            notice.setSendStatus("1");
            notice.setDelFlag("0");
            notice.setSendTime(currentDate);

            boolean flag = noticeService.save(notice);
            if (flag) {
                // 1.全局推送
                if (notice.getMsgType().equals(WebsocketConstant.MSG_TYPE_ALL)) {
                    JSONObject obj = new JSONObject();
                    obj.put(WebsocketConstant.MSG_CMD, WebsocketConstant.CMD_TOPIC);
                    obj.put(WebsocketConstant.MSG_ID, notice.getId());
                    obj.put(WebsocketConstant.MSG_TITLE, notice.getTitle());
                    obj.put(WebsocketConstant.MSG_TXT, notice.getMsgAbstract());
                    webSocket.sendAllMessage(obj.toJSONString());
                } else {
                    // 2.插入用户通告阅读标记表记录
                    String userId = notice.getUserIds();
                    String[] userIdArr = userId.substring(0, (userId.length() - 1)).split(",");
                    JSONObject obj = new JSONObject();
                    obj.put(WebsocketConstant.MSG_CMD, WebsocketConstant.CMD_USER);
                    obj.put(WebsocketConstant.MSG_ID, notice.getId());
                    obj.put(WebsocketConstant.MSG_TITLE, notice.getTitle());
                    obj.put(WebsocketConstant.MSG_TXT, notice.getMsgAbstract());
                    webSocket.sendMoreMessage(userIdArr, obj.toJSONString());
                }
            }
        }
    }
}
