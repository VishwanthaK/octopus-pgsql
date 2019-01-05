CREATE TABLE mstr_order (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	record_status INT NOT NULL,
	created_on DATETIME NOT NULL,

	user_id bigint(20) NOT NULL,
	user_address_id bigint(20) NOT NULL,

	order_number varchar(100) NOT NULL,
	item_total DECIMAL(10,2) NOT NULL,
	gst_total DECIMAL(10,2) NOT NULL,
	grand_total DECIMAL(10,2) NOT NULL,
	delivery_on DATETIME DEFAULT NULL,

	is_delivered bit(1) NOT NULL DEFAULT b'0',
	is_modified bit(1) NOT NULL DEFAULT b'0',
    is_cancelled bit(1) NOT NULL DEFAULT b'0',

	PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE order_details (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	record_status INT NOT NULL,
	created_on DATETIME NOT NULL,

	order_id bigint(20) NOT NULL,
	item_id bigint(20) NOT NULL,
	gst_id bigint(20) NOT NULL,

    qty DECIMAL(10,2) NOT NULL,
	rate DECIMAL(10,2) NOT NULL,
	item_total DECIMAL(10,2) NOT NULL,
	gst_total DECIMAL(10,2) NOT NULL,

    is_cancelled bit(1) NOT NULL DEFAULT b'0',

	PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


ALTER TABLE mstr_order ADD CONSTRAINT mstr_order_fk0 FOREIGN KEY (user_id) REFERENCES mstr_user(id);
ALTER TABLE mstr_order ADD CONSTRAINT mstr_order_fk1 FOREIGN KEY (user_address_id) REFERENCES user_address(id);

ALTER TABLE order_details ADD CONSTRAINT order_details_fk0 FOREIGN KEY (order_id) REFERENCES mstr_order(id);
ALTER TABLE order_details ADD CONSTRAINT order_details_fk1 FOREIGN KEY (item_id) REFERENCES mstr_item(id);
ALTER TABLE order_details ADD CONSTRAINT order_details_fk2 FOREIGN KEY (gst_id) REFERENCES mstr_gst(id);

ALTER TABLE `mstr_order` ADD UNIQUE INDEX `order_number_UNIQUE` (`order_number` ASC);