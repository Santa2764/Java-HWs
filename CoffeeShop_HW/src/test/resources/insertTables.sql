INSERT INTO personalposition (name)
VALUES ('Бариста'),
       ('Официант'),
       ('Кондитер');

INSERT INTO typeassortement (name)
VALUES ('type1'),
       ('type2');

INSERT INTO assortement (nameEn, nameRu, price, idType)
VALUES ('Chocolate Cake', 'Шоколадный торт', 5.99, 1),
       ('Cheesecake', 'Чизкейк', 4.99, 1),
       ('Lemonade', 'Лимонад', 2.50, 2),
       ('Espresso', 'Эспрессо', 3.00, 2),
       ( 'Fruit Salad', 'Фруктовый салат', 6.50, 1);

INSERT INTO client (name, surname, patronymic, birthdate, numTel, email, discount)
VALUES ('Ivan', 'Ivanov', 'Ivanovich', '1990-05-20', '+1234567890', 'ivan.ivanov@example.com', 10),
       ('Petr', 'Petrov', 'Petrovich', '1985-08-15', '+0987654321', 'petr.petrov@example.com', 15),
       ('Anna', 'Sidorova', 'Sergeevna', '1992-11-30', '+1122334455', 'anna.sidorova@example.com', 5),
       ('Maria', 'Smirnova', 'Viktorovna', '1988-01-22', '+9988776655', 'maria.smirnova@example.com', 20),
       ('Dmitry', 'Kuznetsov', 'Dmitrievich', '1995-03-10', '+4567891230', 'dmitry.kuznetsov@example.com', 12);

INSERT INTO "Order" (orderDate, idClient)
VALUES ('2024-09-01', 1),
       ('2024-09-02', 2),
       ('2024-09-03', 3),
       ('2024-09-04', 4),
       ('2024-09-05', 5);

INSERT INTO orderitem (idOrder, idAssortement, quantity, price)
VALUES (1, 1, 2, 5.99),
       (1, 2, 1, 3.49),
       (2, 1, 3, 5.99),
       (2, 3, 2, 4.99),
       (3, 2, 1, 3.49);

INSERT INTO personal (name, surname, patronymic, numTel, email, idPosition)
VALUES ('Иван', 'Иванов', 'Иванович', '+79160001111', 'ivan@example.com', 1),
       ('Петр', 'Петров', 'Петрович', '+79160002222', 'petr@example.com', 2),
       ('Сергей', 'Сергеев', 'Сергеевич', '+79160003333', 'sergey@example.com', 1),
       ('Анна', 'Антонова', 'Сергеевна', '+79160004444', 'anna@example.com', 3),
       ('Мария', 'Маркова', 'Ивановна', '+79160005555', 'maria@example.com', 2);

INSERT INTO personalschedule (idPersonal, workDate, startTime, endTime)
VALUES (1, '2024-09-25', '09:00', '17:00'),
       (2, '2024-09-25', '10:00', '18:00'),
       (3, '2024-09-26', '09:00', '17:00'),
       (1, '2024-09-26', '10:00', '18:00'),
       (2, '2024-09-27', '09:00', '17:00');
