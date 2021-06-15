--liquibase formatted sql

--changeset prakash.patwa:1.0.0
CREATE TABLE activity_files (
  id        UUID                     NOT NULL,
  name      VARCHAR(255)             NOT NULL,
  extension VARCHAR(8)               NOT NULL,
  size      INT8                     NOT NULL,
  status    VARCHAR(32)              NOT NULL,
  created   TIMESTAMP DEFAULT now()  NOT NULL,
  updated   TIMESTAMP DEFAULT now(),
  PRIMARY KEY (id)
);

--rollback DROP TABLE IF EXISTS statement_files CASCADE;