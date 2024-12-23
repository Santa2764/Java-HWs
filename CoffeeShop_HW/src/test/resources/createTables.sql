DROP TABLE IF EXISTS public.typeassortement CASCADE;
DROP TABLE IF EXISTS public.assortement CASCADE;
DROP TABLE IF EXISTS public.client CASCADE;
DROP TABLE IF EXISTS public.orderitem CASCADE;
DROP TABLE IF EXISTS public."Order" CASCADE;
DROP TABLE IF EXISTS public.personalposition CASCADE;
DROP TABLE IF EXISTS public.personalschedule CASCADE;
DROP TABLE IF EXISTS public.personal CASCADE;

CREATE TABLE IF NOT EXISTS public.TypeAssortement
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS public.Assortement
(
    id SERIAL PRIMARY KEY,
    nameEn VARCHAR(255) NOT NULL,
    nameRu VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    idType INT,
    FOREIGN KEY (idType) REFERENCES public.TypeAssortement(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS public.PersonalPosition
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS public.Personal
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255),
    numTel VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    idPosition INT,
    FOREIGN KEY (idPosition) REFERENCES public.PersonalPosition(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS public.Client
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255),
    birthdate DATE NOT NULL,
    numTel VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    discount INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS public."Order"
(
    id SERIAL PRIMARY KEY,
    orderDate DATE NOT NULL,
    idClient INT,
    FOREIGN KEY (idClient) REFERENCES public.Client(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS public.OrderItem
(
    id SERIAL PRIMARY KEY,
    idOrder INT,
    idAssortement INT,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (idOrder) REFERENCES public."Order"(id),
    FOREIGN KEY (idAssortement) REFERENCES public.Assortement(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS public.PersonalSchedule
(
    id SERIAL PRIMARY KEY,
    idPersonal INT NOT NULL,
    workDate DATE NOT NULL,
    startTime TIME NOT NULL,
    endTime TIME NOT NULL,
    FOREIGN KEY (idPersonal) REFERENCES public.Personal(id) ON DELETE CASCADE
    );

DELETE FROM public.typeassortement CASCADE;
DELETE FROM public.assortement CASCADE;
DELETE FROM public.client CASCADE;
DELETE FROM public.orderitem CASCADE;
DELETE FROM public."Order" CASCADE;
DELETE FROM public.personalposition CASCADE;
DELETE FROM public.personalschedule CASCADE;
DELETE FROM public.personal CASCADE;
