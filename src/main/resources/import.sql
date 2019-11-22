DELETE FROM Good_Order_Goods;
DELETE FROM Good_Ingredients;
DELETE FROM Good;
DELETE FROM Good_Order;
DELETE FROM Ingredient;

INSERT INTO Ingredient (id, name, type) VALUES ( 'INRL', 'read a lot', 0 );
INSERT INTO Ingredient (id, name, type) VALUES ( 'INAU', 'advanced understanding', 0 );
INSERT INTO Ingredient (id, name, type) VALUES ( 'SKDK', 'deep knowledge', 1 );
INSERT INTO Ingredient (id, name, type) VALUES ( 'SKHW', 'hard working', 1 );
INSERT INTO Ingredient (id, name, type) VALUES ( 'SKPS', 'precise speaking', 1 );
INSERT INTO Ingredient (id, name, type) VALUES ( 'ATAL', 'advanced listening', 2 );
INSERT INTO Ingredient (id, name, type) VALUES ( 'REBT', 'been in time', 3 );
INSERT INTO Ingredient (id, name, type) VALUES ( 'REFT', 'finish any task', 3 );