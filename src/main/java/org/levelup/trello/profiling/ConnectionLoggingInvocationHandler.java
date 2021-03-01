package org.levelup.trello.profiling;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public final class ConnectionLoggingInvocationHandler implements InvocationHandler {

    private static ConnectionLoggingInvocationHandler instance;
    public Map<Connection, Connection> map;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
