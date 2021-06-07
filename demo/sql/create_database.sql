CREATE TABLE IF NOT EXISTS country(
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(120) NOT NULL
);

CREATE TABLE IF NOT EXISTS  role(
     id INTEGER NOT NULL primary key,
     role VARCHAR(60) NOT NULL
);


CREATE TABLE IF NOT EXISTS  categories(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    category VARCHAR(120) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS  status(
    id INTEGER NOT NULL PRIMARY KEY ,
    name VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS  users(
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

CREATE TABLE IF NOT EXISTS  items(
      id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY ,
      title VARCHAR(255) NOT NULL ,
      description TEXT,
      price FLOAT(2) NOT NULL ,
      img text NOT NULL,
      category_id INTEGER NOT NULL REFERENCES categories(id),
      disable INTEGER NOT NULL DEFAULT 0
);


CREATE TABLE IF NOT EXISTS  delivery_type(
      id INTEGER NOT NULL PRIMARY KEY,
      type VARCHAR(120) not null UNIQUE
);


CREATE TABLE IF NOT EXISTS  order_list(
       id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
       address VARCHAR(255) NOT NULL ,
       city VARCHAR(120) NOT NULL ,
       country_id INTEGER NOT NULL REFERENCES country(id),
       delivery_type_id INTEGER NOT NULL REFERENCES delivery_type(id),
       user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE ,
       status_id INTEGER NOT NULL REFERENCES status(id)
);

CREATE TABLE IF NOT EXISTS  orders(
       item_id INTEGER NOT NULL REFERENCES items(id),
       order_id INTEGER NOT NULL REFERENCES order_list(id) ON DELETE CASCADE,
       quantity INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS  contact_messages(
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
     name VARCHAR(120) NOT NULL ,
     email VARCHAR(120) NOT NULL ,
     message TEXT NOT NULL ,
     phone_number VARCHAR(25) NOT NULL
);
