CREATE TABLE `ARTISTS`
(
  `artistId` INT AUTO_INCREMENT primary key NOT NULL,
  `name`     VARCHAR(100)                   NOT NULL UNIQUE COMMENT 'artistin nimi'
);