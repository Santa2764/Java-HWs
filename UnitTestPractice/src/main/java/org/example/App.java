package org.example;

import org.example.entity.Product;
import org.example.utils.FileWork;
import org.example.utils.ListWork;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class App
{
    private static void writeProductsToFile(Path pathCSVFile, List<Product> products) {
        try {
            FileWork.clearFile(pathCSVFile);
            for (Product prod : products) {
                FileWork.appendStrToFile(pathCSVFile, prod.convertToCsv());
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main( String[] args )
    {
        Path pathCSVFile = Path.of(".", "orders", "order_1.csv");
        Path pathCSVFile2 = Path.of(".", "orders", "order_2.csv");
        Path atbPathCSVFile = Path.of(".", "orders", "atb_res.csv");
        Path silpoPathCSVFile = Path.of(".", "orders", "silpo_res.csv");

        File csvFile = pathCSVFile.toFile();
        File csvFile2 = pathCSVFile2.toFile();
        File atbCsvFile = atbPathCSVFile.toFile();
        File silpoCsvFile = silpoPathCSVFile.toFile();

        List<String> stringsFromFile1 = null;
        List<String> stringsFromFile2 = null;

        // проверяем на сущ. файлы
        if (!FileWork.checkFileExist(atbCsvFile) || !FileWork.checkFileExist(silpoCsvFile)) {
            return;
        }

        // считываем содержимое из файлов
        if (FileWork.checkFileExist(csvFile)) {
            stringsFromFile1 = FileWork.getStringsFromFile(csvFile);
        }
        else {
            System.out.println("Файл не существует!!!");
        }

        if (FileWork.checkFileExist(csvFile2)) {
            stringsFromFile2 = FileWork.getStringsFromFile(csvFile2);
        }
        else {
            System.out.println("Файл не существует!!!");
        }

        // получаем чистые данные (без первой строки)
        if (stringsFromFile1 == null || stringsFromFile2 == null) {
            System.out.println("При считывании данных из csv-файлов, что-то пошло не так...");
            return;
        }
        stringsFromFile1 = ListWork.changeListWithSkip(stringsFromFile1, 1);
        stringsFromFile2 = ListWork.changeListWithSkip(stringsFromFile2, 1);

        // получаем коллекцию продуктов из двух файлов
        List<Product> products = ListWork.getListProductsByListCsv(stringsFromFile1);
        products.addAll(ListWork.getListProductsByListCsv(stringsFromFile2));

        // получаем две коллекции по разным магазинам
        List<Product> atbProducts = ListWork.getFilteredList(
                products, p -> p.getTitleShop().equalsIgnoreCase("атб")
        );
        List<Product> silpoProducts = ListWork.getFilteredList(
                products, p -> p.getTitleShop().equalsIgnoreCase("сильпо")
        );

        // слияние товаров, общее кол-во, и средняя цена товара
        atbProducts = ListWork.mergeIdenticalProductsWithSort(atbProducts);
        silpoProducts = ListWork.mergeIdenticalProductsWithSort(silpoProducts);

        // записываем в файлы csv продукты из разного магазина
        writeProductsToFile(atbPathCSVFile, atbProducts);
        writeProductsToFile(silpoPathCSVFile, silpoProducts);

        System.out.println("END!!!");
    }
}
