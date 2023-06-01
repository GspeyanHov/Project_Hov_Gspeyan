import annotation.Input;
import annotation.InputAnnotationProcessor;

import java.util.Scanner;
/**
 * This class realizes the simple calculation
 * operations via its calc() static method.
 * Also, it overrides Java's base main()
 * method to start the program.
 */
public class Main {
    /**
     * Provides functionality for simple arithmetic calculations
     * using the operations "+", "-", "*", and "/". The calculator
     * only accepts numbers from 0 to 9.
     *
     * @param input A string representing a combination of an operation
     *              and operands.
     * @return A string representing the result of the arithmetic calculation.
     */
    /*
    @Input Annotation for validation parameter String input.
    Not allowed any character values, exclude numbers and simple math operations.
     */
    public static String calc(@Input String input) {
        /*
        used String class's split() method
        to get each String literal from input param
        and assigned them to the String array variable named inputParts.
         */
        String[] inputParts = input.split("");
        /*
        if operator checks input param size is appropriate for required operation type.
        It must be only three digits (firs operand, math operation and second operand).
        If here are much or not as many digits' method throws IllegalArgumentException()
        with message informing user the operation format is incorrect.
         */
        if (inputParts.length != 3) {
            throw new IllegalArgumentException("please input correct format! ");
        }
        /*
          got the first String literal from array of Strings
          cast it to numeric (integer) type value
          assigned it to leftOperand variable.
         */
        int leftOperand = Integer.parseInt(inputParts[0]);
        /*
          got the third String literal from array of Strings
          cast it to numeric (integer) type value
          assigned it to rightOperand variable.
         */
        int rightOperand = Integer.parseInt(inputParts[2]);
        /*
          the variable result must return the total result
          of calculations.
         */
        double result;
        /*
          Used Switch operator to give JVM to understand in
          which character input case provided by user, which
          math operation need to be done.
          Appropriate for character '+' to do add operation
          for '-' sub, for '*' multi, for '/' division, and
          after the type of the operation the value assigned to
          result variable.
         */
        switch (inputParts[1]) {
            case "+" -> result = leftOperand + rightOperand;
            case "-" -> result = leftOperand - rightOperand;
            case "*" -> result = leftOperand * rightOperand;
            case "/" -> {
                /*
                  if operator validates the case of division to zero
                  which is not allowed. If the operation had been done
                  it throws ArithmeticException with message informing
                  user for about not allowed math operation.
                 */
                if (rightOperand == 0) {
                    throw new ArithmeticException("Division of 0 not allowed! ");
                }
                result = (double) leftOperand / rightOperand;
            }
            /*
              all other (which not required) operations may be done by user
              will throw IllegalArgumentException with message informing user
              for about illegal operation.
             */
            default -> throw new IllegalArgumentException("Illegal operation, please try again! ");
        }
        /*
          At the end the total result of calculation was returned,
          by giving it to String's valueOf() method, which receives
          numeric value and returns String.
         */
        return String.valueOf(result);
    }

    /**
     * Java's default main method. From here starts the main thread of the program's process.
     * It is responsible for execution other method calls
     * and from here starts the program.
     * @param args An array of String literals
     * @throws ReflectiveOperationException  checked exception for the case that something
     *                                       happened in the stack trace of reflective calls.
     *                                       It comes from InputAnnotationProcess processInput() method.
     * @return An exit code of program (only void method that have a return type).
     */
    public static void main(String[] args) throws ReflectiveOperationException {
        /*
          Created Scanner obj and passed it the class System's in
          (InputStream static final field)
          and assigned it to sc variable to get the console.
         */
        Scanner sc = new Scanner(System.in);
        /*
          called the System class's out (OutputStream)
          static method printLn() and receives the String text
          needed to execute in console.
         */
        System.out.println("Please input arithmetic operations you want to do! ");
        /*
          Scanner class's nextLine() method called and
          assigned to operation variable, to process the
          needed operation at the next line of console.
         */
        String operation = sc.nextLine();
        /*
          Called the InputAnnotationProcessor custom class's
          processInput() static method and passed this class,
          method name and operation variable.
          It processes the Input annotation to validate
          (allowed only numeric, math(+,-,*,/) String literals)
          the String Input parameter of calc() method.
         */
        InputAnnotationProcessor.processInput(Main.class, "calc", operation);
        /*
          Called the calc () function to execute the
          main functionality of required calculator and
          passed it to String calcOperations variable.
         */
        String calcOperations = calc(operation);
        /*
          Shows the result of math operation.
         */
        System.out.print("Result = " + calcOperations);
    }
}
