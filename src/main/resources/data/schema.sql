-- Users Table (created first to allow Foods to reference it)
CREATE TABLE IF NOT EXISTS Users (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    rating FLOAT DEFAULT 0,
    ratings_count INTEGER DEFAULT 0,
    dietary_restrictions TEXT,
    current_food_id INTEGER DEFAULT 0 -- No foreign key yet; we add it later
);

-- Foods Table (created next, referencing Users)
CREATE TABLE IF NOT EXISTS Foods (
    food_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    quantity INTEGER NOT NULL,
    ingredients TEXT,
    dietary_restrictions TEXT,
    cuisine TEXT,
    owner_id INTEGER NOT NULL,
    image_path TEXT,
    FOREIGN KEY (owner_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

-- Alter Users Table to add the current_food_id foreign key
PRAGMA foreign_keys = OFF;
ALTER TABLE Users RENAME TO Users_old;

CREATE TABLE IF NOT EXISTS Users (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    rating FLOAT DEFAULT 0,
    ratings_count INTEGER DEFAULT 0,
    dietary_restrictions TEXT,
    current_food_id INTEGER DEFAULT 0,
    FOREIGN KEY (current_food_id) REFERENCES Foods(food_id) ON DELETE SET DEFAULT
);

INSERT INTO Users (user_id, name, email, password, rating, ratings_count, dietary_restrictions, current_food_id)
SELECT user_id, name, email, password, rating, ratings_count, dietary_restrictions, current_food_id
FROM Users_old;

DROP TABLE Users_old;
PRAGMA foreign_keys = ON;

-- Swipes Table (created last)
CREATE TABLE IF NOT EXISTS Swipes (
    swipe_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    food_id INTEGER NOT NULL,
    is_right_swipe BOOLEAN NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (food_id) REFERENCES Foods(food_id) ON DELETE CASCADE
);