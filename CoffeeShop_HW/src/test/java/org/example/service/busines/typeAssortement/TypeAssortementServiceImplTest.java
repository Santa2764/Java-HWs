package org.example.service.busines.typeAssortement;

import org.example.dao.typeAssortementDAO.TypeAssortementDao;
import org.example.model.TypeAssortement;
import org.example.service.CoffeeShopDbInitializer;
import org.example.utils.CreateTestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.setProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TypeAssortementServiceImplTest {
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
    void getAllTypeAssortements_ShouldReturnAllTypeAssortements_WhenCalled() {
        List<TypeAssortement> expectedList = createTestTypeAssortements();

        TypeAssortementDao typeAssortementDao = Mockito.mock(TypeAssortementDao.class);
        when(typeAssortementDao.findAll()).thenReturn(expectedList);

        TypeAssortementService typeAssortementService = new TypeAssortementServiceImpl(typeAssortementDao);
        List<TypeAssortement> actualList = typeAssortementService.getAllTypeAssortement();
        assertEquals(expectedList, actualList);
    }

    @Test
    void getTypeAssortementById_ShouldReturnTypeAssortementsById_WhenCalled() {
        List<TypeAssortement> expectedList = createTestTypeAssortements();

        TypeAssortementDao typeAssortementDao = Mockito.mock(TypeAssortementDao.class);
        when(typeAssortementDao.findAll()).thenReturn(expectedList);

        TypeAssortementService typeAssortementService = new TypeAssortementServiceImpl(typeAssortementDao);
        TypeAssortement actualTypeAssortement = typeAssortementService.getTypeAssortementById(1);
        assertEquals(expectedList.get(0), actualTypeAssortement);
    }

    @Test
    void getTypeAssortementById_ShouldThrowException_WhenTypeAssortementNotFound() {
        List<TypeAssortement> expectedList = createTestTypeAssortements();

        TypeAssortementDao typeAssortementDao = Mockito.mock(TypeAssortementDao.class);
        when(typeAssortementDao.findAll()).thenReturn(expectedList);

        TypeAssortementService typeAssortementService = new TypeAssortementServiceImpl(typeAssortementDao);

        assertThrows(IllegalArgumentException.class, () -> typeAssortementService.getTypeAssortementById(99999));
    }

    @Test
    void getTypeAssortementByName_ShouldReturnTypeAssortementsByName_WhenCalled() {
        List<TypeAssortement> expectedList = createTestTypeAssortements();

        TypeAssortementDao typeAssortementDao = Mockito.mock(TypeAssortementDao.class);
        when(typeAssortementDao.findAll()).thenReturn(expectedList);

        TypeAssortementService typeAssortementService = new TypeAssortementServiceImpl(typeAssortementDao);
        TypeAssortement actualTypeAssortement = typeAssortementService.getTypeAssortementByName("type2");
        assertEquals(expectedList.get(1), actualTypeAssortement);

        actualTypeAssortement = typeAssortementService.getTypeAssortementByName("type1");
        assertEquals(expectedList.get(0), actualTypeAssortement);

        actualTypeAssortement = typeAssortementService.getTypeAssortementByName("TYPE2");
        assertEquals(expectedList.get(1), actualTypeAssortement);
    }

    @Test
    void getTypeAssortementByName_ShouldThrowException_WhenTypeAssortementNotFound() {
        List<TypeAssortement> expectedList = createTestTypeAssortements();

        TypeAssortementDao typeAssortementDao = Mockito.mock(TypeAssortementDao.class);
        when(typeAssortementDao.findAll()).thenReturn(expectedList);

        TypeAssortementService typeAssortementService = new TypeAssortementServiceImpl(typeAssortementDao);

        assertThrows(IllegalArgumentException.class, () -> typeAssortementService.getTypeAssortementByName("Test25"));
    }
}
