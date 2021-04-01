package me.jics;

import io.micronaut.http.annotation.*;
import io.reactivex.Single;

@Controller("/main")
public class MainController {

    @Post
    @Head
    public Single<Response> index(@Body Request request) {
        return Single.just(
                Response.builder()
                        .message(String.format("%s-%s", request.getData(), request.getInfo()))
                        .build()
        );
    }
}