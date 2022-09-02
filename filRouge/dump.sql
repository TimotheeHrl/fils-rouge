CREATE DATABSE IF NOT EXISTS `fil_rouge`;
USE `filrouge`;

CREATE TABLE `roles` (
    `id` integer NOT NULL,
    `name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO roles(name) VALUES('ROLE_USER'); INSERT INTO roles(name) VALUES('ROLE_MODERATOR'); INSERT INTO roles(name) VALUES('ROLE_ADMIN');

SELECT * FROM roles;