-- Inserting authors
INSERT INTO author (name) VALUES ('J.K. Rowling');
INSERT INTO author (name) VALUES ('George R.R. Martin');
INSERT INTO author (name) VALUES ('Isaac Asimov');
INSERT INTO author (name) VALUES ('Stephen King');
INSERT INTO author (name) VALUES ('Margaret Atwood');
INSERT INTO author (name) VALUES ('H.G. Wells');
INSERT INTO author (name) VALUES ('J.R.R. Tolkien');

-- Inserting books
INSERT INTO book (title, genre, is_available, amount, author_id, cover_path) VALUES ('Harry Potter and the Sorcerer''s Stone', 'Fantasy', true, 5, 1, '/images/covers/Harry_Potter.jpg');
INSERT INTO book (title, genre, is_available, amount, author_id, cover_path) VALUES ('A Game of Thrones', 'Fantasy', true, 3, 2, '/images/covers/Gra_o_Tron.jpg');
INSERT INTO book (title, genre, is_available, amount, author_id, cover_path) VALUES ('Foundation', 'Sci-Fi', true, 4, 3, '/images/covers/Foundation.jpg' );
INSERT INTO book (title, genre, is_available, amount, author_id, cover_path) VALUES ('I, Robot', 'Sci-Fi', true, 6, 3, '/images/covers/Robot.jpg');
INSERT INTO book (title, genre, is_available, amount, author_id, cover_path) VALUES ('It', 'Horror', true, 2, 4, '/images/covers/To.jpg');
INSERT INTO book (title, genre, is_available, amount, author_id, cover_path) VALUES ('The Shining', 'Horror', true, 3, 4, '/images/covers/The_Shining.jpg');
INSERT INTO book (title, genre, is_available, amount, author_id, cover_path) VALUES ('The Handmaid''s Tale', 'Sci-Fi', true, 5, 5, '/images/covers/The_Handmaids_Tale.jpg');
INSERT INTO book (title, genre, is_available, amount, author_id, cover_path) VALUES ('Oryx and Crake', 'Sci-Fi', true, 4, 5, '/images/covers/Oryx_and_Crake.jpg');
INSERT INTO book (title, genre, is_available, amount, author_id, cover_path) VALUES ('The War of the Worlds', 'Sci-Fi', true, 3, 6, '/images/covers/The_War_of_the_Worlds.jpg');
INSERT INTO book (title, genre, is_available, amount, author_id, cover_path) VALUES ('The Time Machine', 'Sci-Fi', true, 4, 6, '/images/covers/The_Time_Machine.jpg');
INSERT INTO book (title, genre, is_available, amount, author_id, cover_path) VALUES ('The Lord of the Rings', 'Fantasy', true, 3, 7, '/images/covers/Wladca_pierscieni.jpg');
INSERT INTO book (title, genre, is_available, amount, author_id, cover_path) VALUES ('The Hobbit', 'Fantasy', true, 5, 7, '/images/covers/hobbit.jpg');

-- Inserting readers
INSERT INTO reader (card_number, first_name, last_name) VALUES (1000, 'Jan', 'Kowalski');
INSERT INTO reader (card_number, first_name, last_name) VALUES (1001, 'Anna', 'Nowak');

-- Associating books and readers
INSERT INTO reader_books (reader_id, book_id) VALUES (1, 1);
INSERT INTO reader_books (reader_id, book_id) VALUES (2, 2);
