package org.example.service;

import org.example.dao.ConnectionFactory;
import org.example.dao.assortementDAO.AssortementDao;
import org.example.dao.assortementDAO.AssortementDaoImpl;
import org.example.dao.clientDAO.ClientDao;
import org.example.dao.clientDAO.ClientDaoImpl;
import org.example.dao.orderDAO.OrderDao;
import org.example.dao.orderDAO.OrderDaoImpl;
import org.example.dao.orderItemDAO.OrderItemDao;
import org.example.dao.orderItemDAO.OrderItemDaoImpl;
import org.example.dao.personalDAO.PersonalDao;
import org.example.dao.personalDAO.PersonalDaoImpl;
import org.example.dao.personalPositionDAO.PersonalPositionDao;
import org.example.dao.personalPositionDAO.PersonalPositionDaoImpl;
import org.example.dao.personalScheduleDAO.PersonalScheduleDao;
import org.example.dao.personalScheduleDAO.PersonalScheduleDaoImpl;
import org.example.dao.typeAssortementDAO.TypeAssortementDao;
import org.example.dao.typeAssortementDAO.TypeAssortementDaoImpl;
import org.example.exception.ConnectionDBException;
import org.example.exception.FileException;
import org.example.model.*;
import org.example.service.busines.typeAssortement.TypeAssortementService;
import org.example.service.busines.typeAssortement.TypeAssortementServiceImpl;
import org.example.utils.TestUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CoffeeShopDbInitializer {
    private CoffeeShopDbInitializer() {}

    public static void createTables() {
        try {
            Connection conn = ConnectionFactory.getInstance().makeConnection();

            TxtFileReader txtFileReaderTypes = new TxtFileReader("db.sqlScriptCreateTables");
            List<String> strings = txtFileReaderTypes.readFile();

            StringBuilder createTablesQuery = new StringBuilder();
            for (var str : strings) {
                createTablesQuery.append(str).append(" ");
            }

            try (PreparedStatement ps = conn.prepareStatement(createTablesQuery.toString())) {
                ps.execute();
                System.out.println("Create tables successfully...");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } catch (ConnectionDBException | FileException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void deleteAllRowsInDB() {
        AssortementDao assortementDao = new AssortementDaoImpl();
        ClientDao clientDao = new ClientDaoImpl();
        OrderDao orderDao = new OrderDaoImpl();
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        PersonalDao personalDao = new PersonalDaoImpl();
        PersonalPositionDao personalPositionDao = new PersonalPositionDaoImpl();
        PersonalScheduleDao personalScheduleDao = new PersonalScheduleDaoImpl();
        TypeAssortementDao typeAssortementDao = new TypeAssortementDaoImpl();

        assortementDao.deleteAll();
        clientDao.deleteAll();
        orderDao.deleteAll();
        orderItemDao.deleteAll();
        personalDao.deleteAll();
        personalPositionDao.deleteAll();
        personalScheduleDao.deleteAll();
        typeAssortementDao.deleteAll();

        System.out.println("Delete all rows successfully...");
    }

    public static void createTypeAssortements() throws FileException {
        TypeAssortementDao typeAssortementDao = new TypeAssortementDaoImpl();

        TxtFileReader txtFileReaderTypes = new TxtFileReader("data.type_assortements");
        List<String> types = txtFileReaderTypes.readFile();

        List<TypeAssortement> typeAssortements = types.stream()
                .map(typeName -> new TypeAssortement(0, typeName))
                .toList();
        typeAssortementDao.saveMany(typeAssortements);

        System.out.println("TypeAssortements created successfully...");
    }

    public static void createAssortements() throws FileException {
        TypeAssortementDao typeAssortementDao = new TypeAssortementDaoImpl();
        AssortementDao assortementDao = new AssortementDaoImpl();

        TypeAssortementService typeService = new TypeAssortementServiceImpl(typeAssortementDao);

        TxtFileReader txtFileReaderCoffeeNamesRu = new TxtFileReader("data.coffeenames_ru");
        List<String> coffeeNamesRu = txtFileReaderCoffeeNamesRu.readFile();

        TxtFileReader txtFileReaderCoffeeNamesEn = new TxtFileReader("data.coffeenames_en");
        List<String> coffeeNamesEn = txtFileReaderCoffeeNamesEn.readFile();

        TxtFileReader txtFileReaderDesertNamesRu = new TxtFileReader("data.desertnames_ru");
        List<String> desertNamesRu = txtFileReaderDesertNamesRu.readFile();

        TxtFileReader txtFileReaderDesertNamesEn = new TxtFileReader("data.desertnames_en");
        List<String> desertNamesEn = txtFileReaderDesertNamesEn.readFile();

        // id типов ассортимента
        int idCoffeeType = typeService.getTypeAssortementByName("Напиток").getId();
        int idDesertType = typeService.getTypeAssortementByName("Десерт").getId();

        List<Assortement> assortements = new ArrayList<>();
        for (int i = 0; i < coffeeNamesRu.size(); i++) {
            assortements.add(new Assortement(
                    0,
                    coffeeNamesEn.get(i),
                    coffeeNamesRu.get(i),
                    TestUtils.getRandomDouble(10, 100000),
                    idCoffeeType
            ));
        }
        for (int i = 0; i < desertNamesRu.size(); i++) {
            assortements.add(new Assortement(
                    0,
                    desertNamesEn.get(i),
                    desertNamesRu.get(i),
                    TestUtils.getRandomDouble(10, 100000),
                    idDesertType
            ));
        }
        assortementDao.saveMany(assortements);

        System.out.println("Assortements created successfully...");
    }

    public static void createRandomClients() throws FileException {
        TxtFileReader txtFileReaderNames = new TxtFileReader("data.names");
        List<String> randomNames = txtFileReaderNames.readFile();

        TxtFileReader txtFileReaderSurnames = new TxtFileReader("data.lastnames");
        List<String> randomLastNames = txtFileReaderSurnames.readFile();

        ClientDao clientDao = new ClientDaoImpl();

        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < TestUtils.getRandomInteger(8, 12); i++) {
            Client client = new Client();
            client.setName(randomNames.get(TestUtils.getRandomInteger(0, randomNames.size())));

            String lastname = randomLastNames.get(TestUtils.getRandomInteger(0, randomLastNames.size()));
            client.setSurname(lastname);

            client.setPatronymic(randomLastNames.get(TestUtils.getRandomInteger(0, randomLastNames.size())));
            client.setBirthdate(TestUtils.getRandomDate());
            client.setNumTel(TestUtils.getRandomNumTel());
            client.setEmail(TestUtils.getEmailByName(lastname));
            client.setDiscount(TestUtils.getRandomInteger(1, 15));

            clients.add(client);
        }
        clientDao.saveMany(clients);

        System.out.println("Clients created successfully...");
    }

    public static void createRandomOrders() {
        ClientDao clientDao = new ClientDaoImpl();
        OrderDao orderDao = new OrderDaoImpl();

        List<Client> clientsAll = clientDao.findAll();

        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < TestUtils.getRandomInteger(8, 10); i++) {
            LocalDate orderDate = TestUtils.getRandomDate();
            int idClient = clientsAll.get(TestUtils.getRandomInteger(0, clientsAll.size())).getId();
            orders.add(new Order(0, orderDate, idClient));
        }
        orderDao.saveMany(orders);

        System.out.println("Orders created successfully...");
    }

    public static void createRandomOrderItems() {
        OrderDao orderDao = new OrderDaoImpl();
        AssortementDao assortementDao = new AssortementDaoImpl();
        OrderItemDao orderItemDao = new OrderItemDaoImpl();

        List<Order> ordersAll = orderDao.findAll();
        List<Assortement> assortementsAll = assortementDao.findAll();

        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < TestUtils.getRandomInteger(20, 25); i++) {
            int orderId = ordersAll.get(TestUtils.getRandomInteger(0, ordersAll.size())).getId();
            int idAssortement = assortementsAll.get(TestUtils.getRandomInteger(0, assortementsAll.size())).getId();
            int quantity = TestUtils.getRandomInteger(1, 5);
            double price = TestUtils.getRandomDouble(10, 100000);
            orderItems.add(new OrderItem(0, orderId, idAssortement, quantity, price));
        }
        orderItemDao.saveMany(orderItems);

        System.out.println("OrderItems created successfully...");
    }

    public static void createPersonalPosition() throws FileException {
        PersonalPositionDao positionDao = new PersonalPositionDaoImpl();

        TxtFileReader txtFileReaderPersonalPosition = new TxtFileReader("data.personal_position");
        List<String> positions = txtFileReaderPersonalPosition.readFile();

        List<PersonalPosition> personalPositions = positions.stream()
                .map(strPosition -> new PersonalPosition(0, strPosition))
                .toList();
        positionDao.saveMany(personalPositions);

        System.out.println("PersonalPositions created successfully...");
    }

    public static void createRandomPersonal() throws FileException {
        TxtFileReader txtFileReaderNames = new TxtFileReader("data.names");
        List<String> randomNames = txtFileReaderNames.readFile();

        TxtFileReader txtFileReaderSurnames = new TxtFileReader("data.lastnames");
        List<String> randomLastNames = txtFileReaderSurnames.readFile();

        PersonalPositionDao personalPositionDao = new PersonalPositionDaoImpl();
        PersonalDao personalDao = new PersonalDaoImpl();

        List<PersonalPosition> positionsAll = personalPositionDao.findAll();

        List<Personal> personals = new ArrayList<>();
        for (int i = 0; i < TestUtils.getRandomInteger(8, 12); i++) {
            Personal personal = new Personal();
            personal.setName(randomNames.get(TestUtils.getRandomInteger(0, randomNames.size())));

            String lastname = randomLastNames.get(TestUtils.getRandomInteger(0, randomLastNames.size()));
            personal.setSurname(lastname);

            personal.setPatronymic(randomLastNames.get(TestUtils.getRandomInteger(0, randomLastNames.size())));
            personal.setNumTel(TestUtils.getRandomNumTel());
            personal.setEmail(TestUtils.getEmailByName(lastname));
            personal.setIdPosition(positionsAll.get(TestUtils.getRandomInteger(0, positionsAll.size())).getId());

            personals.add(personal);
        }
        personalDao.saveMany(personals);

        System.out.println("Personals created successfully...");
    }

    public static void createPersonalSchedule() {
        PersonalDao personalDao = new PersonalDaoImpl();
        PersonalScheduleDao personalScheduleDao = new PersonalScheduleDaoImpl();

        List<Personal> personalsAll = personalDao.findAll();

        List<PersonalSchedule> personalSchedules = new ArrayList<>();
        for (int i = 0; i < TestUtils.getRandomInteger(8, 12); i++) {
            personalSchedules.add(new PersonalSchedule(
                    0,
                    personalsAll.get(TestUtils.getRandomInteger(0, personalsAll.size())).getId(),
                    TestUtils.getRandomDate(),
                    TestUtils.getRandomTime(),
                    TestUtils.getRandomTime()
            ));
        }
        personalScheduleDao.saveMany(personalSchedules);

        System.out.println("PersonalSchedules created successfully...");
    }
}
