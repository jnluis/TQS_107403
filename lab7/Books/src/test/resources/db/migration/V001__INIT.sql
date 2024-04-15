CREATE TABLE book
(

    id        SERIAL         NOT NULL PRIMARY KEY,
    title     VARCHAR(255)   NOT NULL,
    author    VARCHAR(255)   NOT NULL,
    isbn      VARCHAR(255)   NOT NULL,
    publisher VARCHAR(255)   NOT NULL,
    year      INT            NOT NULL,
    price     DECIMAL(10, 2) NOT NULL
);

INSERT INTO book (id,title,author, isbn, publisher, year, price) VALUES (1, 'The Catcher in the Rye', 'J.D. Salinger', '0316769487', 'Little, Brown and Company', 1951, 7.99);
INSERT INTO book (id,title,author, isbn, publisher, year, price) VALUES (2, 'To Kill a Mockingbird', 'Harper Lee', '0060935464', 'Harper Perennial Modern Classics', 1960, 6.99);
INSERT INTO book (id,title,author, isbn, publisher, year, price) VALUES (3, '1984', 'George Orwell', '0451524934', 'Signet Classic', 1949, 9.99);
INSERT INTO book (id,title,author, isbn, publisher, year, price) VALUES (4, 'The Great Gatsby', 'F. Scott Fitzgerald', '0743273567', 'Scribner', 1925, 6.99);
INSERT INTO book (id,title,author, isbn, publisher, year, price) VALUES (5, 'Pride and Prejudice', 'Jane Austen', '0486280485', 'Dover Publications', 1813, 7.99);


