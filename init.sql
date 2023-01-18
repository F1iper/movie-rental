CREATE DATABASE IF NOT EXISTS movierental;

USE movierental;

CREATE TABLE IF NOT EXISTS company
(
    company_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255)       NOT NULL
);


CREATE TABLE IF NOT EXISTS branch
(
    branch_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name      varchar(255)
);

CREATE TABLE IF NOT EXISTS actors
(
    actor_id   BIGINT       NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    PRIMARY KEY (actor_id)
);


CREATE TABLE IF NOT EXISTS director
(
    director_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    firstname   VARCHAR(20)        NOT NULL,
    lastname    VARCHAR(40)        NOT NULL
);

CREATE TABLE IF NOT EXISTS status
(
    status_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name      VARCHAR(255)       NOT NULL
);

CREATE TABLE IF NOT EXISTS language
(
    language_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255)       NOT NULL
);

CREATE TABLE IF NOT EXISTS movie_type
(
    movie_type_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name          VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS movies
(
    movie_id      BIGINT           NOT NULL AUTO_INCREMENT,
    title         VARCHAR(255)     NOT NULL,
    description   TEXT,
    release_year  INT              NOT NULL,
    length        INT              NOT NULL,
    language_id   BIGINT           NOT NULL,
    movie_type_id BIGINT           NOT NULL,
    cost          DOUBLE PRECISION NOT NULL,
    status_id     BIGINT           NOT NULL,
    rental_rate   DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (movie_id),
    FOREIGN KEY (language_id) REFERENCES language (language_id),
    FOREIGN KEY (movie_type_id) REFERENCES movie_type (movie_type_id),
    FOREIGN KEY (status_id) REFERENCES status (status_id)
);


CREATE TABLE IF NOT EXISTS movie_actor
(
    movie_id BIGINT NOT NULL,
    actor_id BIGINT NOT NULL,
    PRIMARY KEY (movie_id, actor_id),
    FOREIGN KEY (actor_id) REFERENCES actors (actor_id),
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id),
    UNIQUE (actor_id, movie_id)
);

CREATE TABLE IF NOT EXISTS movie_director
(
    director_id BIGINT DEFAULT NULL,
    movie_id    BIGINT DEFAULT NULL,
    FOREIGN KEY (director_id) REFERENCES director (director_id),
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id),
    UNIQUE (movie_id, director_id)
);


CREATE TABLE IF NOT EXISTS customers
(
    customer_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    active      BIT(1)             NOT NULL,
    created_at  DATETIME(6)        NOT NULL,
    email       VARCHAR(255)       NOT NULL,
    firstname   VARCHAR(255)       NOT NULL,
    lastname    VARCHAR(255)       NOT NULL
);


CREATE TABLE IF NOT EXISTS address
(
    address_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    city       VARCHAR(255)       NOT NULL,
    phone      VARCHAR(255)       NOT NULL,
    state      VARCHAR(255)       NOT NULL,
    street     VARCHAR(255)       NOT NULL,
    zip_code   VARCHAR(255)       NOT NULL
);


CREATE TABLE IF NOT EXISTS positions
(
    position_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS staff
(
    staff_id    BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    firstname   VARCHAR(255)       NOT NULL,
    lastname    VARCHAR(255)       NOT NULL,
    salary      DOUBLE             NOT NULL,
    branch_id   BIGINT             NOT NULL,
    position_id BIGINT             NOT NULL,
    FOREIGN KEY (branch_id) REFERENCES branch (branch_id),
    FOREIGN KEY (position_id) REFERENCES positions (position_id)
);

CREATE TABLE IF NOT EXISTS staff_address
(
    staff_id   BIGINT NOT NULL,
    address_id BIGINT NOT NULL,
    FOREIGN KEY (staff_id) REFERENCES staff (staff_id),
    FOREIGN KEY (address_id) REFERENCES address (address_id),
    UNIQUE (staff_id, address_id)
);

CREATE TABLE IF NOT EXISTS branch_address
(
    branch_id  BIGINT NOT NULL,
    address_id BIGINT NOT NULL,
    FOREIGN KEY (branch_id) REFERENCES branch (branch_id),
    FOREIGN KEY (address_id) REFERENCES address (address_id),
    UNIQUE (branch_id, address_id)
);

CREATE TABLE IF NOT EXISTS customer_address
(
    customer_id BIGINT NOT NULL,
    address_id  BIGINT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
    FOREIGN KEY (address_id) REFERENCES address (address_id),
    UNIQUE (customer_id, address_id)
);


CREATE TABLE IF NOT EXISTS branch_movies
(
    branch_id BIGINT NOT NULL,
    movie_id  BIGINT NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id),
    FOREIGN KEY (branch_id) REFERENCES branch (branch_id),
    UNIQUE (branch_id, movie_id)
);

