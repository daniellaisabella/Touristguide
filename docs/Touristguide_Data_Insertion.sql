USE Touristguide;

-- Insert Cities
INSERT INTO CITY (NAME) VALUES 
('KØBENHAVN'),
('BILLUND'),
('HELSINGØR'),
('MØN'),
('AARHUS'),
('RIBE');

-- Insert Tags
INSERT INTO TAG (NAME) VALUES 
('FORLYSTELSESPARK'),
('RESTAURANT'),
('ARKITEKTUR'),
('HISTORIE'),
('MUSEUM'),
('NATUROPLEVELSE'),
('LANDSKAB'),
('UDSIGTSPUNKT'),
('KUNST');

-- Insert Attractions
INSERT INTO TOURISTATTRACTION (NAME, DESCRIPTION, CITY_ID)
SELECT 'Tivoli', 'En historisk forlystelsespark i hjertet af København.', CITY_ID FROM CITY WHERE NAME = 'KØBENHAVN';

INSERT INTO TOURISTATTRACTION (NAME, DESCRIPTION, CITY_ID)
SELECT 'Den Lille Havfrue', 'En berømt bronzestatue inspireret af H.C. Andersens eventyr.', CITY_ID FROM CITY WHERE NAME = 'KØBENHAVN';

INSERT INTO TOURISTATTRACTION (NAME, DESCRIPTION, CITY_ID)
SELECT 'Legoland Billund', 'En familievenlig forlystelsespark bygget af LEGO-klodser.', CITY_ID FROM CITY WHERE NAME = 'BILLUND';

INSERT INTO TOURISTATTRACTION (NAME, DESCRIPTION, CITY_ID)
SELECT 'Nyhavn', 'Et ikonisk havnekvarter med farverige bygninger og livlige caféer.', CITY_ID FROM CITY WHERE NAME = 'KØBENHAVN';

INSERT INTO TOURISTATTRACTION (NAME, DESCRIPTION, CITY_ID)
SELECT 'Kronborg Slot', 'Shakespeares berømte Hamlet-slot, fyldt med historie og kultur.', CITY_ID FROM CITY WHERE NAME = 'HELSINGØR';

INSERT INTO TOURISTATTRACTION (NAME, DESCRIPTION, CITY_ID)
SELECT 'Møns Klint', 'Storslåede hvide kridtklinter med fantastisk udsigt over havet.', CITY_ID FROM CITY WHERE NAME = 'MØN';

INSERT INTO TOURISTATTRACTION (NAME, DESCRIPTION, CITY_ID)
SELECT 'Rundetårn', 'Et gammelt observatorium med en unik spiralrampe og flot udsigt.', CITY_ID FROM CITY WHERE NAME = 'KØBENHAVN';

INSERT INTO TOURISTATTRACTION (NAME, DESCRIPTION, CITY_ID)
SELECT 'ARoS Aarhus Kunstmuseum', 'Et moderne kunstmuseum kendt for sin regnbuepanorama.', CITY_ID FROM CITY WHERE NAME = 'AARHUS';

INSERT INTO TOURISTATTRACTION (NAME, DESCRIPTION, CITY_ID)
SELECT 'Ribe Domkirke', 'Danmarks ældste domkirke i en charmerende middelalderby.', CITY_ID FROM CITY WHERE NAME = 'RIBE';

INSERT INTO TOURISTATTRACTION (NAME, DESCRIPTION, CITY_ID)
SELECT 'Nationalmuseet', 'Danmarks største kulturhistoriske museum med unikke udstillinger.', CITY_ID FROM CITY WHERE NAME = 'KØBENHAVN';

-- Insert Attraction-Tag Relationships
INSERT INTO ATTRACTION_TAG (ATTRACTION_ID, TAG_ID) VALUES
((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'Tivoli'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'FORLYSTELSESPARK')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'Tivoli'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'RESTAURANT')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'Den Lille Havfrue'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'ARKITEKTUR')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'Den Lille Havfrue'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'HISTORIE')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'Legoland Billund'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'FORLYSTELSESPARK')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'Nyhavn'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'RESTAURANT')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'Nyhavn'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'ARKITEKTUR')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'Kronborg Slot'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'HISTORIE')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'Kronborg Slot'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'ARKITEKTUR')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'Kronborg Slot'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'MUSEUM')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'Møns Klint'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'NATUROPLEVELSE')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'Møns Klint'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'LANDSKAB')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'Møns Klint'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'UDSIGTSPUNKT')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'Rundetårn'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'UDSIGTSPUNKT')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'Rundetårn'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'HISTORIE')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'ARoS Aarhus Kunstmuseum'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'KUNST')),

((SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = 'ARoS Aarhus Kunstmuseum'), 
 (SELECT TAG_ID FROM TAG WHERE NAME = 'MUSEUM'));