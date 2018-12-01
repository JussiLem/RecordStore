CREATE TABLE `ALBUMS`
(
  `albumId` INT AUTO_INCREMENT primary key NOT NULL,
  `name`    VARCHAR(100)                   NOT NULL UNIQUE COMMENT 'albumin nimi',
  FOREIGN KEY (`albumId`) REFERENCES ARTISTS (ArtistId)
);