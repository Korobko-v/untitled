package org.levelup.trello.profiling;

import lombok.RequiredArgsConstructor;
import org.levelup.trello.jdbc.JdbcConnectionService;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
@RequiredArgsConstructor
public class OpenConnectionServiceInvocationHandler implements InvocationHandler{

    private final JdbcConnectionService jdbcConnectionService;



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        if (method.isAnnotationPresent(Profiling.class)) {
        if (method.getName().equals("openConnection")) {
            long start = System.nanoTime();

            Object result = method.invoke(jdbcConnectionService, args);

            long end = System.nanoTime();
            System.out.println("Time to acquiring connection: " + (end - start) + "ns");

            return result;
        }

        return method.invoke(jdbcConnectionService, args); // jdbcConnectionService.method(args)
    }
}
