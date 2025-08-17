
DROP DATABASE IF EXISTS `printable_db`;


CREATE DATABASE `printable_db`
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;


USE `printable_db`;





CREATE TABLE `Language` (
  `language_id` CHAR(5) PRIMARY KEY -- ex: fr, en, fr-BE
) ;


CREATE TABLE `Category` (
  `id` INT AUTO_INCREMENT PRIMARY KEY
) ;

CREATE TABLE `Discount` (
  `code` VARCHAR(50) PRIMARY KEY,
  `discount` DECIMAL(5,2) NOT NULL CHECK (`discount` BETWEEN 0 AND 100),
  `is_valid` BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE `User` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL UNIQUE,
  `address` TEXT,
  `authorities` VARCHAR(255),
  `non_expired` BOOLEAN NOT NULL DEFAULT TRUE,
  `non_locked` BOOLEAN NOT NULL DEFAULT TRUE,
  `credentials_non_expired` BOOLEAN NOT NULL DEFAULT TRUE,
  `enabled` BOOLEAN NOT NULL DEFAULT TRUE,
  CHECK (CHAR_LENGTH(`username`) >= 3),
  CHECK (`email` REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$')
) ;



CREATE TABLE `Product` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL CHECK (`price` > 0), -- plus grand que 0
  `description` TEXT,
  `image` LONGBLOB NULL,
  `category_id` INT,
  INDEX (`category_id`),
  CONSTRAINT `fk_product_category`
    FOREIGN KEY (`category_id`) REFERENCES `Category`(`id`)
    ON UPDATE CASCADE
    ON DELETE SET NULL -- Utile pour gardé les produits et et les comandes 
);


CREATE TABLE `Order` (
  `order_id` INT AUTO_INCREMENT PRIMARY KEY,
  `user_id` INT ,
  `is_paid` BOOLEAN NOT NULL DEFAULT FALSE,
  INDEX (`user_id`),
  CONSTRAINT `fk_order_user`
    FOREIGN KEY (`user_id`) REFERENCES `User`(`id`)
    ON UPDATE CASCADE
    ON DELETE SET NULL 
) ;


CREATE TABLE `Order_line` (
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL CHECK (`quantity` > 0),
  `price` DECIMAL(10,2) NOT NULL CHECK (`price` > 0),
  PRIMARY KEY (`order_id`, `product_id`),
  INDEX (`product_id`),
  CONSTRAINT `fk_orderline_order`
    FOREIGN KEY (`order_id`) REFERENCES `Order`(`order_id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT `fk_orderline_product`
    FOREIGN KEY (`product_id`) REFERENCES `Product`(`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT -- non supprimable si utilisé 
) ;


CREATE TABLE `Translation` (
  `language_id` char(5) NOT NULL,
  `category_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`language_id`, `category_id`),
  INDEX (`category_id`),
  CONSTRAINT `fk_translation_language`
    FOREIGN KEY (`language_id`) REFERENCES `Language`(`language_id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT `fk_translation_category`
    FOREIGN KEY (`category_id`) REFERENCES `Category`(`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE
) ;

SET FOREIGN_KEY_CHECKS = 1; 
