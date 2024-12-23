package org.example.service.busines.assortement;

import org.example.dao.assortementDAO.AssortementDao;
import org.example.model.Assortement;
import org.example.model.TypeAssortement;
import org.example.service.CoffeeShopDbInitializer;
import org.example.service.busines.typeAssortement.TypeAssortementService;
import org.example.utils.CreateTestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.setProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AssortementServiceImplTest {
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
    void addAssortement_ShouldAddAssortementIntoTable_WhenCalled() {
        List<Assortement> expectedList = createTestAssortements();

        Assortement assortementAdd = new Assortement();
        assortementAdd.setId(6);
        assortementAdd.setNameEn("New");
        assortementAdd.setNameRu("Новый");
        assortementAdd.setPrice(3.75);
        assortementAdd.setIdTypeAssortement(1);

        expectedList.add(assortementAdd);

        AssortementDao assortementDao = Mockito.mock(AssortementDao.class);
        AssortementService assortementService = new AssortementServiceImpl(assortementDao);
        assortementService.addAssortement(assortementAdd);

        verify(assortementDao, times(1)).save(assortementAdd);
    }

    @Test
    void getAssortementsByType_ShouldReturnListOfAssortementsByTypeName_WhenCalled() {
        List<TypeAssortement> types = createTestTypeAssortements();
        TypeAssortement expectedType = types.get(0);

        List<Assortement> assortements = createTestAssortements();

        List<Assortement> expectedList = new ArrayList<>();
        expectedList.add(assortements.get(0));
        expectedList.add(assortements.get(1));
        expectedList.add(assortements.get(4));

        TypeAssortementService typeAssortementService = Mockito.mock(TypeAssortementService.class);
        AssortementDao assortementDao = Mockito.mock(AssortementDao.class);

        when(typeAssortementService.getTypeAssortementByName(expectedType.getName())).thenReturn(expectedType);
        when(assortementDao.getAssortementsByType(expectedType.getId())).thenReturn(expectedList);

        AssortementService assortementService = new AssortementServiceImpl(assortementDao);

        List<Assortement> actualAssortements = assortementService.getAssortementsByType(expectedType.getName());

        assertEquals(expectedList.size(), actualAssortements.size());
        assertEquals(expectedList, actualAssortements);

        verify(assortementDao, times(1)).getAssortementsByType(expectedType.getId());
    }

    @Test
    void getAssortementById_ShouldReturnAssortement_WhenIdExists() {
        List<Assortement> assortements = createTestAssortements();
        Assortement expectedAssortement = assortements.get(0);

        AssortementDao assortementDaoMock = Mockito.mock(AssortementDao.class);
        when(assortementDaoMock.getAssortementById(1)).thenReturn(expectedAssortement);

        AssortementService assortementService = new AssortementServiceImpl(assortementDaoMock);

        Assortement actualAssortement = assortementService.getAssortementById(1);
        assertEquals(expectedAssortement, actualAssortement);
    }

    @Test
    void deleteAssortementById_ShouldReturnFalse_WhenAssortementExist() {
        List<Assortement> expectedList = createTestAssortements();
        Assortement delAssortement = expectedList.get(1);

        AssortementDao assortementDaoMock = Mockito.mock(AssortementDao.class);
        when(assortementDaoMock.getAssortementById(delAssortement.getId())).thenReturn(delAssortement);

        AssortementService assortementService = new AssortementServiceImpl(assortementDaoMock);

        boolean res = assortementService.deleteAssortementById(delAssortement.getId());
        assertTrue(res);

        verify(assortementDaoMock, times(1)).delete(delAssortement);
    }

    @Test
    void deleteAssortementById_ShouldReturnFalse_WhenAssortementDoesNotExist() {
        AssortementDao assortementDaoMock = Mockito.mock(AssortementDao.class);
        when(assortementDaoMock.getAssortementById(999)).thenReturn(null);

        AssortementService assortementService = new AssortementServiceImpl(assortementDaoMock);

        boolean res = assortementService.deleteAssortementById(999);
        assertFalse(res);
        verify(assortementDaoMock, never()).delete(any());
    }
}
