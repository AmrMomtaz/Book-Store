USE book_store;

# Creating constraints
ALTER TABLE users ADD CONSTRAINT Check_User_Type CHECK (type IN ('manager','customer'));
ALTER TABLE books ADD CONSTRAINT Check_Book_Category CHECK(category IN ('Science','Art','Religion','History','Geography'));