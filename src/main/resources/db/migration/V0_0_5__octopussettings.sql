CREATE TABLE octopus_settings (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	record_status INT NOT NULL,
	created_on DATETIME NOT NULL,
	setting_key varchar(50) NOT NULL,
    setting_value varchar(50) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO octopus_settings VALUES(1, 1, now(), 'DELIVERY_DIFF', '1');