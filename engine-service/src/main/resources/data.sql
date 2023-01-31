insert into usr(id, username, firstname, lastname, email, password) values(1,'tony99', 'Antonio','Rossi','antonio.rossi@email.com', '$2a$12$HbQcKnaGnw18oit6k7XCR.gFmWFfvm4bLYh7ul5WQylynYLhD4vda');

insert into roles(id, name) values(1, 'ROLE_USER');

insert into user_roles(user_id, role_id) values(1, 1);
