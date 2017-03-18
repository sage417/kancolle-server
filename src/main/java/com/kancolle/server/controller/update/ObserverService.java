package com.kancolle.server.controller.update;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * Created by J.K.SAGE on 2017/3/12.
 */
@Profile("dev")
@Service
public class ObserverService {

    private Executor executor = Executors.newFixedThreadPool(200);

    Single<String> sayHello() {
        return Single
                .create((SingleOnSubscribe<String>) e -> e.onSuccess(helloWorld()))
                .subscribeOn(Schedulers.from(executor));
    }

    String helloWorld() {
        //throw new IllegalArgumentException();
        return "hello world";
    }
}
