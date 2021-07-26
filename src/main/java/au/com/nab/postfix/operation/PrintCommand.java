package au.com.nab.postfix.operation;

public class PrintCommand extends AbstractCommand{
    @Override
    public void execute() {
        getCalculator().print();
    }
}
