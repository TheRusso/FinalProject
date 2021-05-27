CREATE TABLE country(
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(120) NOT NULL
);

INSERT INTO country(ID, NAME) VALUES (1, 'Ukraine');
INSERT INTO country(ID, NAME) VALUES (2, 'USA');
INSERT INTO country(ID, NAME) VALUES (3, 'England');
INSERT INTO country(ID, NAME) VALUES (4, 'Russia');

create table role(
     id INTEGER NOT NULL primary key,
     role VARCHAR(60) NOT NULL
);

INSERT INTO role(id, role) VALUES (1, 'admin');
INSERT INTO role(id, role) VALUES (2, 'client');

CREATE TABLE categories(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    category VARCHAR(120) NOT NULL UNIQUE
);

INSERT INTO categories(category) VALUES ('dress');
INSERT INTO categories(category) VALUES ('music');
INSERT INTO categories(category) VALUES ('other');

create table status(
    id INTEGER NOT NULL PRIMARY KEY ,
    name VARCHAR(60) NOT NULL
);

INSERT INTO status(id, name) VALUES (1, 'opened');
INSERT INTO status(id, name) VALUES (2, 'payed');
INSERT INTO status(id, name) VALUES (3, 'delivered');
INSERT INTO status(id, name) VALUES (4, 'closed');

create TABLE users(
      id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
      email VARCHAR(120) NOT NULL UNIQUE,
      pass VARCHAR(120) NOT NULL,
      first_name VARCHAR(120) NOT NULL,
      second_name VARCHAR(120) NOT NULL ,
      address VARCHAR(255) NOT NULL ,
      city VARCHAR(120) NOT NULL ,
      country_id INTEGER NOT NULL REFERENCES country(id),
      role_id int not null DEFAULT 1 REFERENCES role(id)
);

INSERT INTO users(first_name, second_name, address, city, country_id, email, pass, role_id)
VALUES ('Ruslan', 'Humeniuk', 'Pushkina 2', 'Vynnitsia', 1, 'ruslan21343@gmail.com', '123', 1);

INSERT INTO users(first_name, second_name, address, city, country_id, email, pass, role_id)
VALUES ('Vasya', 'Pupochkin', 'Vinni Puha 32', 'Vynnitsia', 3, 'Vinni@ukr.net', 'pass', 1);



CREATE TABLE items(
      id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY ,
      title VARCHAR(255) NOT NULL ,
      description TEXT,
      price FLOAT(2) NOT NULL ,
      count INTEGER NOT NULL ,
      img text NOT NULL,
      category_id INTEGER NOT NULL REFERENCES categories(id)
);

INSERT INTO items(title, description, price, count, img)
VALUES ('Milk', 'There is a cup of milk', 22.5, 20, '/img/photo1.png', 1);

INSERT INTO items(title, description, price, count, img)
VALUES ('Fish', 'There is a fish', 12.2, 100, '/img/photo2.png', 1);

INSERT INTO items(title, description, price, count, img)
VALUES ('Phone', 'There is a phone', 200, 100, '/img/photo3.png', 2);

CREATE TABLE good(
     item_id INTEGER NOT NULL REFERENCES items(id),
     user_id INTEGER NOT NULL REFERENCES users(id),
     quantity INTEGER NOT NULL
);

CREATE TABLE delivery_type(
      id INTEGER NOT NULL PRIMARY KEY,
      type VARCHAR(120) not null UNIQUE
);


INSERT INTO delivery_type(id, type) VALUES (1, 'Nova poshta');
INSERT INTO delivery_type(id, type) VALUES (2, 'Ukr poshta');
INSERT INTO delivery_type(id, type) VALUES (3, 'Meest Express');
INSERT INTO delivery_type(id, type) VALUES (4, 'Pickup');


CREATE TABLE order_list(
       id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
       address VARCHAR(255) NOT NULL ,
       city VARCHAR(120) NOT NULL ,
       country_id INTEGER NOT NULL REFERENCES country(id),
       delivery_type_id INTEGER NOT NULL REFERENCES delivery_type(id),
       user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE ,
       status_id INTEGER NOT NULL REFERENCES status(id)
);

CREATE TABLE orders(
       item_id INTEGER NOT NULL REFERENCES items(id),
       order_id INTEGER NOT NULL REFERENCES order_list(id),
       quantity INTEGER NOT NULL
);

CREATE TABLE orders_users(
        user_id INTEGER NOT NULL REFERENCES users(id),
        order_id INTEGER NOT NULL REFERENCES order_list(id)
);

create table contact_messages(
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
     name VARCHAR(120) NOT NULL ,
     email VARCHAR(120) NOT NULL ,
     message TEXT NOT NULL ,
     phone_number VARCHAR(25) NOT NULL
);
