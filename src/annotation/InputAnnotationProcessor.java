package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * This class process the annotation Input
 * via its processInput() static method
 * using reflection technique.
 */
public class InputAnnotationProcessor {

    /**
     * ProcessInput() static method finds the provided @Input
     * parameter annotation and does the all constrained functionality
     * of @Input annotation using reflection.
     * @param targetClass A Java's Class type reference passed
     *                    to find the method in which parameter
     *                    is annotated.
     * @param methodName  A String name of method need to scan
     *                    Class and find to annotate param.
     * @param params A String one or many annotated parameters
     *               that receive a method
     * @throws ReflectiveOperationException if something will happen in stack trace by
     *                                      reflective calls (Class or method doesn't exist...)
     */
    public static void processInput(Class<?> targetClass, String methodName, String... params)
            throws ReflectiveOperationException {
        /*
          Called Class's getDeclaredFields() method and
          passed method name, and class which need to be
          scanned by JVM to find method for realizing reflection.
          It returns Java's base Method type so
          assigned it to method variable.
         */
        Method method = targetClass.getDeclaredMethod(methodName, String.class);
        /*
          sets methods accessibility true
         */
        method.setAccessible(true);
        /*
          Called getParameterAnnotations() method to find
          all parameter annotations. It returns Java's
          base Annotation array two-dimensional array. In
          this case there is only one parameter so only one
          parameter annotation.
         */
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        /*
          Got the parameter annotation (that is 0 / 0) from array.
          Also, it assigned to annotation variable.
         */
        Annotation annotation = parameterAnnotations[0][0];
        /*
          If operator checks and validates is found
          annotation can be an object of @Input parameter
          annotation, so assigned got the @input's regex()
          and message() methods and assigned appropriate
          String regex and message variables. Same thing had
          been done for params[0] (In this case it is one) it is String inputValue.
          So, if inputValue will match with regex variable, validation will pass,
          against it will throw IllegalArgumentException and give @input's message.
         */
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
