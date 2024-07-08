package Buoi6_CF;

public class F2C extends Command{
    public F2C(Model modelRemote, double fahrenheit, double celsius) {
        super(modelRemote, fahrenheit, celsius);
        //TODO Auto-generated constructor stub
    }
    

    @Override
    public void execute() {
        modelRemote.F2C(fahrenheit);;
    }
}
