CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    phone_number TEXT NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS sessions (
    id BIGSERIAL PRIMARY KEY,
    date_time TIMESTAMP NOT NULL,
    ip TEXT NOT NULL,
    user_id BIGINT,
    CONSTRAINT users
        FOREIGN KEY (user_id)
            REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS images (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    uuid UUID,
    name TEXT,
    mime TEXT,
    size BIGINT,
    CONSTRAINT users
        FOREIGN KEY (user_id)
            REFERENCES users(id)
);