package au.com.nab.postfix.operation;

@FunctionalInterface
/**
 * command pattern used for each of the opeatiosns allowed
 */
public interface ICommand {

    void execute();
}
