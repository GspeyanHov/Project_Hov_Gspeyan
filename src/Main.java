import annotation.Input;
import annotation.InputAnnotationProcessor;

import java.util.Scanner;
public class Main {
    public static String calc(@Input String input) {
        String[] inputParts = input.split("");
        if (inputParts.length != 3) {
            throw new IllegalArgumentException("please input correct format! ");
        }
        int leftOperand = Integer.parseInt(inputParts[0]);
        int rightOperand = Integer.parseInt(inputParts[2]);
        double result;
        switch (inputParts[1]) {
            case "+" -> result = leftOperand + rightOperand;
            case "-" -> result = leftOperand - rightOperand;
            case "*" -> result = leftOperand * rightOperand;
            case "/" -> {
                if (rightOperand == 0) {
                    throw new ArithmeticException("Division of 0 not allowed! ");
                }
                result = (double) leftOperand / rightOperand;
            }
            default -> throw new IllegalArgumentException("Illegal operation, please try again! ");
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) throws ReflectiveOperationException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input arithmetic operations you want to do! ");
        String operation = sc.nextLine();
        InputAnnotationProcessor.processInput(Main.class, "calc", operation);
        String calcOperations = calc(operation);
        System.out.print("Result = " + calcOperations);
    }
}
