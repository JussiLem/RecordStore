-- Artists
CREATE TABLE `ARTISTS`
(
  `id`   INT primary key NOT NULL,
  `name` VARCHAR(100)    NOT NULL UNIQUE
);

-- Albums
CREATE TABLE `ALBUMS`
(
  `albumId` INT AUTO_INCREMENT primary key NOT NULL COMMENT 'albumin tunnus id',
  `name`    VARCHAR(100)                   NOT NULL COMMENT 'albumin nimi',
  `artist`  VARCHAR(100)                   NOT NULL COMMENT 'artisti, jolle albumi kuuluu',
  FOREIGN KEY (`artist`) REFERENCES ARTISTS ("id")
);

-- Tracks
CREATE TABLE `TRACKS`
(
  `trackId` INT AUTO_INCREMENT primary key NOT NULL,
  `artist`  VARCHAR(100)                   NOT NULL,
  `album`   VARCHAR(100),
  `track`   VARCHAR(100)                   NOT NULL,
  FOREIGN KEY ("artist") REFERENCES ARTISTS ("id"),
  FOREIGN KEY ("album") REFERENCES ALBUMS ("albumId")
)