CREATE TABLE IF NOT EXISTS company_branches
(
    company_id BIGINT NOT NULL,
    branch_id  BIGINT NOT NULL,
    PRIMARY KEY (company_id, branch_id),
    FOREIGN KEY (company_id) REFERENCES company (company_id),
    FOREIGN KEY (branch_id) REFERENCES branch (branch_id),
    UNIQUE (branch_id, company_id)
);

CREATE TABLE IF NOT EXISTS rental
(
    rental_id   BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    rent_date   DATETIME(6) NOT NULL,
    return_date DATETIME(6) NOT NULL,
    customer_id BIGINT      NOT NULL,
    movie_id    BIGINT      NOT NULL,
    staff_id    BIGINT      NOT NULL,
    FOREIGN KEY (staff_id) REFERENCES staff (staff_id),
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id)
);


insert into branch (name) values ("Branch 1"), ("Branch 2"), ("Branch 3");
insert into positions (name) values ("STAFF"), ("LEADER"), ("BOSS"), ("TRAINEE");
insert into staff (firstname, lastname, position_id, branch_id, salary) values("Justyna", "Kowalczyk", 1, 2, 5600), ("Kamil", "Bro",3, 1, 2600), ("Jakub", "Bro", 1, 3, 5500), ("Maria", "Tutek", 2, 1, 5000);
insert into address (city, phone, state, street, zip_code)values ("Poznan", "500 300 500", "Greater Poland", "Poznanska", "99-100"), ("Warsaw", "300 500", "Masovian", "Warszawska", "02-508");
insert into language (name) values ("ENGLISH"), ("POLISH"), ("GERMAN"), ("FRENCH"), ("ITALIAN");
insert into status (name) values ("IN REPAIR"), ("IN STOCK"), ("OUT OF STOCK");
insert into movie_type (name) values ("HORROR"), ("ANIME"), ("DOCUMENTARY"), ("ACTION"), ("DRAMA"), ("COMEDY"), ("FUR KIDS");
insert into company (name) values ("LENOVO"), ("HP"), ("ASUS"), ("HUAWEI"), ("SAMSUNG"), ("BOSCH"), ("NINTENDO");
insert into movies (title, description, release_year, length, language_id, cost, status_id, rental_rate, movie_type_id) values ('John Wick', 'Film about John', 2000, 120, 1, 19.99, 1, 0, 1), ('Jumanji', 'Film about jungle', 2015, 150, 1, 9.99, 1, 0, 2), ('Titanic', 'Ship and sea', 1990, 124, 1, 20, 1, 0, 3);
insert into address (city, phone, state, street, zip_code) values ('Warsaw', '55314315', 'Mazowieckie', 'Mokotowska', '05-155'), ('Warsaw', '64275362', 'Mazowieckie', 'Zwirki i Wigury', '02-120'), ('Krakow', '5315315513', 'Malopolskie', 'Krakowska', '30-032');
insert into language (name) values ('POLISH'), ('ENGLISH'), ('FRENCH'), ('SPANISH');
insert into status (name) values ('IN STOCK'), ('OUT OF STOCK'), ('IN REPAIR');
insert into movie_type (name) values ('DRAMA'), ('FUR KIDS'), ('ANIME'), ('HORROR'), ('COMEDY');
insert into staff_address (staff_id, address_id) values (1, 1), (2, 1), (3, 2), (3, 3);


