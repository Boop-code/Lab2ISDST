package by.gstu.project;  // оставить как есть

import by.gstu.project.models.*;  // добавить импорт моделей

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int choice = 0, num;
        boolean frag;
        String name, id, choice2;
        ArrayList<Courier> couriers = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();

        while (choice != 6) {
            System.out.println("=================Меню=================");
            System.out.println("1. Зарегистрировать нового курьера");
            System.out.println("2. Зарегистрировать нового клиента");
            System.out.println("3. Зарегистрировать новый заказ");
            System.out.println("4. Поиск заказа по клиенту");
            System.out.println("5. Отфильтровать курьеров по\n   количествам заказов");
            System.out.println("6. Выход");

            System.out.print("Ваш выбор: ");
            choice = in.nextInt();
            in.nextLine();

            System.out.print("\033[H\033[2J");
            System.out.flush();

            switch (choice) {
                case 1:
                    System.out.println("==========Регистрация курьера==========");
                    System.out.print("Введите имя: ");
                    name = in.nextLine();
                    System.out.print("Введите его id: ");
                    id = in.nextLine();
                    System.out.print("Введите количество выполненых заказов: ");
                    num = in.nextInt();
                    in.nextLine();
                    Courier courier = new Courier(name, id, num);
                    couriers.add(courier);
                    break;
                case 2:
                    System.out.println("=====Регистрация клиента=====");
                    System.out.print("Введите имя: ");
                    name = in.nextLine();
                    System.out.print("Введите его id: ");
                    id = in.nextLine();
                    Client client = new Client(name, id);
                    clients.add(client);
                    break;
                case 3:
                    System.out.println("=====Зарегистрировать заказ=====");
                    System.out.print("Введите название заказа: ");
                    name = in.nextLine();
                    System.out.print("Введите номер курьера: ");
                    num = in.nextInt() - 1;
                    in.nextLine();
                    System.out.print("Товар хрупкий?(да/нет): ");
                    choice2 = in.nextLine();
                    if (choice2.equals("да")) {
                        frag = true;
                    } else {
                        frag = false;
                    }
                    Order order = new Order(couriers.get(num), name, frag);
                    System.out.print("Введите номер клиента(кому принадлежит заказ): ");
                    num = in.nextInt() - 1;
                    in.nextLine();
                    clients.get(num).addOrder(order);
                    break;
                case 4:
                    System.out.println("Доступные клиенты:");
                    for (int i = 0; i < clients.size(); i++) {
                        System.out.println((i + 1) + ". " + clients.get(i).name);
                    }
                    System.out.print("Введите номер клиента для поиска заказов: ");
                    num = in.nextInt() - 1;
                    in.nextLine();
                    ArrayList<Order> orders = clients.get(num).getAllOrders();
                    System.out.printf("Заказы клиента %s:\n", clients.get(num).name);
                    if (orders.isEmpty()) {
                        System.out.println("Заказов нет");
                    } else {
                        for (int i = 0; i < orders.size(); i++) {
                            System.out.println((i+1) + ". " + orders.get(i).name + 
                                             " (Хрупкий: " + orders.get(i).isFragility + ")");
                        }
                    }
                    in.nextLine();
                    break;
                case 5:
                    System.out.print("Введите количество выполненных заказов \nдля фильтрации курьеров: ");
                    num = in.nextInt();
                    for (int i = 0; i < couriers.size(); i++) {
                        if (couriers.get(i).NumbOfDeliver == num ) {
                            System.out.printf("Имя курьера: %s\n", couriers.get(i).name);
                        } else {
                            continue;
                        }
                    }
                    in.nextLine();
                    break;
                case 6:
                    break;
                default:
                    break;
            }
        }
        
        in.close();
    }
}