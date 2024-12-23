CREATE TABLE AUTHOR (
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	NAMEAUTHOR VARCHAR(100) NOT NULL
);

CREATE TABLE CATEGORY (
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	NAMECATEGORY VARCHAR(100) NOT NULL
);

CREATE TABLE BOOK (
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	TITLE VARCHAR(100) NOT NULL,
	PUBLISHER VARCHAR(100) NOT NULL,
	AUTHOR_ID INT NOT NULL,
	CATEGORY_ID INT NOT NULL,
	FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR(ID),
	FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID)
);
