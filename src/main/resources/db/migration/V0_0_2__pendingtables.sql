CREATE SEQUENCE user_address_seq;
CREATE TABLE user_address (
	id bigint NOT NULL DEFAULT NEXTVAL ('user_address_seq'),
	record_status INT NOT NULL,
	created_on TIMESTAMP(0) NOT NULL,
	user_id bigint NOT NULL,
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

CREATE SEQUENCE mstr_item_type_seq;
CREATE TABLE mstr_item_type (
	id bigint NOT NULL DEFAULT NEXTVAL ('mstr_item_type_seq'),
	record_status INT NOT NULL,
	created_on TIMESTAMP(0) NOT NULL,
	type_name varchar(50) NOT NULL,
	PRIMARY KEY (id)
);

CREATE SEQUENCE mstr_item_seq;
CREATE TABLE mstr_item (
	id bigint NOT NULL DEFAULT NEXTVAL ('mstr_item_seq'),
	record_status INT NOT NULL,
	created_on TIMESTAMP(0) NOT NULL,
	gst_id bigint NOT NULL,
	item_type_id bigint NOT NULL,
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

CREATE SEQUENCE mstr_gst_seq;
CREATE TABLE mstr_gst (
	id bigint NOT NULL DEFAULT NEXTVAL ('mstr_gst_seq'),
	record_status INT NOT NULL,
	created_on TIMESTAMP(0) NOT NULL,
	gst_value DECIMAL(4,2) NOT NULL,
	PRIMARY KEY (id)
);

CREATE SEQUENCE item_images_seq;
CREATE TABLE item_images (
	id bigint NOT NULL DEFAULT NEXTVAL ('item_images_seq'),
	record_status INT NOT NULL,
	created_on TIMESTAMP(0) NOT NULL,
	item_id bigint NOT NULL,
	image_path varchar(100) NOT NULL,
	PRIMARY KEY (id)
);

CREATE SEQUENCE order_history_seq;
CREATE TABLE order_history (
	id bigint NOT NULL DEFAULT NEXTVAL ('order_history_seq'),
	record_status INT NOT NULL,
	created_on TIMESTAMP(0) NOT NULL,
	user_id bigint NOT NULL,
	user_address_id bigint NOT NULL,
	item_id bigint NOT NULL,
	ordered_qty_type varchar(10) NOT NULL,
	ordered_qty_value DECIMAL(10,2) NOT NULL,
	committed_delivery_time TIMESTAMP(0) NOT NULL,
	order_status varchar(1) NOT NULL,
	PRIMARY KEY (id)
);

ALTER TABLE user_address ADD CONSTRAINT user_address_fk0 FOREIGN KEY (user_id) REFERENCES mstr_user(id);
ALTER TABLE mstr_item ADD CONSTRAINT mstr_item_fk0 FOREIGN KEY (gst_id) REFERENCES mstr_gst(id);
ALTER TABLE mstr_item ADD CONSTRAINT mstr_item_fk1 FOREIGN KEY (item_type_id) REFERENCES mstr_item_type(id);
ALTER TABLE item_images ADD CONSTRAINT item_images_fk0 FOREIGN KEY (item_id) REFERENCES mstr_item(id);
ALTER TABLE order_history ADD CONSTRAINT order_history_fk0 FOREIGN KEY (user_id) REFERENCES mstr_user(id);
ALTER TABLE order_history ADD CONSTRAINT order_history_fk1 FOREIGN KEY (user_address_id) REFERENCES user_address(id);
ALTER TABLE order_history ADD CONSTRAINT order_history_fk2 FOREIGN KEY (item_id) REFERENCES mstr_item(id);