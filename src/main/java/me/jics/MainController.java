package me.jics;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.*;
import io.reactivex.Single;

import javax.validation.Valid;

@Controller("/main")
public class MainController {

    @Post
    @Head
    public Single<Response> index(@RequestBean @Valid Single<Request> singleRequest) {
        return singleRequest.map(request -> Response.builder()
                .message(String.format("%s-%s", request.getData(), request.getInfo()))
                .build());
    }

    @Post("/simple")
    @Head
    public Single<Response> simple(@RequestBean @Valid Request request) {
        return Single.just(Response.builder()
                .message(String.format("%s-%s", request.getData(), request.getInfo()))
                .build()
        );
    }


}