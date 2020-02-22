CREATE SCHEMA IF NOT EXISTS intuit_payment;

DROP TABLE IF EXISTS PAYMENT;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS EXTERNAL_USER;
DROP TABLE IF EXISTS PAYMENT_METHOD;
  
 CREATE TABLE USER (
    id VARCHAR(50)  PRIMARY KEY,
    NAME VARCHAR(200) NOT NULL,
    EMAIL VARCHAR(200) default NULL,
    DELETED BOOLEAN default false
);
INSERT INTO `intuit_payment`.`USER`(`id`,`NAME`,`EMAIL`)
VALUES
('ebbd8b9d-a065-4752-b545-e0103d7aa1d1','leonidb','some@email.com');
INSERT INTO `intuit_payment`.`USER`(`id`,`NAME`,`EMAIL`)
VALUES
('e7bdca08-056f-11e9-ade9-2dce83242c71','maxd','max@email.com');



 CREATE TABLE EXTERNAL_USER (
    id VARCHAR(50)  PRIMARY KEY,
    NAME VARCHAR(200) NOT NULL,
    EMAIL VARCHAR(200) default NULL,
    DELETED BOOLEAN default false
);  

INSERT INTO `intuit_payment`.`EXTERNAL_USER`(`id`,`NAME`,`EMAIL`)
VALUES
('ebbd8b9d-a065-4752-b545-e0103d7aa1d1','yuval payee','yuval@email.com');
INSERT INTO `intuit_payment`.`EXTERNAL_USER`(`id`,`NAME`,`EMAIL`)
VALUES
('e7bdca08-056f-11e9-ade9-2dce83242c71','nir payee','nir@email.com');

 CREATE TABLE PAYMENT_METHOD (
    id VARCHAR(50)  PRIMARY KEY,
    NAME VARCHAR(200) NOT NULL,
    DELETED BOOLEAN default false
);  
  
  INSERT INTO `intuit_payment`.`PAYMENT_METHOD`(`id`,`NAME`)
VALUES
('ebbd8b9d-a065-4752-b545-e0103d7aa1d1','payment method1');
INSERT INTO `intuit_payment`.`PAYMENT_METHOD`(`id`,`NAME`)
VALUES
('e7bdca08-056f-11e9-ade9-2dce83242c71','payment method2');
  
CREATE TABLE PAYMENT (
    id VARCHAR(50)  PRIMARY KEY,
    PAYMENT_REQUEST_ID VARCHAR(50) NOT NULL,
    USER_ID VARCHAR(50) NOT NULL,
    PAYEE_ID VARCHAR(50) NOT NULL,
    PAYMENT_METHOD_ID VARCHAR(50) NOT NULL,
    AMOUNT DECIMAL(13, 4) NOT NULL,
    CURRENCY VARCHAR(20) NOT NULL,
    SUCCEEDED BOOLEAN not null,
    CONSTRAINT fk_user
    FOREIGN KEY (USER_ID) REFERENCES USER(ID),
	CONSTRAINT fk_ext_user
    FOREIGN KEY (PAYEE_ID) REFERENCES EXTERNAL_USER(ID),
    CONSTRAINT fk_payment_method
    FOREIGN KEY (PAYMENT_METHOD_ID) REFERENCES PAYMENT_METHOD(ID)
);

