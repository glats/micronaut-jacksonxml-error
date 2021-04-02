package me.jics;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.util.Base64;

@Slf4j
@Singleton
public class GeneralInterceptor implements MethodInterceptor<Object, Object> {
    @SneakyThrows
    @Override
    public Object intercept(MethodInvocationContext<Object, Object> context) {
        HttpRequest<Request> request = (HttpRequest<Request>) context.getParameterValueMap().get("request");
        HttpHeaders headers = request.getHeaders();
        Request req = request.getBody().orElseThrow(RuntimeException::new);
        String token = headers.getAuthorization().orElse("");
        ObjectMapper mapper = new ObjectMapper();
        Info info = mapper.readValue(new String(Base64.getDecoder().decode(token.replace("Bearer ", ""))), Info.class);
        req.setData(info.getSub().toUpperCase());
        return context.proceed();
    }
}
