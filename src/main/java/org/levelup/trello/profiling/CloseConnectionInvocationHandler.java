package org.levelup.trello.profiling;

import lombok.RequiredArgsConstructor;
import org.levelup.trello.jdbc.JdbcConnectionService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class CloseConnectionInvocationHandler implements InvocationHandler {
    private final JdbcConnectionService jdbcConnectionService;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().equals("closeConnection")) {
            long start = System.nanoTime();

            Object result = method.invoke(jdbcConnectionService, args);

           long end = System.nanoTime();
            System.out.println("Time to close connection: " + (end - start) + "ns");

            return result;
        }
        return method.invoke(jdbcConnectionService, args);
    }
}
