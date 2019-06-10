ALTER TABLE mstr_order
    ADD delivery_scheduled_on DATETIME DEFAULT NULL AFTER grand_total;

UPDATE mstr_order SET delivery_scheduled_on = DATE_ADD(created_on, INTERVAL 1 DAY)
WHERE id > 0;

ALTER TABLE mstr_order MODIFY COLUMN delivery_scheduled_on DATETIME NOT NULL;