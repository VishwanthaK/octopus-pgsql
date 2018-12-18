DROP TABLE IF EXISTS mstr_role;
CREATE TABLE mstr_role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  record_status int NOT NULL,
  created_on datetime NOT NULL,

  name varchar(50) NOT NULL,
  PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
INSERT INTO mstr_role VALUES (1,1,'2017-09-18 17:38:31','ROLE_ADMIN'),(2,1,'2017-09-18 17:38:31','ROLE_USER');

DROP TABLE IF EXISTS mstr_user;
CREATE TABLE mstr_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  record_status int NOT NULL,
  created_on datetime NOT NULL,

  fullname varchar(50) NOT NULL,
  email varchar(50) DEFAULT NULL,
  username varchar(50) NOT NULL,
  mobile_number varchar(10) NOT NULL,
  password varchar(100) NOT NULL,
  enabled boolean NOT NULL,

  PRIMARY KEY (id),
  CONSTRAINT unique_username_key UNIQUE  (username),
  CONSTRAINT unique_mobile_number_key UNIQUE  (mobile_number)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
INSERT INTO mstr_user VALUES
(1,1,'2017-08-29 18:17:46','Admin1','app1@admin.com','8749006584','8749006584','$2a$10$WDgIzYky2cKHNBOYZHGJgepMqKFKpQ9X4VkVAd6UF4gVPypdjdFU.',true);

DROP TABLE IF EXISTS mstr_user_role;
CREATE TABLE mstr_user_role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  record_status int NOT NULL,
  created_on datetime NOT NULL,
  
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,

  PRIMARY KEY (id),
  CONSTRAINT fk_user_role_role_id FOREIGN KEY (role_id) REFERENCES mstr_role (id),
  CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES mstr_user (id)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
CREATE INDEX fk_user_role_role_id ON mstr_user_role (role_id);
CREATE INDEX fk_user_role_user_id ON mstr_user_role (user_id);
INSERT INTO mstr_user_role VALUES (1,1,'2017-09-03 11:26:08',1,1);