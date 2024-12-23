package org.example.menu;

import org.example.dao.assortementDAO.AssortementDaoImpl;
import org.example.dao.clientDAO.ClientDaoImpl;
import org.example.dao.orderDAO.OrderDaoImpl;
import org.example.dao.orderItemDAO.OrderItemDaoImpl;
import org.example.dao.personalDAO.PersonalDaoImpl;
import org.example.dao.personalPositionDAO.PersonalPositionDaoImpl;
import org.example.dao.typeAssortementDAO.TypeAssortementDaoImpl;
import org.example.enums.AggregationType;
import org.example.model.*;
import org.example.service.busines.assortement.AssortementService;
import org.example.service.busines.assortement.AssortementServiceImpl;
import org.example.service.busines.client.ClientService;
import org.example.service.busines.client.ClientServiceImpl;
import org.example.service.busines.order.OrderService;
import org.example.service.busines.order.OrderServiceImpl;
import org.example.service.busines.orderItems.OrderItemService;
import org.example.service.busines.orderItems.OrderItemServiceImpl;
import org.example.service.busines.personal.PersonalService;
import org.example.service.busines.personal.PersonalServiceImpl;
import org.example.service.busines.personalPosition.PersonalPositionService;
import org.example.service.busines.personalPosition.PersonalPositionServiceImpl;
import org.example.service.busines.typeAssortement.TypeAssortementService;
import org.example.service.busines.typeAssortement.TypeAssortementServiceImpl;
import org.example.utils.Input;

import java.time.LocalDate;
import java.util.List;

public class CoffeeShopMenu {
    private static final TypeAssortementService typeAssortementService;
    private static final AssortementService assortementService;
    private static final ClientService clientService;
    private static final PersonalPositionService personalPositionService;
    private static final PersonalService personalService;
    private static final OrderService orderService;
    private static final OrderItemService orderItemService;

    static {
        typeAssortementService = new TypeAssortementServiceImpl(new TypeAssortementDaoImpl());
        assortementService = new AssortementServiceImpl(new AssortementDaoImpl());
        clientService = new ClientServiceImpl(new ClientDaoImpl());
        personalPositionService = new PersonalPositionServiceImpl(new PersonalPositionDaoImpl());
        personalService = new PersonalServiceImpl(new PersonalDaoImpl());
        orderService = new OrderServiceImpl(new OrderDaoImpl());
        orderItemService = new OrderItemServiceImpl(new OrderItemDaoImpl());
    }


    public void show() {
        System.out.println("CoffeeShop menu:");
        ShowMenu.show();
    }

    public int getChoice() {
        int maxChoice = ShowMenu.getSizeMenu();
        return Input.inputInteger(
                "\nPlease enter the number menu: ",
                "Invalid choice, need from " + ShowMenu.MIN_MENU_NUM + " to " + maxChoice,
                ShowMenu.MIN_MENU_NUM,
                maxChoice
        );
    }


