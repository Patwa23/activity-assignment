--liquibase formatted sql

--changeset prakash.patwa:1.0.0
CREATE TABLE activity (
  activity_id               UUID                    NOT NULL,
  activity_def              VARCHAR(32),
  name                      VARCHAR(32),
  type                      VARCHAR(32),
  start_time                TIMESTAMP DEFAULT now() NOT NULL,
  created                   TIMESTAMP DEFAULT now() NOT NULL,
  updated                   TIMESTAMP DEFAULT now(),
  deleted                   TIMESTAMP,
  PRIMARY KEY (activity_id)
);

--rollback DROP TABLE IF EXISTS statements CASCADE;