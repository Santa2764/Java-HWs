package com.example.CoffeeShop_Spring.service;

import com.example.CoffeeShop_Spring.dao.assortementDAO.AssortementDao;
import com.example.CoffeeShop_Spring.dao.clientDAO.ClientDao;
import com.example.CoffeeShop_Spring.dao.orderDAO.OrderDao;
import com.example.CoffeeShop_Spring.dao.orderItemDAO.OrderItemDao;
import com.example.CoffeeShop_Spring.dao.personalDAO.PersonalDao;
import com.example.CoffeeShop_Spring.dao.personalPositionDAO.PersonalPositionDao;
import com.example.CoffeeShop_Spring.dao.personalScheduleDAO.PersonalScheduleDao;
import com.example.CoffeeShop_Spring.dao.typeAssortementDAO.TypeAssortementDao;
import com.example.CoffeeShop_Spring.model.*;
import com.example.CoffeeShop_Spring.service.busines.typeAssortement.TypeAssortementService;
import com.example.CoffeeShop_Spring.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeShopDbInitializer {

    @Value("${db.sqlScriptCreateTables}")
    private String sqlScriptCreateTables;

    @Value("${data.type_assortements}")
    private String dataTypeAssortements;

    @Value("${data.coffeenames_ru}")
    private String dataCoffeeNamesRu;

    @Value("${data.coffeenames_en}")
    private String dataCoffeeNamesEn;

    @Value("${data.desertnames_ru}")
    private String dataDesertNamesRu;

    @Value("${data.desertnames_en}")
    private String dataDesertNamesEn;

    @Value("${data.names}")
    private String dataNames;

    @Value("${data.lastnames}")
    private String dataLastnames;

    @Value("${data.personal_position}")
    private String dataPersonalPositions;


    @Autowired
    private AssortementDao assortementDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private PersonalDao personalDao;

    @Autowired
    private PersonalPositionDao personalPositionDao;

    @Autowired
    private PersonalScheduleDao personalScheduleDao;

    @Autowired
    private TypeAssortementDao typeAssortementDao;

    @Autowired
    private TypeAssortementService typeAssortementService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    TextFileReader textFileReader;


    private CoffeeShopDbInitializer() {}


    public void createTables() {
        List<String> strings;
        try {
            strings = Files.readAllLines(Paths.get(sqlScriptCreateTables));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StringBuilder createTablesQuery = new StringBuilder();

        for (var currentString : strings) {
            createTablesQuery.append(currentString);
            createTablesQuery.append("\n");
        }

        String sqlStr = createTablesQuery.toString();
        jdbcTemplate.execute(sqlStr);

        System.out.println("Create tables successfully...");
    }

    public void deleteAllRowsInDB() {
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

    public void createTypeAssortements() {
        List<String> types = textFileReader.readTextFile(dataTypeAssortements);

        List<TypeAssortement> typeAssortements = types.stream()
                .map(typeName -> new TypeAssortement(0, typeName))
                .toList();
        typeAssortementDao.saveMany(typeAssortements);

        System.out.println("TypeAssortements created successfully...");
    }

    public void createAssortements() {
        List<String> coffeeNamesRu = textFileReader.readTextFile(dataCoffeeNamesRu);
        List<String> coffeeNamesEn = textFileReader.readTextFile(dataCoffeeNamesEn);
        List<String> desertNamesRu = textFileReader.readTextFile(dataDesertNamesRu);
        List<String> desertNamesEn = textFileReader.readTextFile(dataDesertNamesEn);

        // id типов ассортимента
        int idCoffeeType = typeAssortementService.getTypeAssortementByName("Напиток").getId();
        int idDesertType = typeAssortementService.getTypeAssortementByName("Десерт").getId();

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

    public void createRandomClients() {
        List<String> names = textFileReader.readTextFile(dataNames);
        List<String> lastNames = textFileReader.readTextFile(dataLastnames);

        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < TestUtils.getRandomInteger(8, 12); i++) {
            Client client = new Client();
            client.setName(names.get(TestUtils.getRandomInteger(0, names.size())));

            String lastname = lastNames.get(TestUtils.getRandomInteger(0, lastNames.size()));
            client.setSurname(lastname);

            client.setPatronymic(lastNames.get(TestUtils.getRandomInteger(0, lastNames.size())));
            client.setBirthdate(TestUtils.getRandomDate());
            client.setNumTel(TestUtils.getRandomNumTel());
            client.setEmail(TestUtils.getEmailByName(lastname));
            client.setDiscount(TestUtils.getRandomInteger(1, 15));

            clients.add(client);
        }
        clientDao.saveMany(clients);

        System.out.println("Clients created successfully...");
    }

    public void createRandomOrders() {
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

    public void createRandomOrderItems() {
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

    public void createPersonalPosition() {
        List<String> positions = textFileReader.readTextFile(dataPersonalPositions);

        List<PersonalPosition> personalPositions = positions.stream()
                .map(strPosition -> new PersonalPosition(0, strPosition))
                .toList();
        personalPositionDao.saveMany(personalPositions);

        System.out.println("PersonalPositions created successfully...");
    }

    public void createRandomPersonal() {
        List<String> names = textFileReader.readTextFile(dataNames);
        List<String> lastNames = textFileReader.readTextFile(dataLastnames);

        List<PersonalPosition> positionsAll = personalPositionDao.findAll();

        List<Personal> personals = new ArrayList<>();
        for (int i = 0; i < TestUtils.getRandomInteger(8, 12); i++) {
            Personal personal = new Personal();
            personal.setName(names.get(TestUtils.getRandomInteger(0, names.size())));

            String lastname = lastNames.get(TestUtils.getRandomInteger(0, lastNames.size()));
            personal.setSurname(lastname);

            personal.setPatronymic(lastNames.get(TestUtils.getRandomInteger(0, lastNames.size())));
            personal.setNumTel(TestUtils.getRandomNumTel());
            personal.setEmail(TestUtils.getEmailByName(lastname));
            personal.setIdPosition(positionsAll.get(TestUtils.getRandomInteger(0, positionsAll.size())).getId());

            personals.add(personal);
        }
        personalDao.saveMany(personals);

        System.out.println("Personals created successfully...");
    }

    public void createPersonalSchedule() {
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
