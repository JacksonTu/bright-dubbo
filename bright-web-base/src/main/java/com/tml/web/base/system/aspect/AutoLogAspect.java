package com.tml.web.base.system.aspect;

import cn.dev33.satoken.stp.StpUtil;
import com.tml.api.base.system.entity.SysLog;
import com.tml.api.base.system.service.ISysLogService;
import com.tml.api.base.system.service.ISysUserService;
import com.tml.common.core.annotation.AutoLog;
import com.tml.common.core.constant.CommonConstant;
import com.tml.common.core.utils.JacksonUtil;
import com.tml.common.core.utils.SpringUtil;
import com.tml.common.core.vo.LoginUserVo;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 *  com.tml.web.base.system.aspect
 * @author JacksonTu
 * @date 2019/11/13 14:44
 */
@Aspect
@Component
public class AutoLogAspect {

    @DubboReference
    private ISysLogService sysLogService;

    @DubboReference
    private ISysUserService sysUserService;

    @Pointcut("@annotation(com.tml.common.core.annotation.AutoLog)")
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

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        AutoLog syslog = method.getAnnotation(AutoLog.class);
        if (syslog != null) {
            //注解上的描述,操作日志内容
            sysLog.setLogContent(syslog.value());
            sysLog.setLogType(syslog.logType());

        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");


        //设置操作类型
        if (sysLog.getLogType() == CommonConstant.LOG_TYPE_0) {
            sysLog.setOperateType(getOperateType(methodName, syslog.operateType()));
        }

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JacksonUtil.toJson(args);
            sysLog.setParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        }
    }

    /**
     * 获取操作类型
     */
    private int getOperateType(String methodName, int operateType) {
        if (operateType > 0) {
            return operateType;
        }
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
