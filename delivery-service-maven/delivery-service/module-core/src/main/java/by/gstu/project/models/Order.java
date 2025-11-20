package by.gstu.project.models;

public class Order {
    public String name;
    public Courier courier;
    public boolean isFragility;

    public Order(Courier courier, String name, boolean isFragility) {
        this.courier = courier;
        this.name = name;
        this.isFragility = isFragility;
    }

}