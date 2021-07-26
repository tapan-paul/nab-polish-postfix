package au.com.nab.postfix.operation;

public class NegateCommand extends AbstractCommand{

    @Override
    public void execute() {
        getCalculator().negate();
    }
}
