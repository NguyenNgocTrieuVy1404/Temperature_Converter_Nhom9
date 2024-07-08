package Buoi6_CF;

public class CommandProcessor {

    private static CommandProcessor commandProcessorRemote = null;
    
    private CommandProcessor(){}

    public static CommandProcessor makeCommandProcessor(){
        if(commandProcessorRemote == null){
            commandProcessorRemote = new CommandProcessor();
        }
        return commandProcessorRemote;
    }

    //method
    public void execute(Command commandRemote) {
        commandRemote.execute();
    }
}
