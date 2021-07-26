package au.com.nab.postfix.operation;

public class UndoCommand extends AbstractCommand{
    @Override
    public void execute() {
        getCalculator().undo();
    }
}
