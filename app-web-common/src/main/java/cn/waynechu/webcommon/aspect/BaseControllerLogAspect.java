package cn.waynechu.webcommon.aspect;

import cn.waynechu.webcommon.util.JsonBinder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Controller层方法监控切面
 *
 * @author zhuwei
 * @date 2019/03/22 14:16
 */
@Slf4j
public abstract class BaseControllerLogAspect {

    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public void controllerLog() {
        // do nothing here.
    }

    @Before(value = "controllerLog() && @annotation(logAnnotation)")
    public void doBefore(JoinPoint joinPoint, ApiOperation logAnnotation) {
        log.info("{}开始调用, 参数: {}", logAnnotation.value(), this.getPrintArgsStr(joinPoint));

        // 记录调用开始的时间
        threadLocal.set(System.currentTimeMillis());

        // 添加MDC记录 url:请求地址
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            MDC.put("url", ((ServletRequestAttributes) attributes).getRequest().getRequestURI());
        }
    }

    @After(value = "controllerLog() && @annotation(logAnnotation)")
    public void doAfter(JoinPoint joinPoint, ApiOperation logAnnotation) {
        // do nothing here.
    }

    @AfterReturning(value = "controllerLog() && @annotation(logAnnotation)", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, ApiOperation logAnnotation, Object result) {
        Long timeTaken = System.currentTimeMillis() - threadLocal.get();
        // 添加MDC记录 timeTaken:调用耗时
        MDC.put("timeTaken", String.valueOf(timeTaken));

        String jsonResult = JsonBinder.buildAlwaysBinder().toJson(result);
        log.info("{}结束调用, 耗时: {}ms, 返回值: {}", logAnnotation.value(), timeTaken, jsonResult);

        threadLocal.remove();
    }

    /**
     * 获取不打印的入参类型，覆写该方法可过滤指定类型的方法参数
     * <p>
     * 默认提供 {@code HttpServletResponse}、{@code MultipartFile}、{@code Invisible} 三种类型
     *
     * @return 不打印的入参类型
     */
    protected Collection<Class> excludePrintClass() {
        ArrayList<Class> excludePrintClass = new ArrayList<>(3);
        excludePrintClass.add(HttpServletResponse.class);
        excludePrintClass.add(MultipartFile.class);
        excludePrintClass.add(Invisible.class);
        return excludePrintClass;
    }

    private String getPrintArgsStr(JoinPoint joinPoint) {
        ArrayList<Object> args = new ArrayList<>();
        for (Object arg : joinPoint.getArgs()) {
            boolean isInstance = false;
            for (Class clazz : this.excludePrintClass()) {
                if (clazz.isInstance(arg)) {
                    isInstance = true;
                    break;
                }
            }

            if (!isInstance) {
                args.add(arg);
            }
        }
        return JsonBinder.buildAlwaysBinder().toJson(args);
    }
}