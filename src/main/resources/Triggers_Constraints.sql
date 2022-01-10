USE book_store;

ALTER TABLE users ADD CONSTRAINT Check_User_Type CHECK (type IN ('manager','customer'));
ALTER TABLE books ADD CONSTRAINT Check_Book_Category CHECK(category IN ('Science','Art','Religion','History','Geography'));

DELIMITER $$
CREATE TRIGGER Place_Order_Book BEFORE UPDATE ON books
FOR EACH ROW BEGIN
	SET @availble_books = NEW.copies;
	IF NEW.copies < OLD.threshold THEN
		UPDATE book SET copies = @availble_books+OLD.threshold WHERE ISBN = OLD.ISBN; 
	END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER Place_Order_Book_Negative BEFORE DELETE ON shopping_cart
FOR EACH ROW BEGIN
	SET @book_copies = (SELECT copies FROM book AS b WHERE OLD.ISBN = b.ISBN);
	IF OLD.count < @book_copies THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'The number of copies availble is less than order itself';
	END IF;
END$$
DELIMITER ;

DELIMITER //
CREATE TRIGGER Insertion_Trigger_Books BEFORE INSERT ON books
FOR EACH ROW
BEGIN
	IF(NEW.threshold < 0 OR NEW.copies < 0) THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Wrong insertion of the book';
	END IF;
END //
DELIMITER ;

CREATE EVENT IF NOT EXISTS Clear_Order_More_Than_Three_Months
ON SCHEDULE
EVERY 12 HOUR
DO
DELETE FROM books
WHERE date < DATE_SUB(NOW(), INTERVAL 90 DAY);