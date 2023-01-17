package org.acme;

import io.quarkus.vertx.ConsumeEvent;
import io.vertx.core.MultiMap;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Listener {

    @ConsumeEvent(value = "hello")
    public void listen(MultiMap headers, Hello hello) {
        System.out.println(headers);
        System.out.println(hello);
    }

    /*
    Codec for Hello is registered if this is included
    @ConsumeEvent(value = "hello")
    public void listenNoHeaders(Hello hello) {
        System.out.println(hello);
    }*/

    @ConsumeEvent(value = "hello2")
    public void listen2(Hello2 hello) {
        System.out.println(hello);
    }
}
