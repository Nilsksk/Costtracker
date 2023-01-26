
CREATE TABLE IF NOT EXISTS category
(id IDENTITY NOT NULL,
name varchar(50) NOT NULL);

CREATE TABLE IF NOT EXISTS company
(id IDENTITY NOT NULL,
name varchar(50) NOT NULL,
location varchar(50));

CREATE TABLE IF NOT EXISTS purchase
(id IDENTITY NOT NULL,
name varchar(50) NOT NULL,
description varchar(50),
date date NOT NULL,
category int,
company int,
price numeric(8,2) NOT NULL,
CONSTRAINT FK_CATEGORY FOREIGN KEY (category) references category(id) ON DELETE SET NULL,
CONSTRAINT FK_COMPANY FOREIGN KEY (company) references company(id) ON DELETE SET NULL);