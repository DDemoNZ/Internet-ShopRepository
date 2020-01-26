CREATE SCHEMA `internetshop` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `internetshop`.`items` (
                                        `item_id` INT NOT NULL AUTO_INCREMENT,
                                        `name` VARCHAR(225) NOT NULL,
                                        `price` DECIMAL(6,2) NOT NULL,
                                        PRIMARY KEY (`item_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES (`item1`, `100.00`);
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES (`item2`, `200.00`);


CREATE TABLE `internetshop`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`order_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internetshop`.`orders_items` (
  `orders_items_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  PRIMARY KEY (`orders_items_id`),
  INDEX `orders_items_orders_fk_idx` (`order_id` ASC) VISIBLE,
  INDEX `orders_items_items_fk_idx` (`item_id` ASC) VISIBLE,
  CONSTRAINT `orders_items_orders_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `internetshop`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `orders_items_items_fk`
    FOREIGN KEY (`item_id`)
    REFERENCES `internetshop`.`items` (`item_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE `internetshop`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NULL,
  `secondName` VARCHAR(45) NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `token` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


ALTER TABLE `internetshop`.`orders`
ADD COLUMN `user_id` INT NOT NULL AFTER `order_id`,
ADD INDEX `orders_users_fk_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `internetshop`.`orders`
ADD CONSTRAINT `orders_users_fk`
  FOREIGN KEY (`user_id`)
  REFERENCES `internetshop`.`users` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `internetshop`.`orders`
ADD COLUMN `amount_price` DECIMAL(6,2) NULL AFTER `order_id`;


CREATE TABLE `internetshop`.`user_roles` (
  `user_role_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_role_id`));
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `internetshop`.`user_roles`
ADD INDEX `user_id_fk_to_users_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `internetshop`.`user_roles`
ADD CONSTRAINT `user_id_fk_to_users`
  FOREIGN KEY (`user_id`)
  REFERENCES `internetshop`.`users` (`user_id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;

  ALTER TABLE `internetshop`.`user_roles`
ADD INDEX `role_id_fk_to_roles_idx` (`role_id` ASC) VISIBLE;
;
ALTER TABLE `internetshop`.`user_roles`
ADD CONSTRAINT `role_id_fk_to_roles`
  FOREIGN KEY (`role_id`)
  REFERENCES `internetshop`.`roles` (`role_id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;



CREATE TABLE `internetshop`.`roles` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internetshop`.`buckets` (
  `bucket_id` INT NOT NULL AUTO_INCREMENT,
  `bucket_items_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`bucket_id`),
  INDEX `bucket_user_id_fk_to_users_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `bucket_user_id_fk_to_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `internetshop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



#Registration
INSERT INTO `internetshop`.`users` (`firstName`, `secondName`, `login`, `password`)
VALUES ('Admin', 'Adminovich', 'admin', 'admin');

INSERT INTO `internetshop`.`users` (`firstName`, `secondName`, `login`, `password`)
VALUES ('User', 'Userovich', 'user', 'user');

#Complite order
INSERT INTO `internetshop`.`orders` (`user_id`) VALUES ('1');
INSERT INTO `internetshop`.`orders` (`user_id`) VALUES ('1');
INSERT INTO `internetshop`.`orders` (`user_id`) VALUES ('2');

#Add item to db item_list
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('H_Item1', '100.01');
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('E_Item2', '101.01');
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('L_Item3', '100.11');
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('L_Item4', '110.10');
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('O_Item5', '111.11');

#Its must be done with complete order statement
INSERT INTO `internetshop`.`orders_items` (`order_id`, `item_id`) VALUES ('1', '1');
INSERT INTO `internetshop`.`orders_items` (`order_id`, `item_id`) VALUES ('1', '2');
INSERT INTO `internetshop`.`orders_items` (`order_id`, `item_id`) VALUES ('2', '4');
INSERT INTO `internetshop`.`orders_items` (`order_id`, `item_id`) VALUES ('3', '2');
INSERT INTO `internetshop`.`orders_items` (`order_id`, `item_id`) VALUES ('3', '5');


