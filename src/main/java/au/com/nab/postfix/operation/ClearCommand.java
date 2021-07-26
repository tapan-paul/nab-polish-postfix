package au.com.nab.postfix.operation;

public class ClearCommand extends AbstractCommand {

    @Override
    public void execute() {
        getCalculator().clear();
    }
}
