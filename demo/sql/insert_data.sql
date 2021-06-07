INSERT INTO country(ID, NAME) VALUES (1, 'Ukraine');
INSERT INTO country(ID, NAME) VALUES (2, 'USA');
INSERT INTO country(ID, NAME) VALUES (3, 'England');
INSERT INTO country(ID, NAME) VALUES (4, 'Russia');

INSERT INTO role(id, role) VALUES (0, 'admin');
INSERT INTO role(id, role) VALUES (1, 'client');

INSERT INTO categories(category) VALUES ('clothes');
INSERT INTO categories(category) VALUES ('music');
INSERT INTO categories(category) VALUES ('other');

INSERT INTO status(id, name) VALUES (1, 'registered');
INSERT INTO status(id, name) VALUES (2, 'paid');
INSERT INTO status(id, name) VALUES (3, 'canceled');

INSERT INTO users(first_name, second_name, address, city, country_id, email, pass, role_id)
VALUES ('Ruslan', 'Humeniuk', 'Pushkina 2', 'Vynnitsia', 1, 'ruslan21343@gmail.com', '123', 0);

INSERT INTO users(first_name, second_name, address, city, country_id, email, pass, role_id)
VALUES ('Vasya', 'Pupochkin', 'Vinni Puha 32', 'Vynnitsia', 3, 'Vinni@ukr.net', 'pass', 1);

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

INSERT INTO delivery_type(id, type) VALUES (1, 'Nova poshta');
INSERT INTO delivery_type(id, type) VALUES (2, 'Ukr poshta');
INSERT INTO delivery_type(id, type) VALUES (3, 'Meest Express');
INSERT INTO delivery_type(id, type) VALUES (4, 'Pickup');

INSERT INTO order_list(user_id, address, city, country_id, delivery_type_id, status_id)
        VALUES(1, 'Vinni puha 2', 'Kyiv', 1, 1, 2);

INSERT INTO orders(item_id, order_id, quantity)
        VALUES(1, 1, 4);

INSERT INTO orders(item_id, order_id, quantity)
        VALUES (2, 1, 4);

INSERT INTO order_list(user_id, address, city, country_id, delivery_type_id, status_id)
        VALUES(1, 'Vinni puha 2', 'Kyiv', 1, 1, 1);

INSERT INTO orders(item_id, order_id, quantity)
    VALUES(3, 1, 8);

INSERT INTO orders(item_id, order_id, quantity)
    VALUES (1, 1, 4);

INSERT INTO order_list(user_id, address, city, country_id, delivery_type_id, status_id)
    VALUES(1, 'Wall street', 'NY', 2, 1, 1);

INSERT INTO orders(item_id, order_id, quantity)
    VALUES(3, 2, 1);

INSERT INTO orders(item_id, order_id, quantity)
    VALUES (1, 2, 4);