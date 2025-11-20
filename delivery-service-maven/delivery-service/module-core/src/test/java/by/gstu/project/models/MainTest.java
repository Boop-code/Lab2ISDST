package by.gstu.project.models;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    
    @Test
    void testCourierCreation() {
        // Arrange
        String name = "Иван Курьер";
        String courierId = "C001";
        int numOfDeliveries = 10;

        // Act
        Courier courier = new Courier(name, courierId, numOfDeliveries);

        // Assert
        assertEquals(name, courier.name);
        assertEquals(courierId, courier.courierId);
        assertEquals(numOfDeliveries, courier.NumbOfDeliver);
    }

    @Test
    void testClientCreation() {
        // Arrange
        String name = "Петр Клиент";
        String clientId = "CL001";

        // Act
        Client client = new Client(name, clientId);

        // Assert
        assertEquals(name, client.name);
        assertEquals(clientId, client.clientId);
        assertTrue(client.getAllOrders().isEmpty());
    }

    @Test
    void testOrderCreation() {
        // Arrange
        Courier courier = new Courier("Курьер", "C001", 5);
        String orderName = "Ноутбук";
        boolean fragility = true;

        // Act
        Order order = new Order(courier, orderName, fragility);

        // Assert
        assertEquals(orderName, order.name);
        assertEquals(fragility, order.isFragility);
        assertEquals(courier, order.courier);
    }

    @Test
    void testAddOrderToClient() {
        // Arrange
        Client client = new Client("Клиент", "CL001");
        Courier courier = new Courier("Курьер", "C001", 5);
        Order order = new Order(courier, "Телефон", false);

        // Act
        client.addOrder(order);

        // Assert
        assertEquals(1, client.getAllOrders().size());
        assertEquals(order, client.getAllOrders().get(0));
    }

    @Test
    void testGetAllOrdersFromClient() {
        // Arrange
        Client client = new Client("Клиент", "CL001");
        Courier courier = new Courier("Курьер", "C001", 5);
        
        Order order1 = new Order(courier, "Заказ 1", true);
        Order order2 = new Order(courier, "Заказ 2", false);

        // Act
        client.addOrder(order1);
        client.addOrder(order2);
        ArrayList<Order> orders = client.getAllOrders();

        // Assert
        assertEquals(2, orders.size());
        assertEquals("Заказ 1", orders.get(0).name);
        assertEquals("Заказ 2", orders.get(1).name);
    }

    @Test
    void testCourierFiltering() {
        // Arrange
        ArrayList<Courier> couriers = new ArrayList<>();
        couriers.add(new Courier("Курьер 1", "C001", 10));
        couriers.add(new Courier("Курьер 2", "C002", 5));
        couriers.add(new Courier("Курьер 3", "C003", 10));
        couriers.add(new Courier("Курьер 4", "C004", 3));

        // Act & Assert - фильтрация по 10 заказам
        ArrayList<Courier> filtered = new ArrayList<>();
        for (Courier courier : couriers) {
            if (courier.NumbOfDeliver == 10) {
                filtered.add(courier);
            }
        }

        assertEquals(2, filtered.size());
        assertEquals("Курьер 1", filtered.get(0).name);
        assertEquals("Курьер 3", filtered.get(1).name);
    }

    @Test
    void testEmptyClientOrders() {
        // Arrange
        Client client = new Client("Клиент", "CL001");

        // Act
        ArrayList<Order> orders = client.getAllOrders();

        // Assert
        assertTrue(orders.isEmpty());
    }

    @Test
    void testOrderFragility() {
        // Arrange & Act
        Courier courier = new Courier("Курьер", "C001", 5);
        Order fragileOrder = new Order(courier, "Ваза", true);
        Order nonFragileOrder = new Order(courier, "Книга", false);

        // Assert
        assertTrue(fragileOrder.isFragility);
        assertFalse(nonFragileOrder.isFragility);
    }

    @Test
    void testDeleteOrderFromClient() {
        // Arrange
        Client client = new Client("Клиент", "CL001");
        Courier courier = new Courier("Курьер", "C001", 5);
        
        Order order1 = new Order(courier, "Заказ 1", true);
        Order order2 = new Order(courier, "Заказ 2", false);
        client.addOrder(order1);
        client.addOrder(order2);

        // Act
        client.deleteOrder(0); // Удаляем первый заказ

        // Assert
        assertEquals(1, client.getAllOrders().size());
        assertEquals("Заказ 2", client.getAllOrders().get(0).name);
    }
}