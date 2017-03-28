DELETE FROM users;
DELETE FROM roles;
DELETE FROM laptops;

INSERT INTO roles (role) VALUES ('admin'), ('user'), ('roleForDel');

INSERT INTO users (login, password, role)
VALUES ('userLogin1', 'userPassword1', 'admin'), ('userLogin2', 'userPassword2', 'user');

INSERT INTO laptops (model, make, screen, processor, memory, image, amount, price)
VALUES ('model1', 'make1', 15.4, 'processor1', 1, 'image1', 1, 1.11), ('model2', 'make2', 15.4, 'processor2', 2, 'image2', 2, 2.22);