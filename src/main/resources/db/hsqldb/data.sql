-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled, plan, price) VALUES ('admin1','4dm1n',TRUE, 0, 20.0);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled, plan, price) VALUES ('owner1','0wn3r',TRUE, 0, 20.0);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');

INSERT INTO users(username,password,enabled, plan, price) VALUES ('owner2','0wn3r',TRUE, 0, 20.0);
INSERT INTO authorities(id,username,authority) VALUES (3,'owner2','owner');

INSERT INTO users(username,password,enabled, plan, price) VALUES ('owner3','0wn3r',TRUE, 0, 20.0);
INSERT INTO authorities(id,username,authority) VALUES (4,'owner3','owner');

INSERT INTO users(username,password,enabled, plan, price) VALUES ('owner4','0wn3r',TRUE, 0, 20.0);
INSERT INTO authorities(id,username,authority) VALUES (5,'owner4','owner');

INSERT INTO users(username,password,enabled, plan, price) VALUES ('owner5','0wn3r',TRUE, 0, 20.0);
INSERT INTO authorities(id,username,authority) VALUES (6,'owner5','owner');

INSERT INTO users(username,password,enabled, plan, price) VALUES ('owner6','0wn3r',TRUE, 0, 20.0);
INSERT INTO authorities(id,username,authority) VALUES (7,'owner6','owner');

INSERT INTO users(username,password,enabled, plan, price) VALUES ('owner7','0wn3r',TRUE, 0, 20.0);
INSERT INTO authorities(id,username,authority) VALUES (8,'owner7','owner');

INSERT INTO users(username,password,enabled, plan, price) VALUES ('owner8','0wn3r',TRUE, 0, 20.0);
INSERT INTO authorities(id,username,authority) VALUES (9,'owner8','owner');

INSERT INTO users(username,password,enabled, plan, price) VALUES ('owner9','0wn3r',TRUE, 0, 20.0);
INSERT INTO authorities(id,username,authority) VALUES (10,'owner9','owner');

INSERT INTO users(username,password,enabled, plan, price) VALUES ('owner10','0wn3r',TRUE, 0, 20.0);
INSERT INTO authorities(id,username,authority) VALUES (11,'owner10','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled, plan, price) VALUES ('vet1','v3t',TRUE, 0, 20.0);
INSERT INTO authorities(id,username,authority) VALUES (12,'vet1','veterinarian');

INSERT INTO users(username,password,enabled, plan, price) VALUES ('clinicowner1','cw1',TRUE, 0, 20.0);
INSERT INTO authorities(id,username,authority) VALUES (13,'clinicowner1','clinicOwner');

INSERT INTO users(username,password,enabled, plan, price) VALUES ('clinicowner2','cw2',TRUE, 1, 30.0);
INSERT INTO authorities(id,username,authority) VALUES (14,'clinicowner2','clinicOwner');

INSERT INTO vets(id, first_name,last_name) VALUES (1, 'James', 'Carter');
INSERT INTO vets(id, first_name,last_name) VALUES (2, 'Helen', 'Leary');
INSERT INTO vets(id, first_name,last_name) VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets(id, first_name,last_name) VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets(id, first_name,last_name) VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets(id, first_name,last_name) VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');

INSERT INTO clinic_owner (id, first_name, last_name, address, city, telephone, username)
	VALUES (1, 'Mario', 'Gonzales', 'Avenida Reina Mercedes 32', 'Barcelona', '666666666', 'clinicowner1');
INSERT INTO clinic_owner (id, first_name, last_name, address, city, telephone, username)
	VALUES (2, 'Marta', 'Rubiales', 'Calle cartuja 6', 'Sevilla', '666666661', 'clinicowner2');
	
INSERT INTO clinic (id, name, address, city, telephone, clinic_owner_id) VALUES (1, 'PetVet', 'C/ Madrid', 'Barcelona', '655555555', 1);
INSERT INTO clinic (id, name, address, city, telephone, clinic_owner_id) VALUES (2, 'GatoMania', 'C/ Rosario', 'Sevilla', '655555551', 2);
INSERT INTO clinic (id, name, address, city, telephone, clinic_owner_id) VALUES (3, 'PerroMania', 'C/ Rosario 2', 'Sevilla', '655555551', 2);

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner2');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner3');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner4');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner5');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner6');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner7');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner8');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner9');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner10');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

INSERT INTO pet_hotels(id,initial_date,final_date,pet_id) VALUES (1,'2023-01-04','2023-03-07',1);
INSERT INTO pet_hotels(id,initial_date,final_date,pet_id) VALUES (2,'2023-01-04','2023-03-07',3);
INSERT INTO pet_hotels(id,initial_date,final_date,pet_id) VALUES (3,'2023-01-04','2023-03-07',2);

INSERT INTO adoption_request(id,pet_id) VALUES (1,2);
INSERT INTO adoption_request(id,pet_id) VALUES (2,3);

INSERT INTO adoption_application(id,description,adoption_request_id,owner_id) VALUES (1,'Me encantan los animales y estaba buscando una mascota pequeña ya que no tengo mucho sitio en casa.',1,3);
INSERT INTO adoption_application(id,description,adoption_request_id,owner_id) VALUES (3,'Me encantan los animales y estaba buscando una mascota pequeña ya que no tengo mucho sitio en casa.',1,3);
INSERT INTO adoption_application(id,description,adoption_request_id,owner_id) VALUES (4,'Me encantan los animales y estaba buscando una mascota pequeña ya que no tengo mucho sitio en casa.',1,3);
INSERT INTO adoption_application(id,description,adoption_request_id,owner_id) VALUES (5,'Me encantan los animales y estaba buscando una mascota pequeña ya que no tengo mucho sitio en casa.',1,3);

INSERT INTO adoption_application(id,description,adoption_request_id,owner_id) VALUES (2,'Me puedo hacer cargo del animal, tengo otros perros y son muy sociables. Vivo solo y tengo un piso bastante amplio.',2,4);

--Causes:
INSERT INTO causes(id, name, description,budget_target,organization) VALUES(1, 'RescataGatos', 'Buscamos dinero para comida de gatos callejeros', 100.0, 'SalvaCats');
INSERT INTO causes(id, name, description,budget_target,organization) VALUES(2, 'Linces Protegidos', 'Ayuda a los linces a sobrevivir', 10.0, 'Junta de Andalucía');

--Donations:
INSERT INTO donations(id,amount,date,owner_id,cause_id) VALUES(1,40.0,'2022-01-04',1,1);
INSERT INTO donations(id,amount,date,owner_id,cause_id) VALUES(2,30.0,'2022-01-04',2,1);
INSERT INTO donations(id,amount,date,owner_id,cause_id) VALUES(3,10.0,'2022-01-04',1,2);

--CAVersions
INSERT INTO CA_types VALUES(1, 'Basic');
INSERT INTO CA_types VALUES(2, 'Advanced');
INSERT INTO CA_types VALUES(3, 'Pro');
INSERT INTO CA_Version(id,currentplan_id) VALUES(1,1);
INSERT INTO CA_Version(id,currentplan_id) VALUES(2,2);
INSERT INTO CA_Version(id,currentplan_id) VALUES(3,3);