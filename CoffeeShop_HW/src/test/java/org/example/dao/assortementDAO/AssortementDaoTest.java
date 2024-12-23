package org.example.dao.assortementDAO;

import org.example.model.Assortement;
import org.example.service.CoffeeShopDbInitializer;
import org.example.utils.CreateTestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.setProperty;
import static org.junit.jupiter.api.Assertions.*;

public class AssortementDaoTest {
    private final AssortementDao assortementDao = new AssortementDaoImpl();

    @BeforeAll
    static void initTestDB() {
        setProperty("test", "true");
        CoffeeShopDbInitializer.createTables();
    }

    @BeforeEach
    void initData() {
        CreateTestData.insertData();
    }

    @AfterEach
    void deleteData() {
        CoffeeShopDbInitializer.createTables();
    }

    private List<Assortement> createTestAssortements() {
        List<Assortement> assortements = new ArrayList<>();

        Assortement assortement1 = new Assortement();
        assortement1.setId(1);
        assortement1.setNameEn("Chocolate Cake");
        assortement1.setNameRu("Шоколадный торт");
        assortement1.setPrice(5.99);
        assortement1.setIdTypeAssortement(1);
        assortements.add(assortement1);

        Assortement assortement2 = new Assortement();
        assortement2.setId(2);
        assortement2.setNameEn("Cheesecake");
        assortement2.setNameRu("Чизкейк");
        assortement2.setPrice(4.99);
        assortement2.setIdTypeAssortement(1);
        assortements.add(assortement2);

        Assortement assortement3 = new Assortement();
        assortement3.setId(3);
        assortement3.setNameEn("Lemonade");
        assortement3.setNameRu("Лимонад");
        assortement3.setPrice(2.50);
        assortement3.setIdTypeAssortement(2);
        assortements.add(assortement3);

        Assortement assortement4 = new Assortement();
        assortement4.setId(4);
        assortement4.setNameEn("Espresso");
        assortement4.setNameRu("Эспрессо");
        assortement4.setPrice(3.00);
        assortement4.setIdTypeAssortement(2);
        assortements.add(assortement4);

        Assortement assortement5 = new Assortement();
        assortement5.setId(5);
        assortement5.setNameEn("Fruit Salad");
        assortement5.setNameRu("Фруктовый салат");
        assortement5.setPrice(6.50);
        assortement5.setIdTypeAssortement(1);
        assortements.add(assortement5);

        return assortements;
    }

    @Test
    void findAll_ShouldReturnListOfAssortements_WhenCalled() {
        List<Assortement> expectedList = createTestAssortements();
        List<Assortement> actualList = assortementDao.findAll();
        assertEquals(expectedList, actualList);
    }

    @Test
    void save_ShouldInsertAssortementIntoTable_WhenCalled() {
        List<Assortement> expectedList = createTestAssortements();
        List<Assortement> actualList = assortementDao.findAll();
        assertEquals(expectedList, actualList);

        Assortement assortementAdd = new Assortement();
        assortementAdd.setId(6);
        assortementAdd.setNameEn("New");
        assortementAdd.setNameRu("Новый");
        assortementAdd.setPrice(3.75);
        assortementAdd.setIdTypeAssortement(1);
        assortementDao.save(assortementAdd);

        expectedList.add(assortementAdd);

        actualList.clear();
        actualList = assortementDao.findAll();

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList, actualList);
    }

    @Test
    void saveMany_ShouldInsertListOfAssortementsIntoTable_WhenCalled() {
        List<Assortement> expectedList = createTestAssortements();
        List<Assortement> actualList = assortementDao.findAll();
        assertEquals(expectedList, actualList);

        List<Assortement> addAssortements = new ArrayList<>();

        Assortement assortementAdd1 = new Assortement();
        assortementAdd1.setId(6);
        assortementAdd1.setNameEn("New");
        assortementAdd1.setNameRu("Новый");
        assortementAdd1.setPrice(3.75);
        assortementAdd1.setIdTypeAssortement(1);
        addAssortements.add(assortementAdd1);

        Assortement assortementAdd2 = new Assortement();
        assortementAdd2.setId(7);
        assortementAdd2.setNameEn("New 2");
        assortementAdd2.setNameRu("Новый 2");
        assortementAdd2.setPrice(9.85);
        assortementAdd2.setIdTypeAssortement(2);
        addAssortements.add(assortementAdd2);

        assortementDao.saveMany(addAssortements);

        expectedList.add(assortementAdd1);
        expectedList.add(assortementAdd2);

        actualList = assortementDao.findAll();

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList, actualList);
    }

    @Test
    void update_ShouldUpdateAssortement_WhenCalled() {
        List<Assortement> expectedList = createTestAssortements();
        List<Assortement> actualList = assortementDao.findAll();
        assertEquals(expectedList, actualList);

        Assortement assortement3 = new Assortement();
        assortement3.setId(3);
        assortement3.setNameEn("New 3");
        assortement3.setNameRu("Новый 3");
        assortement3.setPrice(3.3);
        assortement3.setIdTypeAssortement(1);

        assortementDao.update(assortement3);

        expectedList.remove(2);
        expectedList.add(assortement3);

        actualList = assortementDao.findAll();

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList, actualList);
    }

    @Test
    void delete_ShouldDeleteAssortement_WhenCalled() {
        List<Assortement> expectedList = createTestAssortements();
        List<Assortement> actualList = assortementDao.findAll();
        assertEquals(expectedList, actualList);

        assortementDao.delete(expectedList.get(1));
        expectedList.remove(1);

        actualList = assortementDao.findAll();

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList, actualList);
    }

    @Test
    void deleteAll_ShouldDeleteAllAssortements_WhenCalled() {
        List<Assortement> expectedList = createTestAssortements();
        List<Assortement> actualList = assortementDao.findAll();
        assertEquals(expectedList, actualList);

        assortementDao.deleteAll();

        expectedList.clear();
        actualList = assortementDao.findAll();

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList, actualList);
    }

    @Test
    void getAssortementsByType_ShouldReturnListOfAssortementsByTypeAssortementId_WhenCalled() {
        List<Assortement> expectedListType1 = createTestAssortements().stream()
                .filter(a -> a.getIdTypeAssortement() == 1)
                .toList();

        List<Assortement> expectedListType2 = createTestAssortements().stream()
                .filter(a -> a.getIdTypeAssortement() == 2)
                .toList();

        List<Assortement> actualListType1 = assortementDao.getAssortementsByType(1);
        List<Assortement> actualListType2 = assortementDao.getAssortementsByType(2);

        assertEquals(expectedListType1.size(), actualListType1.size());
        assertEquals(expectedListType2.size(), actualListType2.size());

        assertEquals(expectedListType1, actualListType1);
        assertEquals(expectedListType2, actualListType2);
    }

    @Test
    void getAssortementsById_ShouldReturnListOfAssortementsByAssortementId_WhenCalled() {
        List<Assortement> expectedList = createTestAssortements();
        Assortement expectedAssortement = expectedList.get(2);

        Assortement actualAssortement = assortementDao.getAssortementById(3);

        assertNotNull(actualAssortement);
        assertEquals(expectedAssortement, actualAssortement);
    }

    @Test
    void getAssortementsById_ShoultReturnNull_WhenAssortementNotFound() {
        Assortement actualAssortement = assortementDao.getAssortementById(999999);
        assertNull(actualAssortement);
    }
}
