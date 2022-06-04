package com.mygeekbranch.tempapplication;

public class Singleton {
    private static Singleton sSingleton;
    int temperature;

    public Singleton() {
        temperature= 0;

    }

    public static Singleton getSingleton() {
        if (sSingleton == null)
            sSingleton = new Singleton();
        return sSingleton;
    }

    public int getTemperature() {
        return temperature;
    }
    public  void increaseTemperature(){
        temperature++;

    }
    public  void decreaseTemperature(){
        temperature--;
    }


}

