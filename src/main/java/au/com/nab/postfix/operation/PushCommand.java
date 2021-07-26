package au.com.nab.postfix.operation;

import java.math.BigDecimal;
import java.util.Optional;


public class PushCommand extends AbstractCommand {

    private Optional<BigDecimal> value;

    public PushCommand(Optional<BigDecimal> v) {
        this.value = v;
    }

    @Override
    public void execute() {
        getCalculator().push(value);
    }
}
