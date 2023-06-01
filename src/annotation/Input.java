package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation Input for parameter use only.
 * It allows to validate String Input parameter in Main class.
 * Defines constraints (regex for only numeric String literals 0 - 9 and math operators
 * such as '+', '-', '*', '/').
 * for against gives String message informing that must be provided only numbers and math operators.
 * @Retention - RetentionPolicy A Java.lang.annotation package Java's based annotation
 *              informs JVM that Input annotation need to be executed runtime.
 * @Target - ElementType A Java.lang.annotation package Java's based annotation
 *           informs JVM that Input annotation is only for Parameter use.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
public @interface Input {
    String regex() default "[\\d+\\-*/]+";
    String message() default "Please provide a required arithmetic operation! ";
}
