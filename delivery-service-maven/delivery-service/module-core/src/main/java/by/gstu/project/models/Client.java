package by.gstu.project.models;

import java.util.ArrayList;

public class Client {
    public String name;
    public String clientId;
    private ArrayList<Order> orders = new ArrayList<>();

    public Client(String name, String clientId) {
        this.name = name;
        this.clientId = clientId;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void deleteOrder(int i) {
        orders.remove(i);
    }

    public ArrayList<Order> getAllOrders() {
        return orders;
    }
}