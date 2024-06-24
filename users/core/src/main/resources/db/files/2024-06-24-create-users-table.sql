--liquibase formatted sql
--changeset konstantin:2024-06-24-create-users-table

CREATE TABLE users (
    user_uid VARCHAR(255) PRIMARY KEY,
    user_full_name VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    access BOOLEAN NOT NULL,
    deleted BOOLEAN NOT NULL,
    system_source VARCHAR(255) NOT NULL
);
