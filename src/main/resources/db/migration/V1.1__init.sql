CREATE TABLE IF NOT EXISTS book
(
    id BIGSERIAL PRIMARY KEY ,
    uuid UUID NOT NULL ,
    title VARCHAR (255) NOT NULL ,
    sub_title VARCHAR (255),
    currency VARCHAR (6) not null,
    amount INTEGER NOT NULL ,
    description TEXT,
    isbn VARCHAR NOT NULL UNIQUE ,
    user_id VARCHAR NOT NULL
);