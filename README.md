# Reproducer for «No message codec for type»
When a event class is only consumed by `@ConsumeEvent` methods with signature 
```java
@ConsumeEvent(value = "address")
public void listen(MultiMap headers, Body body) {
        ... 
}
```
no message codec is regisered for this class. 
Resulting in 
```shell
java.lang.IllegalArgumentException: No message codec for type: class org.acme.Hello
	at io.vertx.core.eventbus.impl.CodecManager.lookupCodec(CodecManager.java:119)
	at io.vertx.core.eventbus.impl.EventBusImpl.createMessage(EventBusImpl.java:254)
	at io.vertx.core.eventbus.impl.EventBusImpl.publish(EventBusImpl.java:164)
	at io.vertx.core.eventbus.impl.EventBusImpl.publish(EventBusImpl.java:159)
	at org.acme.GreetingResource.hello(GreetingResource.java:23)
	at org.acme.GreetingResource$quarkusrestinvoker$hello_e747664148511e1e5212d3e0f4b40d45c56ab8a1.invoke(Unknown Source)
	at org.jboss.resteasy.reactive.server.handlers.InvocationHandler.handle(InvocationHandler.java:29)
	at io.quarkus.resteasy.reactive.server.runtime.QuarkusResteasyReactiveRequestContext.invokeHandler(QuarkusResteasyReactiveRequestContext.java:114)
	at org.jboss.resteasy.reactive.common.core.AbstractResteasyReactiveContext.run(AbstractResteasyReactiveContext.java:145)
	at io.quarkus.vertx.core.runtime.VertxCoreRecorder$14.runWith(VertxCoreRecorder.java:576)
	at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2449)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1478)
	at org.jboss.threads.DelegatingRunnable.run(DelegatingRunnable.java:29)
	at org.jboss.threads.ThreadLocalResettingRunnable.run(ThreadLocalResettingRunnable.java:29)
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.base/java.lang.Thread.run(Thread.java:833)
```
Adding another method without headers will register a codec.