-- Artists
DROP TABLE IF EXISTS `artists`;
CREATE TABLE `artists`
(
  `id`       INT AUTO_INCREMENT NOT NULL,
  `name`     VARCHAR(100)       NOT NULL,
  `artistId` INT,
  PRIMARY KEY (`id`),
  KEY (`artistId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8mb4;

-- Albums
DROP TABLE IF EXISTS `albums`;
CREATE TABLE `albums`
(
  `albumId`  INT AUTO_INCREMENT NOT NULL COMMENT 'albumin tunnus id',
  `name`     VARCHAR(100)       NOT NULL COMMENT 'albumin nimi',
  `artistId` INT,
  PRIMARY KEY (`albumId`),
  KEY (`name`),
  KEY (`artistId`),
  CONSTRAINT `albums_ibfk_1` FOREIGN KEY (`artistId`) REFERENCES `artists` (`artistId`) ON DELETE SET NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8mb4;

-- Tracks
DROP TABLE IF EXISTS `tracks`;
CREATE TABLE `tracks`
(
  `trackId`  INT AUTO_INCREMENT NOT NULL UNIQUE,
  `track`    VARCHAR(100)       NOT NULL COMMENT 'biisin nimi',
  `artistId` INT,
  `artist`   VARCHAR(100) COMMENT 'artisti, jolle biisi kuuluu',
  `albumId`  INT COMMENT 'albumin tunnus id',
  `album`    VARCHAR(100) COMMENT 'albumi(jos on), jolle biisi kuuluu',
  PRIMARY KEY (`trackId`),
  CONSTRAINT `tracks_ibfk_1` FOREIGN KEY (`artistId`) REFERENCES `artists` (`id`) ON DELETE SET NULL,
  CONSTRAINT `tracks_ibfk_2` FOREIGN KEY (`albumId`) REFERENCES `albums` (`albumId`) ON DELETE SET NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8mb4;