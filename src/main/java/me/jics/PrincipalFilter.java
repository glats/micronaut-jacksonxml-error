package me.jics;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.FilterOrderProvider;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import lombok.SneakyThrows;
import org.reactivestreams.Publisher;

import java.util.Base64;

@Filter("/main/principal")
public class PrincipalFilter implements HttpServerFilter, FilterOrderProvider {
    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        return Flowable.fromPublisher(chain.proceed(request)).map(mutableHttpResponse -> {
            HttpHeaders headers = request.getHeaders();
            Request req = request.getBody(Request.class).orElseThrow(RuntimeException::new);
            String token = headers.getAuthorization().orElse("");
            ObjectMapper mapper = new ObjectMapper();
            Info info = mapper.readValue(new String(Base64.getDecoder().decode(token.replace("Bearer ", ""))), Info.class);
            req.setData(info.getSub().toUpperCase());
            return mutableHttpResponse;
        });
    }
}
