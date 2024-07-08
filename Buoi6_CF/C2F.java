package Buoi6_CF;

public class C2F extends Command{
    public C2F(Model modelRemote, double fahrenheit, double celsius) {
        super(modelRemote, fahrenheit, celsius);
    }
    
    @Override
    public void execute() {
        modelRemote.C2F(celsius);;
    }
}
