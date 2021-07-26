package au.com.nab.postfix.operation;

public class MultiplyCommand extends AbstractCommand{
    @Override
    public void execute() {
        getCalculator().multiply();
    }
}
