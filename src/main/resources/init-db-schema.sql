DROP SCHEMA IF EXISTS gymemployee CASCADE;
CREATE SCHEMA gymemployee;
USE gymemployee;

CREATE TABLE IF NOT EXISTS employee (
id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
name               VARCHAR(255)     NOT NULL,
last_name          VARCHAR(255)     NOT NULL,
birth_date         DATE,
bank_account       VARCHAR(255)     NOT NULL,
salary BIGINT                       NOT NULL
);

CREATE TABLE IF NOT EXISTS employee_roles (
employee_id        BIGINT           NOT NULL,
role               VARCHAR(50)      NOT NULL

CHECK (role IN ('STAFF', 'TRAINER', 'ADMIN')),
FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS trainer_areas (
employee_id         BIGINT          NOT NULL,
facility_id         BIGINT          NOT NULL,

PRIMARY KEY (employee_id, facility_id),
FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE
);