CREATE TABLE COLLATERAL_MODEL
(
	collateral_type VARCHAR(31) NOT NULL,
	id IDENTITY NOT NULL
		CONSTRAINT collateral_model_pkey
			PRIMARY KEY
);

CREATE TABLE CAR
(
	id IDENTITY PRIMARY KEY  NOT NULL
	CONSTRAINT car_pkey
		CONSTRAINT collateral_model_fk
			REFERENCES collateral_model,
	brand VARCHAR(255),
	model VARCHAR(255),
	power DOUBLE PRECISION,
	year_of_issue SMALLINT
);

CREATE TABLE AIRPLANE
(
	id BIGINT NOT NULL
		CONSTRAINT plane_pkey
			PRIMARY KEY
		CONSTRAINT collateral_model_fk
			REFERENCES collateral_model,
	brand VARCHAR(255),
	fuel_capacity INTEGER,
	manufacturer VARCHAR(255),
	model VARCHAR(255),
	year_of_issue SMALLINT
);

CREATE TABLE ASSESSED_VALUE
(
	id IDENTITY not null
		CONSTRAINT assessed_value_pkey
			PRIMARY KEY,
	date DATE ,
	value NUMERIC (19,2)
);

CREATE TABLE COLLATERAL_MODEL_VALUES
(
	collateral_model_id BIGINT NOT NULL
		CONSTRAINT collateral_model_fk
			REFERENCES collateral_model,
	values_id BIGINT NOT NULL
		CONSTRAINT values_uk
			UNIQUE
		CONSTRAINT values_fk
			REFERENCES assessed_value
);
