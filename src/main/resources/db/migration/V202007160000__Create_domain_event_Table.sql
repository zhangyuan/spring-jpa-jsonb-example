CREATE TABLE domain_event (
	id varchar(64) NOT NULL PRIMARY KEY,
	event_type varchar(32),
	payload jsonb NOT NULL,
	received_time timestamp NOT NULL
);