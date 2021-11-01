package proxy;

import daos.Dao;

import java.lang.reflect.Proxy;

public class ProxyUtil {

    public static <T extends Dao> T createProxy(T object) {
        return (T) Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                new Class[]{object.getClass()},
                new DynamicInvocationHandler());
    }
}
