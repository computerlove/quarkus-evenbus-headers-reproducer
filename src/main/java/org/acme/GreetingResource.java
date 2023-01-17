package org.acme;

import io.vertx.core.eventbus.EventBus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/hello")
public class GreetingResource {
    private final EventBus eventBus;

    public GreetingResource(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        try {
            eventBus.publish("hello", new Hello("hello"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            eventBus.publish("hello2", new Hello2("hello2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Hello from RESTEasy Reactive";
    }
}