DELETE FROM Good_Order_Goods;
DELETE FROM Good_Ingredients;
DELETE FROM Good;
DELETE FROM Good_Order;
DELETE FROM Ingredient;

INSERT INTO Ingredient (id, name, type) VALUES ( 'INRL', 'read a lot', 'INTELLECT' );
INSERT INTO Ingredient (id, name, type) VALUES ( 'INAU', 'advanced understanding', 'INTELLECT' );
INSERT INTO Ingredient (id, name, type) VALUES ( 'SKDK', 'deep knowledge', 'SKILL' );
INSERT INTO Ingredient (id, name, type) VALUES ( 'SKHW', 'hard working', 'SKILL' );
INSERT INTO Ingredient (id, name, type) VALUES ( 'SKPS', 'precise speaking', 'SKILL' );
INSERT INTO Ingredient (id, name, type) VALUES ( 'ATAL', 'advanced listening', 'ATTENTION' );
INSERT INTO Ingredient (id, name, type) VALUES ( 'REBT', 'been in time', 'RESPONSIBILITY' );
INSERT INTO Ingredient (id, name, type) VALUES ( 'REFT', 'finish any task', 'RESPONSIBILITY' );