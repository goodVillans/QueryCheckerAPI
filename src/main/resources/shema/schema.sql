DROP DATABASE IF EXISTS query_test;
CREATE DATABASE query_test;
USE query_test;

-- Lookup Tables
CREATE TABLE CustomerStatus (
    CustomerStatusId INT PRIMARY KEY AUTO_INCREMENT,
    CustomerStatusName VARCHAR(50) NOT NULL
);

CREATE TABLE Channel (
    ChannelId INT PRIMARY KEY AUTO_INCREMENT,
    ChannelName VARCHAR(50) NOT NULL
);

CREATE TABLE Level (
    LevelId INT PRIMARY KEY AUTO_INCREMENT,
    LevelName VARCHAR(50) NOT NULL
);

CREATE TABLE StockStatus (
    StockStatusId INT PRIMARY KEY AUTO_INCREMENT,
    StatusName VARCHAR(50) NOT NULL
);

CREATE TABLE StockGroup (
    StockGroupId INT PRIMARY KEY AUTO_INCREMENT,
    GroupName VARCHAR(50) NOT NULL
);

-- Primary Tables
CREATE TABLE Customer (
    CustomerId INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    Surname VARCHAR(50) NOT NULL,
    CustomerStatusId INT NOT NULL,
    ChannelId INT NOT NULL,
    CreateDateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LevelId INT NOT NULL,
    FOREIGN KEY (CustomerStatusId) REFERENCES CustomerStatus(CustomerStatusId),
    FOREIGN KEY (ChannelId) REFERENCES Channel(ChannelId),
    FOREIGN KEY (LevelId) REFERENCES Level(LevelId)
);

CREATE TABLE Stock (
    StockId INT PRIMARY KEY AUTO_INCREMENT,
    Description VARCHAR(50) NOT NULL,
    Quantity INT NOT NULL,
    StockStatusId INT NOT NULL,
    StockGroupId INT NOT NULL,
    FOREIGN KEY (StockStatusId) REFERENCES StockStatus(StockStatusId),
    FOREIGN KEY (StockGroupId) REFERENCES StockGroup(StockGroupId)
);

CREATE TABLE Sale (
    SaleId INT PRIMARY KEY AUTO_INCREMENT,
    CustomerId INT NOT NULL,
    CreateDateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    SaleStatus VARCHAR(50),
    CompleteDateTime TIMESTAMP NULL,
    SaleChannelId INT,
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId)
);

CREATE TABLE SaleItem (
    SaleItemId INT PRIMARY KEY AUTO_INCREMENT,
    SaleId INT NOT NULL,
    StockId INT NOT NULL,
    Quantity INT NOT NULL,
    Price DECIMAL(10,2) NOT NULL,
    Tax DECIMAL(10,2),
    Discount DECIMAL(10,2),
    FOREIGN KEY (SaleId) REFERENCES Sale(SaleId),
    FOREIGN KEY (StockId) REFERENCES Stock(StockId)
);

-- Sample Data
INSERT INTO CustomerStatus (CustomerStatusName) VALUES
('Active'),
('Inactive'),
('VIP'),
('Suspended');

INSERT INTO Channel (ChannelName) VALUES
('Online'),
('Store'),
('Phone'),
('Mobile App');

INSERT INTO Level (LevelName) VALUES
('Bronze'),
('Silver'),
('Gold'),
('Platinum');

INSERT INTO StockStatus (StatusName) VALUES
('In Stock'),
('Low Stock'),
('Out of Stock'),
('Discontinued');

INSERT INTO StockGroup (GroupName) VALUES
('Electronics'),
('Clothing'),
('Home'),
('Sports'),
('Books');