    public static void menuItem1Execute() {
        // Добавление ассортимента

        String nameEn = Input.inputString(
                "Введите название на англ: ",
                "Не должно быть пустой..."
        );
        String nameRu = Input.inputString(
                "Введите название на рус: ",
                "Не должно быть пустой..."
        );

        double minPrice = 10;
        double maxPrice = 100000;
        double price = Input.inputDouble(
                "Введите цену: ",
                "Цена от " + minPrice + " до " + maxPrice + "...",
                minPrice,
                maxPrice
        );

        List<TypeAssortement> types = typeAssortementService.getAllTypeAssortement();
        types.forEach(t -> System.out.println(t.getId() + " - " + t.getName()));
        int minId = 1;
        int maxId = 100000;
        int idTypeAssortement = Input.inputInteger(
                "Введите id вида ассортимента: ",
                "Id от " + minId + " до " + maxId + "...",
                minId, maxId
        );

        try {
            Assortement assortement = new Assortement(0, nameEn, nameRu, price, idTypeAssortement);
            assortementService.addAssortement(assortement);
            System.out.println("Ассортимент успешно добавлен!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuItem2Execute() {
        // Добавление бариста
        addPersonal("Бариста");
    }

    public static void menuItem3Execute() {
        // Добавление кондитера
        addPersonal("Кондитер");
    }

    public static void menuItem4Execute() {
        // Добавление клиента

        String name = Input.inputString("Введите имя: ", "Не должно быть пустым...");
        String surname = Input.inputString("Введите фамилию: ", "Не должно быть пустым...");
        String patronymic = Input.inputString("Введите отчество: ", "Не должно быть пустым...");

        LocalDate birthday = Input.inputDate("Дата рождения:");

        String numTel = Input.inputString("Введите номер телефона: ", "Не должно быть пустым...");
        String email = Input.inputString("Введите email: ", "Не должно быть пустым...");
        int discount = Input.inputInteger(
                "Введите скидку: ",
                "Неверный ввод, нужно от 1 до 10",
                1, 10
        );

        try {
            Client client = new Client(0, name, surname, patronymic, birthday, numTel, email, discount);
            clientService.addClient(client);
            System.out.println("Клиент успешно добавлен!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuItem5Execute() {
        // Изменить email кондитеру

        List<Personal> personals = personalService.getPersonalsByPosition("Кондитер");
        personals.forEach(p -> System.out.println(p.getId() + " - " + p.getName() + " - " + p.getEmail()));

        Personal personal = getPersonalByInputId();
        if (personal == null) {
            return;
        }

        String email = Input.inputString("Введите email: ", "Не должно быть пустым...");

        try {
            personalService.updatePersonalEmail(personal, email);
            System.out.println("Email кондитера успешно изменён!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuItem6Execute() {
        // Изменить номер тел баристу

        List<Personal> personals = personalService.getPersonalsByPosition("Бариста");
        personals.forEach(p -> System.out.println(p.getId() + " - " + p.getName() + " - " + p.getNumTel()));

        Personal personal = getPersonalByInputId();
        if (personal == null) {
            return;
        }

        String numTel = Input.inputString("Введите номер тел: ", "Не должен быть пустым...");

        try {
            personalService.updatePersonalNumTel(personal, numTel);
            System.out.println("Номер тел баристы успешно изменён!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuItem7Execute() {
        // Изменить скидку клиента

        List<Client> clients = clientService.getAllClients();
        clients.forEach(c -> System.out.println(c.getId() + " - " + c.getName() + " - " + c.getDiscount()));

        int idClient = getInputId("Введите id клиента: ");
        Client client = clientService.getClientById(idClient);

        int discount = Input.inputInteger(
                "Введите скидку: ",
                "Неверный ввод, нужно от 1 до 10",
                1, 10
        );

        try {
            clientService.updateClientDiscount(client, discount);
            System.out.println("Скидка клиента успешно изменена!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuItem8Execute() {
        // Удалить десерт

        List<Assortement> assortements = assortementService.getAssortementsByType("Десерт");
        assortements.forEach(a -> System.out.println(a.getId() + " - " + a.getNameRu() + " - " + a.getPrice()));

        int idAssortement = getInputId("Введите id ассортимента: ");

        String text = assortementService.deleteAssortementById(idAssortement)
                ? "Десерт успешно удалён!"
                : "При удалении десерта, что-то пошло не так...";
        System.out.println(text);
    }

    public static void menuItem9Execute() {
        // Удалить официанта
        deletePersonalByPosition("Официант");
    }

    public static void menuItem10Execute() {
        // Удалить бариста
        deletePersonalByPosition("Бариста");
    }

    public static void menuItem11Execute() {
        // Удалить клиента

        List<Client> clients = clientService.getAllClients();
        clients.forEach(c -> System.out.println(c.getId() + " - " + c.getName() + " - " + c.getDiscount()));

        int idClient = getInputId("Введите id клиента: ");

        String text = clientService.deleteClientById(idClient)
                ? "Клиент успешно удалён!"
                : "При удалении клиента, что-то пошло не так...";
        System.out.println(text);
    }

    public static void menuItem12Execute() {
        // Вывод напитков
        List<Assortement> assortements = assortementService.getAssortementsByType("Напиток");
        assortements.forEach(System.out::println);
    }

    public static void menuItem13Execute() {
        // Вывод десертов
        List<Assortement> assortements = assortementService.getAssortementsByType("Десерт");
        assortements.forEach(System.out::println);
    }

    public static void menuItem14Execute() {
        // Вывод баристов
        List<Personal> personals = personalService.getPersonalsByPosition("Бариста");
        personals.forEach(System.out::println);
    }

    public static void menuItem15Execute() {
        // Вывод официантов
        List<Personal> personals = personalService.getPersonalsByPosition("Официант");
        personals.forEach(System.out::println);
    }

    public static void menuItem16Execute() {
        // Минимальная скидка для клиента

        float minDiscount = clientService.getClientDiscount(AggregationType.MIN);
        System.out.println("Минимальная скидка для клиента: " + minDiscount + "%");
    }

    public static void menuItem17Execute() {
        // Максимальная скидка для клиента

        float maxDiscount = clientService.getClientDiscount(AggregationType.MAX);
        System.out.println("Максимальная скидка для клиента: " + maxDiscount + "%");
    }

    public static void menuItem18Execute() {
        // Клиенты с минимальной скидкой
        showClientsByDiscount(AggregationType.MIN, "минимальной");
    }

    public static void menuItem19Execute() {
        // Клиенты с максимальной скидкой
        showClientsByDiscount(AggregationType.MAX, "максимальной");
    }

    public static void menuItem20Execute() {
        // Средняя величина скидки клиентов

        float avgDiscount = clientService.getClientDiscount(AggregationType.AVG);
        System.out.println("Средняя скидка клиентов: " + avgDiscount + "%");
    }

    public static void menuItem21Execute() {
        // Самый молодой клиент

        Client client = clientService.getClientByBirthday(AggregationType.MAX);
        String text = client == null ? "Молодого клиента не найдено..." : client.toString();
        System.out.println(text);
    }

    public static void menuItem22Execute() {
        // Самый возрастной клиент

        Client client = clientService.getClientByBirthday(AggregationType.MIN);
        String text = client == null ? "Возрастного клиента не найдено..." : client.toString();
        System.out.println(text);
    }

    public static void menuItem23Execute() {
        // Клиенты др в этот день

        List<Client> clients = clientService.getClientsByTodayBirthday();
        if (clients.isEmpty()) {
            System.out.println("Клиентов не найденно...");
        }
        else {
            clients.forEach(System.out::println);
        }
    }

    public static void menuItem24Execute() {
        // Клиенты у которых нет email

        List<Client> clients = clientService.getClientsWithoutEmail();
        if (clients.isEmpty()) {
            System.out.println("Клиентов не найденно...");
        }
        else {
            clients.forEach(System.out::println);
        }
    }

    public static void menuItem25Execute() {
        // Заказы в конкретную дату

        LocalDate orderDate = Input.inputDate("Дата заказа:");
        List<Order> orders = orderService.getOrdersByOrderDate(orderDate);
        if (orders.isEmpty()) {
            System.out.println("Заказы по дате не найденно...");
        }
        else {
            orders.forEach(System.out::println);
        }
    }

    public static void menuItem26Execute() {
        // Заказы в промежутке дат

        LocalDate fromOrderDate = Input.inputDate("От какой даты:");
        LocalDate toOrderDate = Input.inputDate("До какой даты:");

        List<Order> orders = orderService.getOrdersByBetweenOrderDate(fromOrderDate, toOrderDate);
        if (orders.isEmpty()) {
            System.out.println("Заказы в диапазоне дат не найденно...");
        }
        else {
            orders.forEach(System.out::println);
        }
    }

    public static void menuItem27Execute() {
        // Заказы кол-ва десертов по дате
        executeOrderCountByType("десерт");
    }

    public static void menuItem28Execute() {
        // Заказы кол-ва напитков по дате
        executeOrderCountByType("напиток");
    }


    public boolean execute(int choiceMenu) {
        switch (choiceMenu) {
            case 1:
                menuItem1Execute();
                break;
            case 2:
                menuItem2Execute();
                break;
            case 3:
                menuItem3Execute();
                break;
            case 4:
                menuItem4Execute();
                break;
            case 5:
                menuItem5Execute();
                break;
            case 6:
                menuItem6Execute();
                break;
            case 7:
                menuItem7Execute();
                break;
            case 8:
                menuItem8Execute();
                break;
            case 9:
                menuItem9Execute();
                break;
            case 10:
                menuItem10Execute();
                break;
            case 11:
                menuItem11Execute();
                break;
            case 12:
                menuItem12Execute();
                break;
            case 13:
                menuItem13Execute();
                break;
            case 14:
                menuItem14Execute();
                break;
            case 15:
                menuItem15Execute();
                break;
            case 16:
                menuItem16Execute();
                break;
            case 17:
                menuItem17Execute();
                break;
            case 18:
                menuItem18Execute();
                break;
            case 19:
                menuItem19Execute();
                break;
            case 20:
                menuItem20Execute();
                break;
            case 21:
                menuItem21Execute();
                break;
            case 22:
                menuItem22Execute();
                break;
            case 23:
                menuItem23Execute();
                break;
            case 24:
                menuItem24Execute();
                break;
            case 25:
                menuItem25Execute();
                break;
            case 26:
                menuItem26Execute();
                break;
            case 27:
                menuItem27Execute();
                break;
            case 28:
                menuItem28Execute();
                break;
            default:
                return false;
        }
        return true;
    }


    public static void executeOrderCountByType(String assortmentType) {
        LocalDate orderDate = Input.inputDate("Дата заказа:");

        List<Order> orders = orderService.getOrdersByOrderDate(orderDate);
        if (orders.isEmpty()) {
            System.out.println("Заказы по дате не найдены...");
            return;
        }

        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrdersId(orders);
        if (orderItems.isEmpty()) {
            System.out.println("Заказы по дате не найдены...");
            return;
        }

        List<OrderItem> orderItemsByType = orderItemService.getOrderItemsByTypeAssortement(orderItems, assortmentType);
        System.out.println("Кол-во заказов " + assortmentType + ": " + orderItemsByType.size());
    }

    public static void showClientsByDiscount(AggregationType type, String discountTypeName) {
        float discount = clientService.getClientDiscount(type);
        List<Client> clients = clientService.getClientsByDiscount((int)discount);

        if (clients.isEmpty()) {
            System.out.println("Клиенты с " + discountTypeName + " скидкой не найдены...");
        }
        else {
            System.out.println("Клиенты с " + discountTypeName + " скидкой (" + discount + "%):");
            clients.forEach(System.out::println);
        }
    }

    private static void addPersonal(String positionName) {
        // информация о персонале
        String name = Input.inputString("Введите имя: ", "Не должно быть пустым...");
        String surname = Input.inputString("Введите фамилию: ", "Не должно быть пустым...");
        String patronymic = Input.inputString("Введите отчество: ", "Не должно быть пустым...");
        String numTel = Input.inputString("Введите номер телефона: ", "Не должно быть пустым...");
        String email = Input.inputString("Введите email: ", "Не должно быть пустым...");

        // получение id позиции
        int idPosition = personalPositionService.getPersonalPositionByName(positionName).getId();

        try {
            Personal personal = new Personal(0, name, surname, patronymic, numTel, email, idPosition);
            personalService.addPersonal(personal);
            System.out.println("Персонал '" + positionName + "' успешно добавлен!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static int getInputId(String text) {
        int minId = 1;
        int maxId = 100000;
        return Input.inputInteger(
                text,
                "Id от " + minId + " до " + maxId + "...",
                minId, maxId
        );
    }

    private static Personal getPersonalByInputId() {
        int idPersonal = getInputId("Введите id кондитера: ");

        Personal personal = personalService.getPersonalById(idPersonal);
        if (personal == null) {
            System.out.println("Бариста не найден...");
            return null;
        }

        return personal;
    }

    private static void deletePersonalByPosition(String position) {
        List<Personal> personals = personalService.getPersonalsByPosition(position);
        personals.forEach(p -> System.out.println(p.getId() + " - " + p.getName() + " - " + p.getNumTel()));

        int idPersonal = getInputId("Введите id " + position.toLowerCase() + ": ");

        String text = personalService.deletePersonalById(idPersonal)
                ? position + " успешно удалён по причине увольнения!"
                : "При удалении " + position.toLowerCase() + ", что-то пошло не так...";
        System.out.println(text);
    }
}
