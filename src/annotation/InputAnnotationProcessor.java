package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
public class InputAnnotationProcessor {
    public static void processInput(Class<?> targetClass, String methodName, String... params)
            throws ReflectiveOperationException {
        Method method = targetClass.getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Annotation annotation = parameterAnnotations[0][0];
        if (annotation instanceof Input input) {
            String regex = input.regex();
            String message = input.message();
            String inputValue = params[0];
            if (!inputValue.matches(regex)) {
                throw new IllegalArgumentException(message);
            }
        }
    }
}
