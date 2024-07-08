package Buoi6_CF;

public class WindowApp {
    public static void main(String[] args) {
        
        CommandProcessor commandProcessorRemote = CommandProcessor.makeCommandProcessor();
        Model modelRemote = new Model();

        View view = new View();
        view.setModelRemote(modelRemote);
        view.setCommandProcessorRemote(commandProcessorRemote);

    }
}
