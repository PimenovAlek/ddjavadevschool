create table company_unit(
id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL,
creationDate timestamp NOT NULL,
headquarter_id INT 
);

create table employee_card(
id SERIAL PRIMARY KEY,
firstname VARCHAR(50) NOT NULL,
lastname VARCHAR(50) NOT NULL,
thirdname VARCHAR(50),
login VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
creationDate timestamp NOT NULL,
role_id INT NOT NULL,
position_id INT NOT NULL,
unit_id INT NOT NULL
);

ALTER TABLE company_unit
 ADD CONSTRAINT fk_company_unit_employee_card FOREIGN KEY (headquarter_id) REFERENCES employee_card (id);

create table unit_position(
id INT PRIMARY KEY,
parent_id INT,
FOREIGN KEY (parent_id) REFERENCES company_unit(id));

create table user_position(
id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL);

create table rights(
id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL);

create table roles(
id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL);

create table role_rights(
role_id INT NOT NULL,
right_id INT NOT NULL,
FOREIGN KEY (role_id) REFERENCES roles (id),
FOREIGN KEY (right_id) REFERENCES rights (id));

ALTER TABLE employee_card
 ADD CONSTRAINT fk_employee_card_roles FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE employee_card
 ADD CONSTRAINT fk_employee_card_user_position FOREIGN KEY (position_id) REFERENCES user_position (id);


INSERt INTO roles(name) VALUES('пользователь');
INSERT INTO user_position(name) VALUES('директор компании');
INSERT INTO user_position(name) VALUES('начальник департамента');
INSERT INTO user_position(name) VALUES('начальник отдела');
INSERT INTO user_position(name) VALUES('тестировщик');
INSERT INTO user_position(name) VALUES('разработчик');
INSERT INTO employee_card(firstname,lastname,login,password,creationdate,unit_id,role_id,position_id)
VALUES ('ALEX','Pimenov','test@test.te','123','2022-03-04 13:21', 1,1,1);
INSERT INTO employee_card(firstname,lastname,login,password,creationdate,unit_id,role_id,position_id)
VALUES ('Bob','Bobb','test1@test.te','123','2022-03-04 13:21', 2,1,2);
INSERT INTO employee_card(firstname,lastname,login,password,creationdate,unit_id,role_id,position_id)
VALUES ('Lex','Luthor','test2@test.te','123','2022-03-04 13:21', 3,1,3);
INSERT INTO employee_card(firstname,lastname,login,password,creationdate,unit_id,role_id,position_id)
VALUES ('Bruce','Wayne','test3@test.te','123','2022-03-04 13:21', 3,1,5);
INSERT INTO employee_card(firstname,lastname,login,password,creationdate,unit_id,role_id,position_id)
VALUES ('Jim','Gorgon','test@test.te','123','2022-03-04 13:21', 3,1,5);
INSERT INTO company_unit(name, headquarter_id,creationdate) VALUES('ДД', 1,'2022-03-04 12:18');
INSERT INTO company_unit(name, headquarter_id,creationdate) VALUES('департамент персонала', 1,'2022-03-04 12:18');
INSERT INTO company_unit(name, headquarter_id,creationdate) VALUES('отдел 1', 1,'2022-03-04 12:18');
INSERT INTO company_unit(name, headquarter_id,creationdate) VALUES('отдел 2', 1,'2022-03-04 12:18');
INSERT INTO unit_position(id, parent_id) VALUES(1, null);
INSERT INTO unit_position(id, parent_id) VALUES(2,1);
INSERT INTO unit_position(id, parent_id) VALUES(3,2);
INSERT INTO unit_position(id, parent_id) VALUES(4,2);
INSERT INTO rights(name) VALUES ('просмотр');
INSERT INTO rights(name) VALUES ('редактирование');
INSERT INTO rights(name) VALUES ('удаление');