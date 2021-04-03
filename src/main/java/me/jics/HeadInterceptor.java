package me.jics;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.util.Base64;
import java.util.List;

@Slf4j
@Singleton
public class HeadInterceptor implements MethodInterceptor<Object, Object> {
    @SneakyThrows
    @Override
    public Object intercept(MethodInvocationContext<Object, Object> context) {

        Request request = (Request) context.getParameterValueMap().get("request");
        // Where do i get Authorization header?
        // i.e String token = (String) context.getParameterValueMap().get("Authorization");
        String token = (String) context.getParameterValues()[1];

//        String token = "eyJhdWQiOiJ0ZXN0IiwiaXNzIjoidGVzdCIsInN1YiI6InRlc3QiLCJleHAiOjExMTExMTEsImlhdCI6MTExMTExMTEsImRhdGEiOiJ0ZXN0In0=";

        ObjectMapper mapper = new ObjectMapper();
        Info info = mapper.readValue(new String(Base64.getDecoder().decode(token)), Info.class);
        request.setData(info.getSub().toUpperCase());

        return context.proceed();
    }
}
