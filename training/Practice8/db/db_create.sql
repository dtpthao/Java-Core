drop table  users_groups;
drop table users;
drop table groups;

CREATE table users (
	id int NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	login VARCHAR(255) NOT NULL unique
);

CREATE table groups (
	id int NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	name VARCHAR(255) NOT NULL unique
);

CREATE table users_groups (
	user_id INT,
	group_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE,
    primary key(user_id, group_id)
);

INSERT INTO users (login) 
VALUES ('ivanov');

INSERT INTO groups (name)
VALUES ('teamA');