INSERT INTO employee (name, last_name, birth_date, bank_account, salary) VALUES
('Alice', 'Johnson', '1985-07-20', '987654321', 60000),
('Bob', 'Smith', '1990-03-15', '123456789', 55000),
('Charlie', 'Brown', '1982-11-25', '567890123', 70000),
('Diana', 'Prince', '1995-06-08', '345678901', 65000),
('Ethan', 'Hunt', '1988-09-17', '234567890', 72000);

INSERT INTO employee_roles (employee_id, role) VALUES
(1, 'ADMIN'), (1, 'TRAINER'),
(2, 'STAFF'),
(3, 'TRAINER'), (4, 'STAFF'),
(5, 'ADMIN');

INSERT INTO trainer_areas (employee_id, facility_id) VALUES
(1, 1), (1, 2),
(2, 3),
(3, 4), (3, 5),
(5, 6);
