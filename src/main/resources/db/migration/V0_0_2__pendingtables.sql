CREATE TABLE user_address (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	record_status INT NOT NULL,
	created_on DATETIME NOT NULL,
	user_id bigint(20) NOT NULL,
	alternate_contact_number varchar(50),
	house_or_flat_no varchar(50) ,
	building_or_house_name varchar(50) ,
	street varchar(50) NOT NULL,
	landmark varchar(50) ,
	locality varchar(50) NOT NULL,
	city varchar(50) NOT NULL,
	pincode INT,
	PRIMARY KEY (id)
);


CREATE TABLE mstr_item_type (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	record_status INT NOT NULL,
	created_on DATETIME NOT NULL,
	type_name varchar(50) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE mstr_item (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	record_status INT NOT NULL,
	created_on DATETIME NOT NULL,
	gst_id bigint(20) NOT NULL,
	item_type_id bigint(20) NOT NULL,
	name varchar(100) NOT NULL,
	item_size varchar(20) NOT NULL,
	is_available BOOLEAN NOT NULL,
	max_price DECIMAL(10,2) ,
	actual_price DECIMAL(10,2) ,
	selling_price DECIMAL(10,2) ,
	qty_type varchar(10),
	qty_value DECIMAL(10,2) ,
	PRIMARY KEY (id)
);


CREATE TABLE mstr_gst (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	record_status INT NOT NULL,
	created_on DATETIME NOT NULL,
	gst_value DECIMAL(4,2) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE item_images (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	record_status INT NOT NULL,
	created_on DATETIME NOT NULL,
	item_id bigint NOT NULL,
	image_path varchar(100) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE order_history (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	record_status INT NOT NULL,
	created_on DATETIME NOT NULL,
	user_id bigint(20) NOT NULL,
	user_address_id bigint NOT NULL,
	item_id bigint(20) NOT NULL,
	ordered_qty_type varchar(10) NOT NULL,
	ordered_qty_value DECIMAL(10,2) NOT NULL,
	committed_delivery_time DATETIME NOT NULL,
	order_status varchar(1) NOT NULL,
	PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE user_address ADD CONSTRAINT user_address_fk0 FOREIGN KEY (user_id) REFERENCES mstr_user(id);
ALTER TABLE mstr_item ADD CONSTRAINT mstr_item_fk0 FOREIGN KEY (gst_id) REFERENCES mstr_gst(id);
ALTER TABLE mstr_item ADD CONSTRAINT mstr_item_fk1 FOREIGN KEY (item_type_id) REFERENCES mstr_item_type(id);
ALTER TABLE item_images ADD CONSTRAINT item_images_fk0 FOREIGN KEY (item_id) REFERENCES mstr_item(id);
ALTER TABLE order_history ADD CONSTRAINT order_history_fk0 FOREIGN KEY (user_id) REFERENCES mstr_user(id);
ALTER TABLE order_history ADD CONSTRAINT order_history_fk1 FOREIGN KEY (user_address_id) REFERENCES user_address(id);
ALTER TABLE order_history ADD CONSTRAINT order_history_fk2 FOREIGN KEY (item_id) REFERENCES mstr_item(id);