package au.com.nab.postfix.operation;

import au.com.nab.postfix.domain.Calculator;
import lombok.Getter;

@Getter
public abstract class AbstractCommand implements ICommand {

    protected final Calculator calculator = Calculator.getInstance();

}
