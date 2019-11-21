CREATE TABLE IF NOT EXISTS Ingredient (
    id VARCHAR(4) NOT NULL,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(30) NOT NULL 
);

CREATE TABLE IF NOT EXISTS Good (
    id IDENTITY,
    name VARCHAR(50) NOT NULL,
    createdAt VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS Good_Ingredients (
    good BIGINT NOT NULL,
    ingredient VARCHAR(4) NOT NULL
);

ALTER TABLE Good_Ingredients ADD FOREIGN KEY (good) REFERENCES Good(id);
ALTER TABLE Good_Ingredients ADD FOREIGN KEY (ingredient) REFERENCES Ingredient(id);

CREATE TABLE IF NOT EXISTS Good_Order (
    id IDENTITY,
    deliveryName VARCHAR(50) NOT NULL,
    deliveryStreet VARCHAR(50) NOT NULL,
    deliveryCity VARCHAR(50) NOT NULL,
    deliveryState VARCHAR(20) NOT NULL,
    deliveryZip VARCHAR(20) NOT NULL,
    ccNumber VARCHAR(16) NOT NULL,
    ccExpiration VARCHAR(5) NOT NULL,
    ccCVV VARCHAR(3) NOT NULL,
    placedAt TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS Good_Order_Goods (
  goodOrder BIGINT NOT NULL,
  good BIGINT NOT NULL                                    
);

ALTER TABLE Good_Order_Goods ADD FOREIGN KEY (goodOrder) REFERENCES Good_Order(id);
ALTER TABLE Good_Order_Goods ADD FOREIGN KEY (good) REFERENCES Good(id);