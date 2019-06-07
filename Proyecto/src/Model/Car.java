package Model;

import java.util.ArrayList;

public class Car{

    private String model;
    private String brand;
    private String motor;
    private float price;
    private int year;
    //private file photo;
    private int passengers;
    private ArrayList<Accesory> accesories;

    public Car(String model, String brand, String motor, float price, int year, int passengers, ArrayList<Accesory> accesories) {
        this.model = model;
        this.brand = brand;
        this.motor = motor;
        this.price = price;
        this.year = year;
        this.passengers = passengers;
        this.accesories = accesories;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public ArrayList<Accesory> getAccesories() {
        return accesories;
    }

    public void setAccesories(ArrayList<Accesory> accesories) {
        this.accesories = accesories;
    }
}
