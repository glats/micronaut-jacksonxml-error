package me.jics;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.aop.InterceptorBean;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.core.type.MutableArgumentValue;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.util.*;

@Slf4j
@Singleton
@InterceptorBean(Head.class)
public class HeadInterceptor implements MethodInterceptor<Object, Object> {
    @SneakyThrows
    @Override
    public Object intercept(MethodInvocationContext<Object, Object> context) {

        Request request = (Request) context.getParameterValueMap().get("request");
        String token = request.getHeader().replace("Bearer ", "");

        ObjectMapper mapper = new ObjectMapper();
        Info info = mapper.readValue(new String(Base64.getDecoder().decode(token)), Info.class);
        request.setData(info.getSub().toUpperCase());

        return context.proceed();
    }
}
