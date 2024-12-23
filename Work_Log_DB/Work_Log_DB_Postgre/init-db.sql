CREATE TABLE notice (
        id SERIAL PRIMARY KEY,
        message VARCHAR(255) NOT NULL,
        type VARCHAR(50) NOT NULL,
        processed BOOLEAN DEFAULT FALSE
);