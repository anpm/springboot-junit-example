DROP TABLE IF EXISTS employee;

CREATE SEQUENCE HIBERNATE_SEQUENCE
    START WITH 2
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) DEFAULT NULL,
  email VARCHAR(250) NOT NULL,
  active BOOLEAN NOT NULL
);

INSERT INTO employee (first_name, last_name, email, active) VALUES ('anpham', 'minh', 'anpham@gmail.com', true);