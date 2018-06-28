

DROP TABLE roles;
DROP TABLE users;
DROP TABLE statuses;
DROP TABLE categories;
DROP TABLE level;
DROP TABLE test;
DROP TABLE question;
DROP TABLE answer;
DROP TABLE test_questions;
DROP TABLE user_test;


CREATE TABLE roles (
	id INTEGER NOT NULL PRIMARY KEY,
	name NVARCHAR(10) NOT NULL UNIQUE
);
INSERT INTO roles VALUE(0, 'admin');
INSERT INTO roles VALUE(1, 'user');
INSERT INTO roles VALUE(2, 'block');

-- -------------------------------------------------------------------------------------
CREATE TABLE users  (
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	login NVARCHAR(20) NOT NULL UNIQUE,
	pwd NVARCHAR(20) NOT NULL,
	first_name NVARCHAR(20) NOT NULL,
	last_name NVARCHAR(20) NOT NULL,
	role_id INTEGER NOT NULL REFERENCES roles(id)
		ON DELETE CASCADE 
		ON UPDATE RESTRICT
);
INSERT INTO users (login, pwd, first_name, last_name, role_id) 
VALUES('admin', '1', 'Thao', 'Do', 0);
INSERT INTO users (login, pwd, first_name, last_name, role_id) 
VALUES('student', '1', 'Thao', 'Do', 1);
INSERT INTO users (login, pwd, first_name, last_name, role_id) 
VALUES('block', '1', 'Thao', 'Do', 2);
INSERT INTO users (login, pwd, first_name, last_name, role_id) 
VALUES('student2', '1', 'Thao', 'Do', 1);

