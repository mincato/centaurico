
CREATE TABLE tipo_materiales
(
  id bigserial UNIQUE,
  codigo character varying(255) NOT NULL,
  valor character varying(255) NOT NULL,
  CONSTRAINT tipo_materiales_pkey PRIMARY KEY (id)
);

CREATE TABLE users
(
  id bigserial UNIQUE,
  username character varying(255) NOT NULL,
  firstName character varying(255) NOT NULL,
  middleName character varying(255) NOT NULL,
  lastName character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (id)
);