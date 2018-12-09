-- Artists
CREATE TABLE `artists`
(
  `id`   INT AUTO_INCREMENT NOT NULL UNIQUE,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8mb4;

-- Albums
CREATE TABLE `albums`
(
  `albumId` INT AUTO_INCREMENT NOT NULL UNIQUE COMMENT 'albumin tunnus id',
  `name`    VARCHAR(100) DEFAULT NULL COMMENT 'albumin nimi',
  `artist`  VARCHAR(100) DEFAULT NULL COMMENT 'artisti, jolle albumi kuuluu',
  PRIMARY KEY (`albumId`),
  KEY (`name`),
  FOREIGN KEY (`artist`) REFERENCES artists (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8mb4;

-- Tracks
CREATE TABLE `tracks`
(
  `trackId` INT          NOT NULL UNIQUE,
  `track`   VARCHAR(100) NOT NULL COMMENT 'biisin nimi',
  `artist`  VARCHAR(100) NOT NULL COMMENT 'artisti, jolle biisi kuuluu',
  `album`   VARCHAR(100) COMMENT 'albumi(jos on), jolle biisi kuuluu',
  PRIMARY KEY (`track`),
  FOREIGN KEY (`artist`) REFERENCES artists (`name`),
  FOREIGN KEY (`album`) REFERENCES albums (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8mb4;