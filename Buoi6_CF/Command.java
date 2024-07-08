package Buoi6_CF;

public abstract class Command {
    protected Model modelRemote;
    protected double fahrenheit;
    protected double celsius;
    public Command(Model modelRemote, double fahrenheit, double celsius){
        this.modelRemote = modelRemote;
        this.fahrenheit = fahrenheit;
        this.celsius = celsius;
    }
    public abstract void execute();
}
