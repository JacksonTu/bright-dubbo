package com.tml.common.core.constant;

/**
 *  公共常量
 * @author JacksonTu
 * @date 2019/5/10 13:59
 **/
public interface CommonConstant {

    /**
     * 目录
     */
    int CATALOG = 0;
    /**
     * 菜单
     */
    int MENU = 1;
    /**
     * 按钮
     */
    int BUTTON = 2;

    /** 定时任务状态 */
    /**
     * 正常
     */
    int NORMAL = 0;
    /**
     * 暂停
     */
    int PAUSE = 1;

    /** 文件存储*/
    /**
     * 本地
     */
    int LOCAL = 0;
    /**
     * fastdfs
     */
    int FASTDFS = 1;
    /**
     * 七牛云
     */
    int QINIU = 2;
    /**
     * 阿里云
     */
    int ALIYUN = 3;
    /**
     * 腾讯云
     */
    int QCLOUD = 4;

    /**
     * 菜单，按钮状态
     * 是
     */
    int YES = 0;
    /**
     * 不是
     */
    int NO = 1;
    /**
     * 开启
     */
    int OPEN = 0;
    /**
     * 关闭
     */
    int CLOSE = 1;


    /**
     * 自定义错误
     */
    String X_ERROR = "x.servlet.exception.code";
    String X_ERROR_CODE = "x.servlet.exception.error";
    String X_ERROR_MESSAGE = "x.servlet.exception.message";
    String X_ACCESS_DENIED = "x.access.denied";

    /**
     * 超级管理员ID
     */
    int SUPER_ADMIN = 1;

    /**
     * 客户端ID KEY
     */
    String SIGN_CLIENT_ID_KEY = "clientId";

    /**
     * 客户端秘钥 KEY
     */
    String SIGN_CLIENT_SECRET_KEY = "clientSecret";

    /**
     * 随机字符串 KEY
     */
    String SIGN_NONCE_KEY = "nonce";
    /**
     * 时间戳 KEY
     */
    String SIGN_TIMESTAMP_KEY = "timestamp";
    /**
     * 签名类型 KEY
     */
    String SIGN_SIGN_TYPE_KEY = "signType";
    /**
     * 签名结果 KEY
     */
    String SIGN_SIGN_KEY = "sign";


    /**
     * 默认最小页码
     */
    long MIN_PAGE = 0;
    /**
     * 最大显示条数
     */
    long MAX_LIMIT = 1000;
    /**
     * 默认页码
     */
    long DEFAULT_PAGE = 1;
    /**
     * 默认显示条数
     */
    long DEFAULT_LIMIT = 10;
    /**
     * 页码 KEY
     */
    String PAGE_KEY = "page";
    /**
     * 显示条数 KEY
     */
    String PAGE_LIMIT_KEY = "limit";
    /**
     * 排序字段 KEY
     */
    String PAGE_SORT_KEY = "sort";
    /**
     * 排序方向 KEY
     */
    String PAGE_ORDER_KEY = "order";


    /**
     * JWT用户名
     */
    String JWT_DEFAULT_USERNAME = "username";


    /**
     * 登陆Token
     */
    String JWT_DEFAULT_TOKEN_NAME = "token";

    /**
     * JWT Token默认密钥
     */
    String JWT_DEFAULT_SECRET = "666666";

    /**
     * 签发人
     */
    String JWT_DEFAULT_ISSUER = "JacksonTu";

    /**
     * 签发的目标
     */
    String JWT_DEFAULT_AUDIENCE = "web";

    /**
     * JWT 默认过期时间，3600L，单位秒
     */
    int JWT_DEFAULT_EXPIRE_SECOND = 3600;


    /**
     * 登录用户Token令牌缓存KEY前缀
     */
    String JWT_PREFIX_USER_TOKEN = "hdw_user_token_";

    /**
     * 系统日志类型： 操作
     */
    int LOG_TYPE_0 = 0;

    /**
     * 操作日志类型： 查询
     */
    int OPERATE_TYPE_1 = 1;

    /**
     * 操作日志类型： 添加
     */
    int OPERATE_TYPE_2 = 2;

    /**
     * 操作日志类型： 更新
     */
    int OPERATE_TYPE_3 = 3;

    /**
     * 操作日志类型： 删除
     */
    int OPERATE_TYPE_4 = 4;

    /**
     * 操作日志类型： 导入
     */
    int OPERATE_TYPE_5 = 5;

    /**
     * 操作日志类型： 导出
     */
    int OPERATE_TYPE_6 = 6;
}
