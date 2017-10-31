

USE comscroller

begin
  CREATE TABLE Users
  (
    id INT NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(10) NOT NULL,
    nickname VARCHAR(50) NOT NULL UNIQUE,
    complited_games VARCHAR(6000),
    banned BIT DEFAULT 0 NOT NULL,
    PRIMARY KEY(id)
  )
  CREATE TABLE Games
  (
    id INT NOT NULL UNIQUE,
    name VARCHAR(200) NOT NULL,
    ownerid INT DEFAULT 1,
    ispublic BIT DEFAULT 0 NOT NULL,
    approved BIT DEFAULT 0 NOT NULL,
    finished BIT DEFAULT 0 NOT NULL,
    startscene INT NOT NULL,
    maincharacter INT,
    endscene1 INT NOT NULL,
    endscene2 INT,
    endscene3 INT,
    lastcheckpoint INT,
    PRIMARY KEY(id)
  )
  CREATE TABLE Scenes
  (
    id INT NOT NULL UNIQUE,
    gameid INT NOT NULL,
    type INT NOT NULL,
    maintext VARCHAR(2000),
    picture1 VARCHAR(200),
    picture2 VARCHAR(200),
    action1 INT,
    actiontext1 VARCHAR(2000),
    action2 INT,
    actiontext2 VARCHAR(2000),
    action3 INT,
    actiontext3 VARCHAR(2000),
    characterid INT,
    ischeckpoint BIT,
    PRIMARY KEY(id)
  )
  CREATE TABLE Characters
  (
    id INT NOT NULL UNIQUE,
    name VARCHAR(100),
    hp BIT,
    mp INT,
    str INT,
    dex INT,
    int INT,
    luck INT,
    items VARCHAR(6000),
    type INT NOT NULL,
    picture VARCHAR(200),
    PRIMARY KEY(id)
  )



-- Create FKs
ALTER TABLE Games
    ADD    FOREIGN KEY (ownerid)
    REFERENCES Users(id)
;
    
ALTER TABLE Scenes
    ADD    FOREIGN KEY (gameid)
    REFERENCES Games(id)
;
    
ALTER TABLE Games
    ADD    FOREIGN KEY (startscene)
    REFERENCES Scenes(id)
;
    
ALTER TABLE Scenes
    ADD    FOREIGN KEY (action1)
    REFERENCES Scenes(id)
;
    
ALTER TABLE Scenes
    ADD    FOREIGN KEY (action2)
    REFERENCES Scenes(id)
;
    
ALTER TABLE Scenes
    ADD    FOREIGN KEY (action3)
    REFERENCES Scenes(id)
;
    
ALTER TABLE Games
    ADD    FOREIGN KEY (endscene1)
    REFERENCES Scenes(id)
;
    
ALTER TABLE Games
    ADD    FOREIGN KEY (endscene2)
    REFERENCES Scenes(id)
;
    
ALTER TABLE Games
    ADD    FOREIGN KEY (endscene3)
    REFERENCES Scenes(id)
;
    
ALTER TABLE Games
    ADD    FOREIGN KEY (maincharacter)
    REFERENCES Characters(id)
;
    
ALTER TABLE Scenes
    ADD    FOREIGN KEY (characterid)
    REFERENCES Characters(id)
;
    
ALTER TABLE Games
    ADD    FOREIGN KEY (lastcheckpoint)
    REFERENCES Scenes(id)
;
    
end
-- Create Indexes

