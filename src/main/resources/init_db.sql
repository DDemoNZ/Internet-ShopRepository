CREATE TABLE `bucket_items` (
  `bucket_items_id` int NOT NULL AUTO_INCREMENT,
  `item_id` int NOT NULL,
  `bucket_id` int NOT NULL,
  PRIMARY KEY (`bucket_items_id`),
  KEY `bucket_item_id_fk_to_items_idx` (`item_id`),
  KEY `bucket_id_fk_to_bucket_idx` (`bucket_id`),
  CONSTRAINT `bucket_id_fk_to_bucket` FOREIGN KEY (`bucket_id`) REFERENCES `buckets` (`bucket_id`),
  CONSTRAINT `bucket_item_id_fk_to_items` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8

CREATE TABLE `bucket_items` (
  `bucket_items_id` int NOT NULL AUTO_INCREMENT,
  `item_id` int NOT NULL,
  `bucket_id` int NOT NULL,
  PRIMARY KEY (`bucket_items_id`),
  KEY `bucket_item_id_fk_to_items_idx` (`item_id`),
  KEY `bucket_id_fk_to_bucket_idx` (`bucket_id`),
  CONSTRAINT `bucket_id_fk_to_bucket` FOREIGN KEY (`bucket_id`) REFERENCES `buckets` (`bucket_id`),
  CONSTRAINT `bucket_item_id_fk_to_items` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8

CREATE TABLE `items` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` decimal(6,2) NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8

INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('H_Item1', 100.02);
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('E_Item1', 120.21);
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('L_Item1', 101.11);
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('L_Item1', 110.10);
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('O_Item1', 111.01);

CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `amount_price` decimal(6,2) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `orders_users_fk_idx` (`user_id`),
  CONSTRAINT `orders_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8

CREATE TABLE `orders_items` (
  `orders_items_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `item_id` int NOT NULL,
  PRIMARY KEY (`orders_items_id`),
  KEY `orders_items_orders_fk_idx` (`order_id`),
  KEY `orders_items_items_fk_idx` (`item_id`),
  CONSTRAINT `orders_items_items_fk` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`),
  CONSTRAINT `orders_items_orders_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8

CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8

INSERT INTO `internetshop`.`roles` (`role_name`) VALUES ('USER');
INSERT INTO `internetshop`.`items` (`role_name`) VALUES ('ADMIN');

CREATE TABLE `user_roles` (
  `user_role_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_role_id`),
  KEY `user_id_fk_to_users_idx` (`user_id`),
  KEY `role_id_fk_to_roles_idx` (`role_id`),
  CONSTRAINT `role_id_fk_to_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `user_id_fk_to_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8

CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `secondName` varchar(45) DEFAULT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8
