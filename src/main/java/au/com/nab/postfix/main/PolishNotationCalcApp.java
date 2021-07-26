package au.com.nab.postfix.main;

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

        try {
            Scanner in = new Scanner(System.in);
            boolean isRunning = true;

            while (isRunning) {
                String val = in.next();
                    switch (Operation.Type.valueOf(val)) {
                        case PUSH:
                            BigDecimal arg = new BigDecimal(in.next());
                            execute(new PushCommand(Optional.of(arg)));
                            break;
                        case POP:
                            execute(new PopCommand());
                            break;
                        case CLEAR:
                            execute( new ClearCommand());
                            break;
                        case ADD:
                            execute(new AdditionCommand());
                            break;
                        case MUL:
                            execute(new MultiplyCommand());
                            break;
                        case NEG:
                            execute(new NegateCommand());
                            break;
                        case INV:
                            execute( new InverseCommand());
                            break;
                        case PRINT:
                            execute( new PrintCommand());
                            break;
                        case UNDO:
                            execute( new UndoCommand());
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
