CREATE TABLE IF NOT EXISTS `actor`
(
    `actor_id`  BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `firstname` VARCHAR(40)       NULL DEFAULT NULL,
    `lastname`  VARCHAR(50)       NULL DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `address`
(
    `address_id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `city`       VARCHAR(40)       NULL DEFAULT NULL,
    `phone`      VARCHAR(20)       NULL DEFAULT NULL,
    `state`      VARCHAR(30)       NULL DEFAULT NULL,
    `street`     VARCHAR(40)       NULL DEFAULT NULL,
    `zip_code`   VARCHAR(10)       NULL DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `company`
(
    `company_id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(30)       NULL DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `branch`
(
    `branch_id`  BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `address_id` BIGINT             NULL DEFAULT NULL,
    `company_id` BIGINT             NULL DEFAULT NULL,
    FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`),
    FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
);

CREATE TABLE IF NOT EXISTS `movie`
(
    `movie_id`     BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `category`     SMALLINT           NULL DEFAULT NULL,
    `rent_cost`    DOUBLE             NOT NULL,
    `description`  VARCHAR(255)       NULL DEFAULT NULL,
    `language`     SMALLINT           NULL DEFAULT NULL,
    `length`       INT                NOT NULL,
    `release_year` INT                NOT NULL,
    `rental_rate`  DOUBLE             NOT NULL,
    `status`       SMALLINT           NULL DEFAULT NULL,
    `title`        VARCHAR(50)       NULL DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `branch_movies`
(
    `branch_id` BIGINT NOT NULL,
    `movie_id`  BIGINT NOT NULL,
    FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`),
    FOREIGN KEY (`branch_id`) RóEFERENCES `branch` (`branch_id`),
    UNIQUE (`branch_id`, `movie_id`)
);

CREATE TABLE IF NOT EXISTS `company_branches`
(
    `company_id` BIGINT NOT NULL,
    `branch_id`  BIGINT NOT NULL,
    PRIMARY KEY (`company_id`, `branch_id`),
    FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`),
    FOREIGN KEY (`branch_id`) REFERENCES `branch` (`branch_id`),
    UNIQUE (`branch_id`, `company_id`)
);

CREATE TABLE IF NOT EXISTS `customer`
(
    `customer_id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `active`      BIT(1)             NOT NULL,
    `created_at`  DATETIME(6)        NULL DEFAULT NULL,
    `email`       VARCHAR(50)       NULL DEFAULT NULL,
    `firstname`   VARCHAR(40)       NULL DEFAULT NULL,
    `lastname`    VARCHAR(50)       NULL DEFAULT NULL,
    `address_id`  BIGINT             NULL DEFAULT NULL,
    FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
);

CREATE TABLE IF NOT EXISTS `movie_actor`
(
    `movie_id` BIGINT NOT NULL,
    `actor_id` BIGINT NOT NULL,
    PRIMARY KEY (`movie_id`, `actor_id`),
    FOREIGN KEY (`actor_id`) REFERENCES `actor` (`actor_id`),
    FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`),
    UNIQUE (`actor_id`, `movie_id`)
);

CREATE TABLE IF NOT EXISTS `staff`
(
    `staff_id`   BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `firstname`  VARCHAR(20)       NULL DEFAULT NULL,
    `lastname`   VARCHAR(50)       NULL DEFAULT NULL,
    `position`   SMALLINT           NULL DEFAULT NULL,
    `salary`     DOUBLE             NOT NULL,
    `address_id` BIGINT             NULL DEFAULT NULL,
    `branch_id`  BIGINT             NULL DEFAULT NULL,
    FOREIGN KEY (`branch_id`) REFERENCES `branch` (`branch_id`),
    FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
);

CREATE TABLE IF NOT EXISTS `rental`
(
    `rental_id`   BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `rent_date`   DATETIME(6)        NULL DEFAULT NULL,
    `return_date` DATETIME(6)        NULL DEFAULT NULL,
    `customer_id` BIGINT             NULL DEFAULT NULL,
    `movie_id`    BIGINT             NULL DEFAULT NULL,
    `staff_id`    BIGINT             NULL DEFAULT NULL,
    FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`),
    FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
    FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`)
);
