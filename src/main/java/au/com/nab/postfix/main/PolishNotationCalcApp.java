package au.com.nab.postfix.main;

import au.com.nab.postfix.domain.Calculator;
import au.com.nab.postfix.domain.Operation;
import au.com.nab.postfix.operation.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
/**
 * main app - reads from commandline
 */
public class PolishNotationCalcApp {

    private void run() {
        Calculator instance = Calculator.getInstance();
        try {
            Scanner in = new Scanner(System.in);
            boolean isRunning = true;

            while (isRunning) {
                String val = in.next();
                    switch (Operation.Type.valueOf(val)) {
                        case PUSH:
                            BigDecimal arg = new BigDecimal(in.next());
                            execute(() -> instance.push(Optional.of(arg)));
                            break;
                        case POP:
                            execute(instance::pop);
                            break;
                        case CLEAR:
                            execute(instance::clear);
                            break;
                        case ADD:
                            execute(instance::add);
                            break;
                        case MUL:
                            execute(instance::multiply);
                            break;
                        case NEG:
                            execute(instance::negate);
                            break;
                        case INV:
                            execute(instance::inverse);
                            break;
                        case PRINT:
                            execute(instance::print);
                            break;
                        case UNDO:
                            execute(instance::undo);
                            break;
                        case QUIT:
                            isRunning = false;
                            break;
                        default:
                            throw new IllegalAccessException(" UNKNOWN OPERATION");
                    }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void execute(ICommand command) {
        command.execute();
    }

    public static void main(String[] args){

            PolishNotationCalcApp calcApp = new PolishNotationCalcApp();
            calcApp.run();
        }

}
