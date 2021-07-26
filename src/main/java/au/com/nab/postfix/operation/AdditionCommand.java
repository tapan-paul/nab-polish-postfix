package au.com.nab.postfix.operation;

public class AdditionCommand extends AbstractCommand {

    @Override
    public void execute() {
        getCalculator().add();
    }
}
