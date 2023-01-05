CREATE
DATABASE IF NOT EXISTS movierental;
USE movierental;

CREATE TABLE IF NOT EXISTS company
(
    company_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255)       NULL DEFAULT NULL
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
    firstname   VARCHAR(20) DEFAULT NULL,
    lastname    VARCHAR(40) DEFAULT NULL
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
    created_at  DATETIME(6)  DEFAULT NULL,
    email       VARCHAR(255) DEFAULT NULL,
    firstname   VARCHAR(255) DEFAULT NULL,
    lastname    VARCHAR(255) DEFAULT NULL
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
    rent_date   DATETIME(6) DEFAULT NULL,
    return_date DATETIME(6) DEFAULT NULL,
    customer_id BIGINT      DEFAULT NULL,
    movie_id    BIGINT      DEFAULT NULL,
    staff_id    BIGINT      DEFAULT NULL,
    FOREIGN KEY (staff_id) REFERENCES staff (staff_id),
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id)
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