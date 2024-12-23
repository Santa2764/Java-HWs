package org.example.dao.typeAssortementDAO;

import org.example.model.TypeAssortement;
import org.example.service.CoffeeShopDbInitializer;
import org.example.utils.CreateTestData;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.setProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeAssortementDaoTest {
    private final TypeAssortementDao typeAssortementDao = new TypeAssortementDaoImpl();

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

    private List<TypeAssortement> createTestTypeAssortements() {
        List<TypeAssortement> list = new ArrayList<>();

        TypeAssortement type1 = new TypeAssortement();
        type1.setId(1);
        type1.setName("type1");
        list.add(type1);

        TypeAssortement type2 = new TypeAssortement();
        type2.setId(2);
        type2.setName("type2");
        list.add(type2);

        return list;
    }

    @Test
    void save_ShouldInsertTypeAssortementIntoTable_WhenCalled() {
        TypeAssortement typeAssortement = new TypeAssortement();
        typeAssortement.setId(3);
        typeAssortement.setName("type3");

        typeAssortementDao.save(typeAssortement);

        List<TypeAssortement> allTypes = typeAssortementDao.findAll();

        int expected = 3;
        int actual = allTypes.size();
        assertEquals(expected, actual);

        List<TypeAssortement> expectedList = createTestTypeAssortements();

        expectedList.add(typeAssortement);  // 3

        assertEquals(expectedList, allTypes);
    }

    @Test
    void saveMany_ShouldInsertListOfTypeAssortementsIntoTable_WhenCalled() {
        List<TypeAssortement> typeAssortements = new ArrayList<>();

        TypeAssortement type1 = new TypeAssortement();
        type1.setId(3);
        type1.setName("type3");
        typeAssortements.add(type1);

        TypeAssortement type2 = new TypeAssortement();
        type2.setId(4);
        type2.setName("type4");
        typeAssortements.add(type2);

        typeAssortementDao.saveMany(typeAssortements);

        List<TypeAssortement> allTypes = typeAssortementDao.findAll();

        int expected = 4;
        int actual = allTypes.size();
        assertEquals(expected, actual);

        List<TypeAssortement> expectedList = createTestTypeAssortements();

        TypeAssortement addType3 = new TypeAssortement();
        addType3.setId(3);
        addType3.setName("type3");
        expectedList.add(addType3);

        TypeAssortement addType4 = new TypeAssortement();
        addType4.setId(4);
        addType4.setName("type4");
        expectedList.add(addType4);

        assertEquals(expectedList, allTypes);
    }

    @Test
    void findAll_ShouldReturnListOfTypeAssortements_WhenCalled() {
        List<TypeAssortement> expectedList = createTestTypeAssortements();
        List<TypeAssortement> actualList = typeAssortementDao.findAll();
        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList, actualList);
    }

    @Test
    void update_ShouldUpdateTypeAssortement_WhenCalled() {
        List<TypeAssortement> actualList = typeAssortementDao.findAll();
        List<TypeAssortement> expectedList = createTestTypeAssortements();

        assertEquals(expectedList, actualList);

        TypeAssortement updateType = new TypeAssortement();
        updateType.setId(2);
        updateType.setName("type22");

        typeAssortementDao.update(updateType);

        actualList.clear();
        actualList = typeAssortementDao.findAll();

        TypeAssortement type1 = expectedList.get(0);

        expectedList.clear();
        expectedList.add(type1);
        expectedList.add(updateType);

        assertEquals(expectedList, actualList);
    }

    @Test
    void delete_ShouldDeleteTypeAssortement_WhenCalled() {
        List<TypeAssortement> actualList = typeAssortementDao.findAll();
        List<TypeAssortement> expectedList = createTestTypeAssortements();

        assertEquals(expectedList, actualList);

        typeAssortementDao.delete(expectedList.get(0));

        actualList.clear();
        actualList = typeAssortementDao.findAll();

        TypeAssortement type2 = expectedList.get(1);

        expectedList.clear();
        expectedList.add(type2);

        assertEquals(expectedList, actualList);
    }

    @Test
    void deleteAll_ShouldDeleteAllTypeAssortements_WhenCalled() {
        List<TypeAssortement> actualList = typeAssortementDao.findAll();
        List<TypeAssortement> expectedList = createTestTypeAssortements();

        assertEquals(expectedList, actualList);

        typeAssortementDao.deleteAll();

        actualList.clear();
        actualList = typeAssortementDao.findAll();

        expectedList.clear();

        assertEquals(actualList.size(), 0);
        assertEquals(expectedList, actualList);
    }

    @Test
    void save_ShouldReturnException_WhenCalled() {
        CreateTestData.dropTables();

        TypeAssortement type3 = new TypeAssortement();
        type3.setId(3);
        type3.setName("type3");

        Assertions.assertThrows(RuntimeException.class, () -> typeAssortementDao.save(type3));
    }
}
