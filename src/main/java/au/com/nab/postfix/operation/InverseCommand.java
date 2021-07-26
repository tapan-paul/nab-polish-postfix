package au.com.nab.postfix.operation;

public class InverseCommand extends AbstractCommand {
    @Override
    public void execute() {
        getCalculator().inverse();
    }
}
