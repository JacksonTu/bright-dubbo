package com.tml.common.core.exception;

import com.tml.common.core.api.IErrorCode;

/**
 *  断言处理类，用于抛出各种API异常
 * @author JacksonTu
 * @date 2020/3/28 16:41
 */
public class Asserts {
    public static void fail(String message) {
        throw new BaseException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new BaseException(errorCode);
    }
}
