package me.jics;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.*;
import io.reactivex.Single;

import javax.validation.Valid;

@Controller("/main")
public class MainController {


    //@Valid doesn't trigger
    @Post("/principal")
    public Single<Response> principal(@Body @Valid Request request) {
        return Single.just(
                Response.builder()
                        .message(String.format("%s-%s", request.getData(), request.getInfo()))
                        .build()
        );
    }

    //Don't wanna pass @Header on my method. Looks bad...
    @Post
    @Head
    public Single<Response> index(@Body @Valid Request request, @Header("Authorization") String authorizationHeader) {
        return Single.just(
                Response.builder()
                        .message(String.format("%s-%s", request.getData(), request.getInfo()))
                        .build()
        );
    }

    //How do i validate my object?
    @Post("/general")
    @General
    public Single<Response> general(HttpRequest<Request> request) {
        Request req = request.getBody().orElseThrow(RuntimeException::new);
        return Single.just(
                Response.builder()
                        .message(String.format("%s-%s", req.getData(), req.getInfo()))
                        .build()
        );
    }

    //Looks like the similar to put @Header("Authorization"). Looks bad IMO.
    @Post("/commander")
    @Commander
    public Single<Response> commander(@Body @Valid Request request, HttpRequest<?> httpRequest) {
        return Single.just(
                Response.builder()
                        .message(String.format("%s-%s", request.getData(), request.getInfo()))
                        .build()
        );
    }
}