-- Inserting authors
INSERT INTO author (name) VALUES ('J.K. Rowling');
INSERT INTO author (name) VALUES ('George R.R. Martin');
INSERT INTO author (name) VALUES ('Isaac Asimov');
INSERT INTO author (name) VALUES ('Stephen King');
INSERT INTO author (name) VALUES ('Margaret Atwood');
INSERT INTO author (name) VALUES ('H.G. Wells');
INSERT INTO author (name) VALUES ('J.R.R. Tolkien');

-- Inserting books
INSERT INTO book (title, genre, is_available, amount, author_id) VALUES ('Harry Potter and the Sorcerer''s Stone', 'Fantasy', true, 5, 1);
INSERT INTO book (title, genre, is_available, amount, author_id) VALUES ('A Game of Thrones', 'Fantasy', true, 3, 2);
INSERT INTO book (title, genre, is_available, amount, author_id) VALUES ('Foundation', 'Sci-Fi', true, 4, 3);
INSERT INTO book (title, genre, is_available, amount, author_id) VALUES ('I, Robot', 'Sci-Fi', true, 6, 3);
INSERT INTO book (title, genre, is_available, amount, author_id) VALUES ('It', 'Horror', true, 2, 4);
INSERT INTO book (title, genre, is_available, amount, author_id) VALUES ('The Shining', 'Horror', true, 3, 4);
INSERT INTO book (title, genre, is_available, amount, author_id) VALUES ('The Handmaid''s Tale', 'Sci-Fi', true, 5, 5);
INSERT INTO book (title, genre, is_available, amount, author_id) VALUES ('Oryx and Crake', 'Sci-Fi', true, 4, 5);
INSERT INTO book (title, genre, is_available, amount, author_id) VALUES ('The War of the Worlds', 'Sci-Fi', true, 3, 6);
INSERT INTO book (title, genre, is_available, amount, author_id) VALUES ('The Time Machine', 'Sci-Fi', true, 4, 6);
INSERT INTO book (title, genre, is_available, amount, author_id) VALUES ('The Lord of the Rings', 'Fantasy', true, 3, 7);
INSERT INTO book (title, genre, is_available, amount, author_id) VALUES ('The Hobbit', 'Fantasy', true, 5, 7);

-- Inserting readers
INSERT INTO reader (card_number, first_name, last_name) VALUES (1000, 'Jan', 'Kowalski');
INSERT INTO reader (card_number, first_name, last_name) VALUES (1001, 'Anna', 'Nowak');

-- Associating books and readers
INSERT INTO reader_books (reader_id, book_id) VALUES (1, 1);
INSERT INTO reader_books (reader_id, book_id) VALUES (2, 2);
