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

INSERT INTO categories(category) VALUES ('clothes');
INSERT INTO categories(category) VALUES ('music');
INSERT INTO categories(category) VALUES ('other');

create table status(
    id INTEGER NOT NULL PRIMARY KEY ,
    name VARCHAR(60) NOT NULL
);

INSERT INTO status(id, name) VALUES (1, 'registered');
INSERT INTO status(id, name) VALUES (2, 'paid');
INSERT INTO status(id, name) VALUES (3, 'canceled');

create TABLE users(
      id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
      email VARCHAR(120) NOT NULL UNIQUE,
      pass VARCHAR(120) NOT NULL,
      first_name VARCHAR(120) NOT NULL,
      second_name VARCHAR(120) NOT NULL ,
      address VARCHAR(255) NOT NULL ,
      city VARCHAR(120) NOT NULL ,
      country_id INTEGER NOT NULL REFERENCES country(id),
      role_id int not null DEFAULT 1 REFERENCES role(id),
      banned integer not null DEFAULT 0
);

INSERT INTO users(first_name, second_name, address, city, country_id, email, pass, role_id)
VALUES ('Ruslan', 'Humeniuk', 'Pushkina 2', 'Vynnitsia', 1, 'ruslan21343@gmail.com', '123', 0);

INSERT INTO users(first_name, second_name, address, city, country_id, email, pass, role_id)
VALUES ('Vasya', 'Pupochkin', 'Vinni Puha 32', 'Vynnitsia', 3, 'Vinni@ukr.net', 'pass', 1);



CREATE TABLE items(
      id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY ,
      title VARCHAR(255) NOT NULL ,
      description TEXT,
      price FLOAT(2) NOT NULL ,
      img text NOT NULL,
      category_id INTEGER NOT NULL REFERENCES categories(id),
      disable INTEGER NOT NULL DEFAULT 0
);

INSERT INTO items(title, description, price, img, category_id)
VALUES ('Motanka T-Shirt', 'Very cool motanka t-shirt', 22.5, '/items/img/ref.jpg', 1);

INSERT INTO items(title, description, price, img, category_id)
VALUES ('SOLSTAFIR', 'ENDLESS TWILIGHT OF CODEPENDENT LOVE - CLEAR BLACK MARBLED 2-VINYL', 16.99, '/items/img/asd.jpg', 2);

INSERT INTO items(title, description, price, img, category_id)
VALUES ('POWERWOLF', 'CALL OF THE WILD - MEDIABOOK 2-CD', 24.99, '/items/img/pawerwolfcd.png', 2);

INSERT INTO items(title, description, price, img, category_id)
VALUES ('APOPHIS', 'EXCESS - SCHWARZES VINYL', 23.99, '/items/img/apophisvinyl.png', 2);

INSERT INTO items(title, description, price, img, category_id)
VALUES ('TOOL', 'THE TORCH - T-SHIRT', 19.99, '/items/img/tool.jpg', 1);

INSERT INTO items(title, description, price, img, category_id)
VALUES ('PINK FLOYD', 'DSOTM ESCHER - T-SHIRT', 19.99, '/items/img/pinkfloyd.jpg', 1);

INSERT INTO items(title, description, price, img, category_id)
VALUES ('Bon Jovi vinyl', 'Bon Jovi - Bon Jovi 2020 - Vinyl LP', 34.99, '/items/img/bon.jpg', 2);

INSERT INTO items(title, description, price, img, category_id)
VALUES ('Lynyrd Skynyrd', 'Lynyrd Skynyrd - (Pronounced ''Leh-''Nerd ''skin-''Nerd) - Vinyl LP
- Track List: 1. I Ain''t the One 2. Tuesday''s Gone 3.
Gimme Three Steps 4. Simple Man 5. Things Goin'' on 6.
Mississippi Kid 7. Poison Whiskey 8. Free Bird', 28.99, '/items/img/lynyrd.jpg', 2);

INSERT INTO items(title, description, price, img, category_id)
VALUES ('Nirvana poster', 'Poster', 8.99, '/items/img/nirvana.jpg', 3);

INSERT INTO items(title, description, price, img, category_id)
VALUES ('Jimi Hendrix poster', 'Poster', 8.99, '/items/img/jimi.png', 3);



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

create table contact_messages(
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
     name VARCHAR(120) NOT NULL ,
     email VARCHAR(120) NOT NULL ,
     message TEXT NOT NULL ,
     phone_number VARCHAR(25) NOT NULL
);
