

INSERT INTO CheckedOutBooks (UserCardNumber, BookNumber)
VALUES ((SELECT LibraryUser.UserCardNumber FROM LibraryUser WHERE FirstName = 'Gregory'), (SELECT Books.ID FROM Books WHERE ID = 2));

SELECT LibraryUser.FirstName AS 'User', Books.Title as 'Book',
       (SELECT COUNT(*)
        FROM CheckedOutBooks
        WHERE CheckedOutBooks.UserCardNumber = LibraryUser.UserCardNumber) AS 'Number Checked Out'
FROM LibraryUser
         JOIN CheckedOutBooks ON LibraryUser.UserCardNumber = CheckedOutBooks.UserCardNumber
         JOIN Books ON CheckedOutBooks.BookNumber = Books.ID;

SELECT * FROM CheckedOutBooks
