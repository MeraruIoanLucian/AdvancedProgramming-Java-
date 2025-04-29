CREATE TABLE continents (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE countries (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           code CHAR(3) NOT NULL UNIQUE,
                           continent_id INT NOT NULL,
                           FOREIGN KEY (continent_id) REFERENCES continents(id)
);