-- -------------------------------------------------------------------------------------
CREATE TABLE statuses (
	id INTEGER NOT NULL PRIMARY KEY,
	name NVARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO statuses VALUES(1, 'passed');
INSERT INTO statuses VALUES(0, 'failed');

-- -------------------------------------------------------------------------------------
CREATE TABLE categories(
	id INTEGER NOT NULL PRIMARY KEY UNIQUE,
	name NVARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO categories VALUES(0, 'Math');
INSERT INTO categories VALUES(1, 'Physical');
INSERT INTO categories VALUES(2, 'Chemistry');

-- -------------------------------------------------------------------------------------
CREATE TABLE level(
	id INTEGER NOT NULL PRIMARY KEY UNIQUE,
    name NVARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO level(id, name) VALUES(0, 'Easy');
INSERT INTO level(id, name) VALUES(1, 'Medium');
INSERT INTO level(id, name) VALUES(2, 'Difficult');

-- -------------------------------------------------------------------------------------
CREATE TABLE test(
	id INTEGER NOT NULL AUTO_INCREMENT,
	category_id INTEGER NOT NULL REFERENCES categories(id) 
		ON DELETE CASCADE 
		ON UPDATE RESTRICT,
    name NVARCHAR(256) NOT NULL,
    level_id INTEGER NOT NULL REFERENCES level(id)
		ON DELETE CASCADE 
		ON UPDATE RESTRICT,
	duration VARCHAR(15),
    PRIMARY KEY (id, category_id)
);

INSERT INTO test(category_id, name, level_id, duration)
VALUES (1, 'Физика_Математика', 0, '30');
INSERT INTO test(category_id, name, level_id, duration)
VALUES (2, 'lkadjlakdnv', 0, '30');

-- -------------------------------------------------------------------------------------
CREATE TABLE question(
	id INTEGER NOT NULL AUTO_INCREMENT,
    category_id INTEGER NOT NULL REFERENCES categories(id)
		ON DELETE CASCADE 
		ON UPDATE RESTRICT,
	level_id INTEGER NOT NULL REFERENCES level(id)
		ON DELETE CASCADE 
		ON UPDATE RESTRICT,
	name NVARCHAR(300) NOT NULL,
    PRIMARY KEY (id, category_id)
);

-- -------------------------------------------------------------------------------------
CREATE TABLE answer(
	id INTEGER NOT NULL AUTO_INCREMENT,
	category_id INTEGER NOT NULL REFERENCES category(id)
		ON DELETE CASCADE 
        ON UPDATE RESTRICT,
	question_id INTEGER NOT NULL REFERENCES question(id)
		ON DELETE CASCADE 
		ON UPDATE RESTRICT,
    text NVARCHAR(300) NOT NULL,
	correct BOOLEAN DEFAULT false,
    PRIMARY KEY(id, category_id, question_id)
);

-- -------------------------------------------------------------------------------------
-- CREATE TABLE question_answers(
-- 	answer_id INTEGER NOT NULL REFERENCES answer(id)
-- 		ON DELETE CASCADE 
--        ON UPDATE RESTRICT,
-- 	question_id INTEGER NOT NULL REFERENCES question(id)
-- 		ON DELETE CASCADE 
-- 		ON UPDATE RESTRICT
-- );

-- -------------------------------------------------------------------------------------
CREATE TABLE test_questions(
	category_id INTEGER NOT NULL REFERENCES category(id)
		ON DELETE CASCADE 
		ON UPDATE RESTRICT,
	test_id INTEGER NOT NULL REFERENCES test(id)
		ON DELETE CASCADE 
		ON UPDATE RESTRICT,
    question_id INTEGER NOT NULL REFERENCES question(id)
		ON DELETE CASCADE 
		ON UPDATE RESTRICT,
	PRIMARY KEY(category_id, test_id, question_id)
);

-- -------------------------------------------------------------------------------------
CREATE TABLE user_test(
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	user_id INTEGER NOT NULL REFERENCES users(id)
		ON DELETE CASCADE 
		ON UPDATE RESTRICT,
	category_id INTEGER NOT NULL REFERENCES test(id)
		ON DELETE CASCADE 
		ON UPDATE RESTRICT,
    test_id INTEGER NOT NULL REFERENCES test(id)
		ON DELETE CASCADE 
		ON UPDATE RESTRICT,
    date VARCHAR(30) NOT NULL,
    result INTEGER NOT NULL,
    status_id INTEGER NOT NULL REFERENCES statuses(id)
		ON DELETE CASCADE 
		ON UPDATE RESTRICT
);

-- -------------------------------------------------------------------------------------


INSERT INTO question(category_id, level_id, name) 
VALUES (1, 0, 'Кинематика изучает');
INSERT INTO answer(category_id, question_id, text, correct) 
VALUES (1, 1, 'условия равновесия материальных тел под действием сил', false);
INSERT INTO answer(category_id, question_id, text, correct) 
VALUES (1, 1, 'геометрические законы движения тел без учета их масс и действующих на них сил', true);
INSERT INTO answer(category_id, question_id, text, correct) 
VALUES (1, 1, 'законы движения тел под действием приложенных к ним сил', false);
INSERT INTO answer(category_id, question_id, text, correct) 
VALUES (1, 1, 'законы равновесия и перемещения одного тела относительно другого под действием сил', false);

INSERT INTO test_questions(category_id, test_id, question_id) 
VALUES (1, 1, 1);

INSERT INTO question(category_id, level_id, name) 
VALUES (1, 0, 'Механика изучает');
INSERT INTO answer(category_id, question_id, text, correct) 
VALUES (1, 2, 'условия равновесия материальных тел под действием сил', false);
INSERT INTO answer(category_id, question_id, text, correct) 
VALUES (1, 2, 'геометрические законы движения тел без учета их масс и действующих на них сил', false);
INSERT INTO answer(category_id, question_id, text, correct) 
VALUES (1, 2, 'законы движения тел под действием приложенных к ним сил', false);
INSERT INTO answer(category_id, question_id, text, correct) 
VALUES (1, 2, 'законы равновесия и перемещения одного тела относительно другого под действием сил', true);

INSERT INTO test_questions(category_id, test_id, question_id) 
VALUES (1, 1, 2);

INSERT INTO question(category_id, level_id, name) 
VALUES (1, 0, 'Землю можно считать материальной точкой:');
INSERT INTO answer(category_id, question_id, text, correct) 
VALUES (1, 3, 'при изучении ее движения вокруг Солнца', true);
INSERT INTO answer(category_id, question_id, text, correct) 
VALUES (1, 3, 'при изучении ее вращательного движения вокруг своей оси', false);
INSERT INTO answer(category_id, question_id, text, correct)  
VALUES (1, 3, 'при изучении движения циклонов и антициклонов', false);
INSERT INTO answer(category_id, question_id, text, correct)  
VALUES (1, 3, 'при изучении движения литосферных плит ', false);

INSERT INTO test_questions(category_id, test_id, question_id) 
VALUES (1, 1, 3);

INSERT INTO question(category_id, level_id, name) 
VALUES (1, 0, 'Системой отсчета называют:');
INSERT INTO answer(category_id, question_id, text, correct)  
VALUES (1, 4, 'тело или совокупность неподвижных тел, относительно которой определяется пространственное и временное положение других тел', true);
INSERT INTO answer(category_id, question_id, text, correct)  
VALUES (1, 4, 'тройку линейно независимых направленных отрезков прямых, выходящих из одной точки', false);
INSERT INTO answer(category_id, question_id, text, correct)  
VALUES (1, 4, 'тело, размерами которого можно пренебречь при описании его движения', false);
INSERT INTO answer(category_id, question_id, text, correct)  
VALUES (1, 4, 'совокупность тел, движение которых рассматривается', false);

INSERT INTO test_questions(category_id, test_id, question_id) 
VALUES (1, 1, 4);




