package com.tml.common.core.constant;

/**
 * @author JacksonTu
 * @version 1.0
 *  Websocket常量类
 * @date 2020/10/15 17:02
 */
public interface WebsocketConstant {

    /**
     * 消息json key:cmd
     */
    String MSG_CMD = "cmd";

    /**
     * 消息json key:msgId
     */
    String MSG_ID = "msgId";

    /**
     * 消息json key:msgTitle
     */
    String MSG_TITLE = "msgTitle";

    /**
     * 消息json key:msgTxt
     */
    String MSG_TXT = "msgTxt";

    /**
     * 消息json key:userId
     */
    String MSG_USER_ID = "userId";

    /**
     * 消息类型 heartCheck
     */
    String CMD_CHECK = "heartCheck";

    /**
     * 消息类型 user 用户消息
     */
    String CMD_USER = "user";

    /**
     * 消息类型 topic 系统通知
     */
    String CMD_TOPIC = "topic";

    /**
     * 消息类型 revoke 撤销
     */
    String CMD_REVOKE = "revoke";

    /**
     * 消息类型 email
     */
    String CMD_EMAIL = "email";

    /**
     * 消息类型 sign 会议签到
     */
    String CMD_SIGN = "sign";

    /**
     * 消息类型 新闻发布/取消
     */
    String NEWS_PUBLISH = "publish";

    /**
     * 通告对象类型（USER:指定用户，ALL:全体用户）
     */
    String MSG_TYPE_USER = "USER";
    String MSG_TYPE_ALL = "ALL";

    /**
     * 发布状态（0未发布，1已发布，2已撤销）
     */
    String NO_SEND = "0";
    String HAS_SEND = "1";
    String HAS_REVOKE = "2";

    /**
     * 阅读状态（0未读，1已读）
     */
    String HAS_READ_FLAG = "1";
    String NO_READ_FLAG = "0";

    /**
     * 优先级（L低，M中，H高）
     */
    String PRIORITY_L = "L";
    String PRIORITY_M = "M";
    String PRIORITY_H = "H";

    /**
     * 短信模板方式  0 .登录模板、1.注册模板、2.忘记密码模板
     */
    String SMS_TPL_TYPE_0 = "0";
    String SMS_TPL_TYPE_1 = "1";
    String SMS_TPL_TYPE_2 = "2";
}
