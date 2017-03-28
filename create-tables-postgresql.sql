DROP TABLE IF EXISTS public.laptops;

CREATE TABLE public.laptops
(
  model character varying NOT NULL,
  make character varying NOT NULL,
  screen numeric(3,1),
  processor character varying,
  memory integer,
  image character varying,
  amount integer,
  price numeric,
  CONSTRAINT pk_model PRIMARY KEY (model)
);

DROP TABLE IF EXISTS public.roles;

CREATE TABLE public.roles
(
  role character varying NOT NULL,
  CONSTRAINT pk_role PRIMARY KEY (role)
);

DROP TABLE IF EXISTS public.users;

CREATE TABLE public.users
(
  login character varying NOT NULL,
  password character varying NOT NULL,
  role character varying NOT NULL,
  CONSTRAINT pk_login PRIMARY KEY (login),
  CONSTRAINT fk_role FOREIGN KEY (role)
      REFERENCES public.roles (role) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO roles (role) VALUES
  ('admin'), ('user');

INSERT INTO users (login, password, role)
VALUES
  ('admin','admin','admin'),
  ('Bob','dilan','user'),
  ('Dik','4321','user'),
  ('Lolo','1234','user'),
  ('newAdmin','admin','admin');


INSERT INTO laptops (model, make, screen, processor, memory, image, amount, price)
VALUES ('250 G5 / W4M56EA','HP',15.6,'Celeron',2,NULL,17,6500),
  ('GL552VW / DM141T','Asus',15.6,'Core i5',4,NULL,4,23700),
  ('GL552VW / GL552VW-DM350','Asus',15.6,'Core i5',4,NULL,7,21700),
  ('Inspiron 15 5559 / I55545DDL-T2B','Dell',15.6,'Core i5',4,NULL,5,14600),
  ('MacBook Pro 15 / MLH32','Apple',15.4,'Core i7',16,NULL,10,82700),
  ('MacBook Pro 15 / MLW72','Apple',15.4,'Core i7',7,NULL,6,58450),
  ('MacBookPro','Apple',15.0,'Core i7',12,NULL,7,77777),
  ('MackBook Air 13 / MMGF2','Apple',13.3,'Core i5',8,NULL,21,27000),
  ('ProBook 440 G4 / Y7Z75EA','HP',14.0,'Core i3',4,NULL,11,15500),
  ('ProBook 450 G3 / P4P34EA','HP',15.6,'Core i7',4,NULL,14,23000),
  ('Vostro 3568 / N030VNEMEA02UBU','Dell',15.6,'Core i3',4,NULL,7,11399);