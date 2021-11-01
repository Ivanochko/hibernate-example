package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public class DynamicInvocationHandler implements InvocationHandler {

    Logger log = Logger.getLogger("DynamicInvocationHandler ::: ");

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        log.info(String.format("Invoked method: {%s}", method.getName()));

        return 42;
    }
}