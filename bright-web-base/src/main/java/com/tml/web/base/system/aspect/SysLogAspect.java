package com.tml.web.base.system.aspect;

import cn.dev33.satoken.stp.StpUtil;
import com.tml.api.base.system.entity.SysLog;
import com.tml.api.base.system.service.ISysLogService;
import com.tml.api.base.system.service.ISysUserService;
import com.tml.common.core.constant.CommonConstant;
import com.tml.common.core.utils.JacksonUtil;
import com.tml.common.core.utils.SpringUtil;
import com.tml.common.core.vo.LoginUserVo;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.util.Date;

/**
 *  com.tml.web.base.system.aspect
 * @author JacksonTu
 * @date 2019/11/13 14:58
 */
@Aspect
@Component
public class SysLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

    @DubboReference
    private ISysLogService sysLogService;

    @DubboReference
    private ISysUserService sysUserService;


    //@Pointcut("within(@org.springframework.stereotype.Controller *)")
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    @AfterReturning(returning = "object", pointcut = "logPointCut()")
    public void doAfterReturning(Object object) {
        if (object != null) {
            logger.info("response={}", JacksonUtil.toJson(object));
        } else {
            logger.info("response=");
        }

    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        //日志类型
        sysLog.setLogType(0);
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(methodName);
        sysLog.setClassName(className);

        //设置操作类型
        if (sysLog.getLogType() == CommonConstant.LOG_TYPE_0) {
            sysLog.setOperateType(getOperateType(methodName));
        }

        //请求的参数
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException:
                // It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException:
                // getOutputStream() has already been called for this response
                continue;
            }
            arguments[i] = args[i];
        }
        String params = "";
        if (arguments != null) {
            try {
                params = JacksonUtil.toJson(arguments);
            } catch (Exception e) {
                params = arguments.toString();
            }
        }
        sysLog.setParams(params);

        //获取request
        HttpServletRequest request = SpringUtil.getHttpServletRequest();
        //设置IP地址
        sysLog.setClientIp(request.getRemoteAddr());

        boolean isLogin = StpUtil.isLogin();
        if (isLogin) {
            //获取登录用户信息
            LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
            if (loginUserVo != null) {
                sysLog.setLoginName(loginUserVo.getLoginName());
            }

            //耗时
            sysLog.setTime(time);
            sysLog.setCreateTime(new Date());
            //保存系统日志
            sysLogService.save(sysLog);

            //ip
            logger.info("ip={}", request.getRemoteAddr());
            //URL
            logger.info("url={}", request.getRequestURL());
            //method
            logger.info("className={}", className);
            //类方法
            logger.info("methodName={}", methodName);
            //参数
            logger.info("args={}", sysLog.getParams());
            //执行时长
            logger.info("time={}", time + "ms");
        }
    }

    /**
     * 获取操作类型
     */
    private int getOperateType(String methodName) {
        if (methodName.startsWith("list")) {
            return CommonConstant.OPERATE_TYPE_1;
        }
        if (methodName.startsWith("save")) {
            return CommonConstant.OPERATE_TYPE_2;
        }
        if (methodName.startsWith("update")) {
            return CommonConstant.OPERATE_TYPE_3;
        }
        if (methodName.startsWith("delete")) {
            return CommonConstant.OPERATE_TYPE_4;
        }
        if (methodName.startsWith("import")) {
            return CommonConstant.OPERATE_TYPE_5;
        }
        if (methodName.startsWith("export")) {
            return CommonConstant.OPERATE_TYPE_6;
        }
        return CommonConstant.OPERATE_TYPE_1;
    }
}
