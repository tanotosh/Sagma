-- Users Table
CREATE TABLE IF NOT EXISTS Users (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    rating FLOAT DEFAULT 0,
    ratings_count INTEGER DEFAULT 0,
    dietary_restrictions TEXT
    current_food_id INTEGER DEFAULT 0,
    FOREIGN KEY (current_food_id) REFERENCES Foods(food_id)
);

-- Foods Table
CREATE TABLE IF NOT EXISTS Foods (
    food_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    quantity INTEGER NOT NULL,
    ingredients TEXT,
    dietary_restrictions TEXT,
    cuisine TEXT,
    owner_id INTEGER,
    image_path TEXT,
    FOREIGN KEY (owner_id) REFERENCES Users(user_id)
);

-- Swipes Table
CREATE TABLE IF NOT EXISTS Swipes (
    swipe_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    food_id INTEGER NOT NULL,
    is_right_swipe BOOLEAN NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (food_id) REFERENCES Foods(food_id)
);

