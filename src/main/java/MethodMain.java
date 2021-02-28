import com.iiichz.common.ParamUtils;
import com.iiichz.method.RPCUtils;
import com.iiichz.spring.SpringBaseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public class MethodMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodMain.class);

    public Object process(String message) throws Exception {
        String methodMessage = RPCUtils.getMethodMessage(message);
        List<String> paramMessage = RPCUtils.getParamMessage(message);
        String paramMode = RPCUtils.getParamMode(message);
        List<Integer> builderIndex = RPCUtils.getBuilderIndex(message);
        List<Object> handlers = RPCUtils.getHandlers(message);
        String beanMode = RPCUtils.getBeanMode(message);
        //获取全路径类名和方法名
        String className = StringUtils.substringBeforeLast(methodMessage, ".");
        String methodName = StringUtils.substringAfterLast(methodMessage, ".");
        Class<?> cls = Class.forName(className);

        Object res = null;
        if(paramMessage == null){
            res = processEmptyParamMethod(cls, methodName);
        } else {
            res = processValueParamMethod(cls, className,paramMessage, paramMode, builderIndex, handlers, beanMode, methodName);
        }
        return res;
    }

    private Object processEmptyParamMethod(Class<?> cls, String methodName) throws Exception {
        Method method = null;
        try{
            method = cls.getDeclaredMethod(methodName);
            method.setAccessible(true);
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Cannot find the method which has empty param, please input correct param!");
        }
        Constructor declaredConstructor = cls.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance();
        return method.invoke(o);
    }

    private Object processValueParamMethod(Class<?> cls, String className, List<String> paramMessage, String paramMode,
                                           List<Integer> builderIndex, List<Object> handlers, String beanMode, String methodName) throws Exception {
        //methodParamList是json转换成的方法的参数列表
        List methodParamList = RPCUtils.getMethodParam(className, methodName, paramMessage, paramMode, builderIndex, handlers);
        Class<?>[] cArg = ParamUtils.getMethodParamTypes(cls, methodName, paramMessage, builderIndex, handlers);
        Method method = null;
        try{
            method = cls.getDeclaredMethod(methodName, cArg);
            method.setAccessible(true);
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Can't find the method, Please check your method param!");
        }
        Object[] paramObj = ParamUtils.getMethodParamObj(method, methodParamList);
        if(beanMode != null && beanMode.equals("springBean")){
            Object bean = SpringBaseUtil.getBean(cls);
            if(bean == null){
                throw new Exception("Can not get spring bean! Please inject the bean!");
            }
            return method.invoke(bean, paramObj);
        } else {
            return method.invoke(cls.newInstance(), paramObj);
        }
    }
}