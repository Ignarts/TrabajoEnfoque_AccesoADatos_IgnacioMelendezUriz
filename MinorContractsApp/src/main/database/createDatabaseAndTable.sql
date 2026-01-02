CREATE DATABASE IF NOT EXISTS minor_contracts_db;
USE minor_contracts_db;

CREATE TABLE IF NOT EXISTS contracts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nif VARCHAR(20),
    awardedTo VARCHAR(255),
    genericObject VARCHAR(255),
    objectDescription TEXT,
    awardedDate VARCHAR(50),
    amount DECIMAL(10, 2),
    consultedProviders TEXT
);