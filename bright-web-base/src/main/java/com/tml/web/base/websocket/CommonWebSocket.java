package com.tml.web.base.websocket;

import com.alibaba.fastjson2.JSONObject;
import com.tml.common.core.constant.WebsocketConstant;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author JacksonTu
 * @version 1.0
 *  公用websocket工具类
 * @date 2020/10/15 16:53
 */
@Component
@Slf4j
@ServerEndpoint("/ws/common/{userId}") //此注解相当于设置访问URL
public class CommonWebSocket {

    private static final CopyOnWriteArraySet<CommonWebSocket> webSockets = new CopyOnWriteArraySet<>();
    private static final Map<String, Session> sessionPool = new HashMap<String, Session>();
    private Session session;

    /**
     * 连接建立成功
     *
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        try {
            this.session = session;
            webSockets.add(this);
            sessionPool.put(userId, session);
            log.info("【websocket消息】有新的连接，总数为: {}", webSockets.size());
        } catch (Exception e) {
        }
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        try {
            webSockets.remove(this);
            log.info("【websocket消息】连接断开，总数为: {}", webSockets.size());
        } catch (Exception e) {
        }
    }

    /**
     * 收到客户端消息
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("【websocket消息】收到客户端消息: {}", message);
        JSONObject obj = new JSONObject();
        obj.put(WebsocketConstant.MSG_CMD, WebsocketConstant.CMD_CHECK);//业务类型
        obj.put(WebsocketConstant.MSG_TXT, "心跳响应");//消息内容
        session.getAsyncRemote().sendText(obj.toJSONString());
    }

    /**
     * 广播消息
     *
     * @param message
     */
    public void sendAllMessage(String message) {
        log.info("【websocket消息】广播消息: {}", message);
        for (CommonWebSocket webSocket : webSockets) {
            try {
                if (webSocket.session.isOpen()) {
                    webSocket.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 单点消息
     *
     * @param userId
     * @param message
     */
    public void sendOneMessage(String userId, String message) {
        Session session = sessionPool.get(userId);
        if (session != null && session.isOpen()) {
            try {
                log.info("【websocket消息】广播消息: {}", message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 多人单点消息
     *
     * @param userIds
     * @param message
     */
    public void sendMoreMessage(String[] userIds, String message) {
        for (String userId : userIds) {
            Session session = sessionPool.get(userId);
            if (session != null && session.isOpen()) {
                try {
                    log.info("【websocket消息】广播消息: {}", message);
                    session.getAsyncRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
