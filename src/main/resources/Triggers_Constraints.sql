USE book_store;
# Creating constraints
ALTER TABLE users ADD CONSTRAINT Check_User_Type CHECK (type IN ('manager','customer'));