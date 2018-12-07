CREATE TABLE `ARTISTS`
(
  `id`   INT primary key NOT NULL,
  `name` VARCHAR(100)    NOT NULL UNIQUE
);

CREATE TABLE `ALBUMS`
(
  `albumId` INT AUTO_INCREMENT primary key NOT NULL,
  `name`    VARCHAR(100)                   NOT NULL COMMENT 'albumin nimi'
);