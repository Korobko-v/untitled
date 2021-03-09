package org.levelup.trello.profiling;

import lombok.RequiredArgsConstructor;
import org.levelup.trello.jdbc.JdbcConnectionService;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Arrays;

@RequiredArgsConstructor
public class OpenConnectionServiceInvocationHandler implements InvocationHandler{

    private final JdbcConnectionService jdbcConnectionService;



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Class<?> clazz = jdbcConnectionService.getClass();
        Method classMethod = getOverridenMethod(clazz, method, args);


        //        if (method.isAnnotationPresent(Profiling.class)) {
        if (classMethod.getAnnotation(AliveTime.class) != null) {
            long start = System.nanoTime();

            Object result = method.invoke(jdbcConnectionService, args);

            long end = System.nanoTime();
            System.out.println("Time to acquiring connection: " + (end - start) + "ns");

            ConnectionAliveTimeService.getInstance()
                    .writeTimeWhenConnectionWasOpened((Connection)result);
            return createConnectionProxy((Connection) result);
        }

        return method.invoke(jdbcConnectionService, args); // jdbcConnectionService.method(args)
    }

    private Object createConnectionProxy(Connection connection) {
        return Proxy.newProxyInstance(
                connection.getClass().getClassLoader(),
                connection.getClass().getInterfaces(),
                    new CloseConnectionInvocationHandler(connection));
    }

    private Method findMethod (Class<?> clazz, Method interfaceMethod) {
        Method[] classMethods = clazz.getMethods();
        for (Method classMethod : classMethods) {
            if (classMethod.getName().equals(interfaceMethod.getName())) {
                return classMethod;
            }
        }
        throw new AbstractMethodError("Couldn't find method in class, but it's defined in interface");
    }

    private Method getOverridenMethod(Class <?> clazz, Method interfaceMethod, Object[] args) {
        Class<?>[] argTypes = args == null ? null : (Class<?>[]) Arrays.stream(args)
                .map(arg -> arg.getClass())
                .toArray();

        try {
            return clazz.getMethod(interfaceMethod.getName(), argTypes);
        } catch (NoSuchMethodException e) {
            throw new AbstractMethodError("Couldn't find method in class, but it's defined in interface");
        }
    }
}
