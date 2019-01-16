package com.soldier.controller;

import com.soldier.domain.SysLog;
import com.soldier.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    private Date begintime;
    private Class clazz;
    private Method method;
    /**
     * 前置通知
     *
     * 开始时间
     * 访问的类
     * 访问的方法(方法名称 方法的类名)
     * @param joinPoint
     */
    @Before("execution(* com.soldier.controller.*.*(..))")
    public void dobefore(JoinPoint joinPoint) throws NoSuchMethodException {
        try {
            //获取开始时间
            begintime = new Date();
            //访问的类
            clazz = joinPoint.getTarget().getClass();
            //获取方法名称
            String methodName = joinPoint.getSignature().getName();
            //获取方法参数
            Object[] args = joinPoint.getArgs();

            //获取方法对象
            if (args==null&&args.length==0){
                method = clazz.getMethod(methodName);
            }else {
                Class[] classes = new Class[args.length];
                for (int i = 0; i < classes.length; i++) {
                    classes[i]=args[i].getClass();
                }
                method = clazz.getMethod(methodName, classes);
            }
        }  catch (Exception e) {
            System.out.println("************"+e.getMessage());
        }

    }
    @After("execution(* com.soldier.controller.*.*(..))")
    public void doafter(JoinPoint joinPoint){
        //获取访问时长
        try {
            long time= new Date().getTime()-begintime.getTime();
            //获取url
            String url="";
            if (clazz!=null && method!=null && clazz!=LogAop.class){
                //获取类上的RequestMapping路径
                RequestMapping annotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
                if (annotation!=null){
                    String[] values = annotation.value();
                    String[] paths = annotation.path();

                    //获取方法上的RequstMapping路径
                    RequestMapping annotation1 = method.getAnnotation(RequestMapping.class);
                    if (annotation1!=null){
                        String[] value = annotation1.value();
                        String[] path = annotation1.path();
                        if (values!=null&&values.length!=0){
                            if (value!=null&&value.length!=0){
                                url=values[0]+value[0];
                            }else {
                                url=values[0]+path[0];
                            }

                        }else {
                            if (value!=null&&value.length!=0){
                                url=paths[0]+value[0];
                            }else {
                                url=paths[0]+path[0];
                            }
                        }

                        //获取访问ip
                        String ip = request.getRemoteAddr();
                        //获取当前操作的用户
                        // 可以通过securityContext获取，也可以从request.getSession中获取
                        SecurityContext context = SecurityContextHolder.getContext();
                        String username = ((User) (context.getAuthentication().getPrincipal())).getUsername();


                        //将日志相关信息封装到SysLog对象
                        SysLog sysLog = new SysLog();
                        sysLog.setExecutionTime(time); //执行时长
                        sysLog.setIp(ip);
                        sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                        sysLog.setUrl(url);
                        sysLog.setUsername(username);
                        sysLog.setVisitTime(begintime);

                        sysLogService.add(sysLog);


                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
