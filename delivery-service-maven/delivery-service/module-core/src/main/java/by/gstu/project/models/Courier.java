package by.gstu.project.models;

public class Courier {
    public String name;
    public String courierId;
    public int NumbOfDeliver;

    public Courier(String name, String courierId, int NumbOfDeliver) {
        this.name = name;
        this.courierId = courierId;
        this.NumbOfDeliver = NumbOfDeliver;
    }
}