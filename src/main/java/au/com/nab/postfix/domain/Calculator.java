package au.com.nab.postfix.domain;

import au.com.nab.postfix.exception.CalculatorException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.Stack;

/**
 * main calc - performs reverse polish notation
 * data structure for operations is a stack
 */
@Slf4j
public class Calculator {

    private final Stack<BigDecimal> calcStack;
    private final Stack<BigDecimal> undo;
    private static final Calculator calculator = new Calculator();

    private Calculator() {
        calcStack = new Stack<>();
        undo = new Stack<>(); // hold last operation values.
    }

    public static Calculator getInstance() {
        return calculator;
    }

    public void push(Optional<BigDecimal> value) {
        calcStack.push(value.orElseThrow());
    }

    public void add() {
        action('+');
    }

    public void negate() {

        if (isCalcStackNotEmpty()) {
            BigDecimal a = calcStack.pop();
            calcStack.push(a.negate());
            undo.clear();
            undo.push(a);
        }
    }

    public void pop() {

        if (isCalcStackNotEmpty()) {
            BigDecimal a = calcStack.pop();
            undo.clear();
            undo.push(a);
        } else {
            throw new CalculatorException("empty stack");
        }
    }

    private boolean isCalcStackNotEmpty() {
        return !calcStack.isEmpty();
    }

    public void clear() {

        undo.clear();
        while (isCalcStackNotEmpty()) {
            undo.push(calcStack.pop());
        }
        calcStack.clear();
    }

    public void multiply() {
        action('*');
    }

    public void inverse() {

        if (isCalcStackNotEmpty()) {
            BigDecimal a = calcStack.pop();
            undo.clear();
            undo.push(a);
            calcStack.push(BigDecimal.ONE.divide(a, 2, RoundingMode.HALF_DOWN));
        }
    }

    // testing purposes
    public int size() {
        return calcStack.size();
    }
    // testing only.
    public BigDecimal top() {
        return calcStack.peek();
    }

    public void print() {
        calcStack.stream().forEach(System.out::println);
    }

    public void undo() {

        //if called either when no undo happened or multiple undos at once
        // undo is only the 'last' operation
        if (isCalcStackNotEmpty() && undo.isEmpty()) {
            throw new CalculatorException(" nothing to undo");
        }

        if (isCalcStackNotEmpty()) {
            calcStack.pop();
        }

        while (!undo.isEmpty()) {
            calcStack.push(undo.pop());
        }

    }

    private void action(char action) {

        if (isCalcStackNotEmpty()) {

            if (calcStack.size() < 2) {
                throw new CalculatorException(" not enough elements in stack to perform  " + action);
            }

            BigDecimal a = calcStack.pop();
            BigDecimal b = calcStack.pop();

            switch (action) {
                case '*':
                    calcStack.push(a.multiply(b));
                    break;
                case '+':
                    calcStack.push(a.add(b));
                    break;
                default:
                    throw new CalculatorException(" unrecognised action");
            }
            undo.clear();
            undo.push(a);
            undo.push(b);

        }
    }

}
