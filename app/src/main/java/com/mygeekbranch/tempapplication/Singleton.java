package com.mygeekbranch.tempapplication;

public class Singleton {
    private static Singleton sSingleton;
    int temperature;
    private boolean isCheckHumidity;
    private boolean isCheckNightMode;

    public Singleton() {
        temperature = 0;
        isCheckHumidity = false;
        isCheckNightMode = false;

    }

    public static Singleton getSingleton() {
        if (sSingleton == null)
            sSingleton = new Singleton();
        return sSingleton;
    }

    public int getTemperature() {
        return temperature;
    }

    public boolean isCheckHumidity() {
        return isCheckHumidity;
    }

    public void setCheckHumidity(boolean checkHumidity) {
        isCheckHumidity = checkHumidity;
    }

    public boolean isCheckNightMode() {
        return isCheckNightMode;
    }

    public void setCheckNightMode(boolean checkNightMode) {
        isCheckNightMode = checkNightMode;
    }

    public void increaseTemperature() {
        temperature++;

    }

    public void decreaseTemperature() {
        temperature--;
    }


}

