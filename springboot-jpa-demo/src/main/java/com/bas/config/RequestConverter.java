package com.bas.config;


import com.bas.model.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Configuration
@Slf4j
public class RequestConverter {

    @Value("${com.bas.special.user}")
    private List<String> specialUsers;

    @Pointcut("execution(* com.bas.controller.*Controller.*(..))")
    public void executeService() {
    }

    @Around("executeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        log.info("url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);

        // parse request body
        Map<String, String> userMap = specialUsers.stream().collect(Collectors.toMap(s -> s.split("\\|")[0], s -> s.split("\\|")[1]));

        if (userMap.isEmpty())
            return pjp.proceed();

        Object[] args = pjp.getArgs();
        String userName = null;
        for (Object arg : args) {
            log.info("arg: {}", arg);
            if (arg instanceof UserInfoDTO) {
                UserInfoDTO userInfoDTO = (UserInfoDTO) arg;
                if (userMap.containsKey(userInfoDTO.getName())) {
                    // change to special user name
                    userName = userInfoDTO.getName();
                    userInfoDTO.setName(userMap.get(userInfoDTO.getName()));
                    break;
                }
            }
        }

        Object result = pjp.proceed();
        // rollback special user name
        if (userName != null && result instanceof UserInfoDTO) {
            ((UserInfoDTO) result).setName(userName);
            return result;
        } else {
            return pjp.proceed();
        }
    }

}