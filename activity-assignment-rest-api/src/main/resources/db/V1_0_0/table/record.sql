--liquibase formatted sql

--changeset prakash.patwa:1.0.0
CREATE TABLE record (
  record_id                 UUID                    NOT NULL,
  record_def                VARCHAR(32),
  time                      TIMESTAMP DEFAULT now() NOT NULL,
  distance                  NUMERIC,
  power                     NUMERIC,
  cadence                   NUMERIC,
  created                   TIMESTAMP DEFAULT now() NOT NULL,
  updated                   TIMESTAMP DEFAULT now(),
  deleted                   TIMESTAMP,
  PRIMARY KEY (record_id)
);

--rollback DROP TABLE IF EXISTS statements CASCADE;