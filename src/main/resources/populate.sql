CREATE TABLE article
(
  id bigserial NOT NULL,
  header character varying(255),
  text character varying(2048),
  page character varying(255),
  CONSTRAINT "article_PK" PRIMARY KEY (id),
  CONSTRAINT "article_header_UK" UNIQUE (header)
);
CREATE TABLE settings
(
  key character varying(255) NOT NULL,
  value character varying(255),
  description character varying(2048),
  CONSTRAINT "settings_PK" PRIMARY KEY (key)

);
CREATE TABLE image
(
  image character varying(50) NOT NULL,
  header character varying(255),
  description character varying(2048),
  tag character varying(50) NOT NULL,
  md5 character varying(50) NOT NULL,
  md5thump character varying(50) NOT NULL,
  date_add timestamp,
  in_club number(1),
  CONSTRAINT "image_PK" PRIMARY KEY (image, tag)

);



insert into article(id, header, text, page) values (1, 'тестовая статья', 'полное содержание тестовой статьи', 'about_minibull.html');
insert into settings(key, value) values ('image-data', '/tmp');


