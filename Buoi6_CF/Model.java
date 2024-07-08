package Buoi6_CF;

import java.util.Observable;

public class Model extends Observable{
    private double result;

    public void C2F(double celsius){
        result = celsius * 9/5 + 32;
        changeState();
    }
    public void F2C(double fahrenheit){
        result = (fahrenheit - 32) * 5/9;
        changeState();
    }
    
    public double getResult() {
        return result;
    }
    public void changeState(){
        setChanged();
        notifyObservers();
    }
}