INSERT INTO Customer (FirstName, Surname, CustomerStatusId, ChannelId, LevelId, CreateDateTime) VALUES
('John', 'Smith', 1, 1, 2, '2018-01-01 10:00:00'),
('Sarah', 'Johnson', 3, 2, 4, '2018-01-02 11:00:00'),
('Michael', 'Williams', 1, 1, 1, '2018-01-03 12:00:00'),
('Emma', 'Brown', 1, 3, 2, '2018-01-04 13:00:00'),
('James', 'Jones', 2, 4, 1, '2018-01-05 14:00:00'),
('Emily', 'Garcia', 1, 2, 3, '2018-01-06 15:00:00'),
('William', 'Miller', 3, 1, 4, '2018-01-07 16:00:00'),
('Olivia', 'Davis', 1, 3, 2, '2018-01-08 17:00:00'),
('Alexander', 'Rodriguez', 1, 2, 1, '2018-01-09 18:00:00'),
('Sophia', 'Martinez', 1, 1, 3, '2018-01-10 19:00:00');

INSERT INTO Stock (Description, Quantity, StockStatusId, StockGroupId) VALUES
('iPhone 13', 50, 1, 1),
('Samsung TV', 30, 1, 1),
('Nike Running Shoes', 100, 1, 4),
('Adidas T-Shirt', 150, 1, 2),
('Coffee Maker', 25, 2, 3),
('Gaming Console', 20, 1, 1),
('Yoga Mat', 75, 1, 4),
('Gaming Keyboard', 30, 1, 1),
('Desk Lamp', 45, 1, 3),
('Wireless Mouse', 55, 1, 1),
('Laptop Stand', 25, 1, 3),
('Hiking Boots', 20, 1, 4),
('Winter Jacket', 45, 1, 2),
('Smart Watch', 60, 1, 1),
('Bluetooth Speaker', 40, 1, 1),
('Tennis Racket', 30, 1, 4),
('Office Chair', 25, 2, 3);

INSERT INTO Sale (CustomerId, CreateDateTime, SaleStatus, CompleteDateTime, SaleChannelId) VALUES
(1, '2018-01-15 14:30:00', 'Complete', '2018-01-15 14:35:00', 1),
(2, '2018-01-16 09:45:00', 'Complete', '2018-01-16 10:00:00', 2),
(3, '2018-01-20 16:20:00', 'Complete', '2018-01-20 16:30:00', 1),
(4, '2018-01-25 11:15:00', 'Complete', '2018-01-25 11:30:00', 3),
(5, '2018-02-01 13:20:00', 'Complete', '2018-02-01 13:30:00', 4),
(1, '2018-02-05 15:45:00', 'Complete', '2018-02-05 16:00:00', 1),
(2, '2018-02-10 10:30:00', 'Complete', '2018-02-10 10:45:00', 2),
(3, '2018-02-15 14:20:00', 'Complete', '2018-02-15 14:30:00', 1),
(4, '2018-02-20 09:15:00', 'Complete', '2018-02-20 09:30:00', 3),
(5, '2018-02-25 16:45:00', 'Complete', '2018-02-25 17:00:00', 4);

INSERT INTO SaleItem (SaleId, StockId, Quantity, Price, Tax, Discount) VALUES
(1, 1, 1, 999.99, 100.00, 0.00),     -- iPhone
(1, 3, 2, 89.99, 9.00, 10.00),       -- Nike Shoes
(2, 2, 1, 599.99, 60.00, 50.00),     -- Samsung TV
(2, 4, 3, 29.99, 3.00, 5.00),        -- Adidas T-Shirt
(3, 5, 1, 199.99, 20.00, 0.00),      -- Coffee Maker
(3, 6, 1, 499.99, 50.00, 25.00),     -- Gaming Console
(4, 7, 2, 39.99, 4.00, 0.00),        -- Yoga Mat
(4, 8, 1, 129.99, 13.00, 20.00),     -- Winter Jacket
(5, 9, 1, 299.99, 30.00, 0.00),      -- Smart Watch
(5, 10, 2, 79.99, 8.00, 10.00),      -- Bluetooth Speaker
(6, 11, 1, 159.99, 16.00, 0.00),     -- Tennis Racket
(6, 12, 1, 249.99, 25.00, 25.00),    -- Office Chair
(7, 1, 1, 999.99, 100.00, 50.00),    -- iPhone
(8, 2, 1, 599.99, 60.00, 0.00),      -- Samsung TV
(9, 3, 2, 89.99, 9.00, 10.00),       -- Nike Shoes
(10, 4, 1, 29.99, 3.00, 0.00);       -- Adidas T-Shirt