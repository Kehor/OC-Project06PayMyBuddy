CREATE DATABASE IF NOT EXISTS PayMyBuddy;
use PayMyBuddy;
CREATE TABLE `user` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `email` varchar(255) UNIQUE,
  `password` varchar(255),
  `created_at` datetime DEFAULT (now()),
  `iban` varchar(255),
  `balance` decimal(8,2),
  `role_id` int
);

CREATE TABLE `role` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `role_name` varchar(65)
);

CREATE TABLE `friends` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `friend_id` int
);

CREATE TABLE `transactions` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `sender_id` int,
  `receiver_id` int,
  `description` varchar(255),
  `amount` decimal(8,2),
  `created_at` datetime DEFAULT (now())
);

CREATE TABLE `bank` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `description` varchar(255),
  `amount` decimal(8,2),
  `created_at` datetime DEFAULT (now())
);

ALTER TABLE `friends` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `friends` ADD FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`);

ALTER TABLE `transactions` ADD FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`);

ALTER TABLE `transactions` ADD FOREIGN KEY (`receiver_id`) REFERENCES `user` (`id`);

ALTER TABLE `bank` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

INSERT INTO role (`id`, `role_name`) VALUES (1,"ROLE_ADMIN"),(2,"ROLE_USER");

INSERT INTO user (`id`, `name`, `email`, `password`, `created_at`, `iban`, `balance`, `role_id`) VALUE (1, "admin", "admin@gmail.com", "$2a$10$VeGxUxWKKBl2Nku96vnbx.psHC9/88a.8gQx.1No.KhY44FBlMDxu", "2021-01-01 01:01:42", "FR00", "999", 1);