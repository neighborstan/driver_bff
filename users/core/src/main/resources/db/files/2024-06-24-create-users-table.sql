--liquibase formatted sql
--changeset konstantin:2024-06-24-create-users-table

CREATE TABLE users (
    user_uid TEXT PRIMARY KEY,
    user_full_name TEXT NOT NULL,
    role TEXT NOT NULL,
    phone_number TEXT NOT NULL,
    access BOOLEAN NOT NULL,
    deleted BOOLEAN NOT NULL,
    system_source TEXT NOT NULL
);
