package au.com.nab.postfix.operation;

public class PopCommand extends AbstractCommand {

    @Override
    public void execute() {
        getCalculator().pop();
    }
}
