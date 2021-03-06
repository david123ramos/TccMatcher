CREATE DATABASE IF NOT EXISTS tccmatcher;
use tccmatcher;

DROP TABLE IF EXISTS MOCK_DATA;

DROP TABLE IF EXISTS PREFERENCES;

create table IF NOT EXISTS MOCK_DATA (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(200) NOT NULL,
    last_name VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL,
    gender VARCHAR(100) NOT NULL,
    psw VARCHAR(100) NOT NULL,
    institution VARCHAR(200),
    created_at datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    updated_at timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS PREFERENCES(
    id INT not null  auto_increment,
    description VARCHAR(200) NOT NULL,
    id_user INT,
    created_at datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    updated_at timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),

    PRIMARY KEY (id),
    CONSTRAINT fk_personid FOREIGN KEY (id_user) References MOCK_DATA(id)
);


CREATE TABLE IF NOT EXISTS TCC(
    id INT not null  auto_increment,
    title VARCHAR(200) NOT NULL,
    description VARCHAR(200) NOT NULL,
    id_user INT,
    created_at datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    updated_at timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),

    PRIMARY KEY (id),
    CONSTRAINT fk_personid_tcc FOREIGN KEY (id_user) References MOCK_DATA(id)
);

CREATE TABLE IF NOT EXISTS KEYWORD(
      id INT not null  auto_increment,
      title VARCHAR(200) NOT NULL,
      id_tcc INT,
      created_at datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
      updated_at timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),

      PRIMARY KEY (id),
      CONSTRAINT fk_tccid FOREIGN KEY (id_tcc) References TCC(id)
);


INSERT INTO MOCK_DATA (first_name, last_name, email, gender, psw)
VALUES
('Hephzibah', 'Caulket', 'hcaulket0@over-blog.com', 'Female', 'scXwqut'),
('Christy', 'Amoss', 'camoss1@forbes.com', 'Male', 'Asji8V'),
('Rice', 'Pellatt', 'rpellatt2@deviantart.com', 'Non-binary', 'CCcRjcUeXf8'),
('Mart', 'Dowglass', 'mdowglass3@illinois.edu', 'Polygender', 'veRmkDaLmY8f'),
('Gerti', 'Craik', 'gcraik4@archive.org', 'Bigender', 'gaj89O'),
('Julita', 'Redgewell', 'jredgewell5@cam.ac.uk', 'Genderfluid', 'yjh7yYGuP'),
('Harald', 'Pitchers', 'hpitchers6@mediafire.com', 'Bigender', 'CIJYNL1lI9'),
('Faun', 'Boanas', 'fboanas7@discuz.net', 'Polygender', 'leW8Ha3kXyc'),
('Curry', 'Dadge', 'cdadge8@elegantthemes.com', 'Polygender', 'ZlWgzz9HFHX'),
('Corrinne', 'Oldman', 'coldman9@jiathis.com', 'Genderfluid', 'iJ35ac'),
('Nikolia', 'Tomankiewicz', 'ntomankiewicza@wired.com', 'Genderfluid', 'cYRBVDW9'),
('Casi', 'McPake', 'cmcpakeb@theguardian.com', 'Genderqueer', '0bPUG6'),
('Glynis', 'Drennan', 'gdrennanc@amazon.de', 'Female', 'huuASfMo'),
('Raquel', 'Stubs', 'rstubsd@mozilla.com', 'Non-binary', 'OjxBiOMH4'),
('Case', 'Minney', 'cminneye@domainmarket.com', 'Genderqueer', '1Q9PCI'),
('Wait', 'Oganian', 'woganianf@yellowbook.com', 'Male', 'YuPFkzO4ROO'),
('Shane', 'Pring', 'springg@gov.uk', 'Genderfluid', 'ZB7bKzPM5'),
('Aldous', 'Vockins', 'avockinsh@etsy.com', 'Genderqueer', '0qnSurGCnfz'),
('Lawry', 'Tomkys', 'ltomkysi@latimes.com', 'Female', '8wBPWBldmKeD'),
('Jsandye', 'Detloff', 'jdetloffj@yellowbook.com', 'Non-binary', 'As59gD'),
('Bamby', 'Real', 'brealk@dell.com', 'Agender', 'FjS98kYN'),
('Itch', 'Kondrachenko', 'ikondrachenkol@vkontakte.ru', 'Non-binary', '9TfApx'),
('Arlene', 'Powling', 'apowlingm@census.gov', 'Agender', 'eC9YiYv'),
('Zarla', 'Bellew', 'zbellewn@ihg.com', 'Polygender', '7tsSqW'),
('Ondrea', 'Uccello', 'ouccelloo@mac.com', 'Female', 'XATrfs'),
('Montgomery', 'Keaton', 'mkeatonp@fema.gov', 'Non-binary', 'obZwzmRfwBQv'),
('Estele', 'Quaif', 'equaifq@scribd.com', 'Polygender', 'A087pxb6'),
('Hanni', 'Moxsom', 'hmoxsomr@kickstarter.com', 'Male', 'WHx6UIB2yE'),
('Jennee', 'Roskruge', 'jroskruges@accuweather.com', 'Genderfluid', 'Yby7Aiq5C'),
('Jodie', 'Bonas', 'jbonast@ucoz.ru', 'Bigender', 'vIOxMI6ArW5'),
('Tiphani', 'Redon', 'tredonu@businessinsider.com', 'Bigender', '5XvefqKddArd'),
('Diandra', 'Baskerfield', 'dbaskerfieldv@histats.com', 'Polygender', 'AvmITpxEzm'),
('Margaux', 'Brisland', 'mbrislandw@last.fm', 'Bigender', 'UWEOEt'),
('Dorry', 'Lagadu', 'dlagadux@stanford.edu', 'Non-binary', 'q3S7x6VRe4DK'),
('Gustavus', 'Lennox', 'glennoxy@godaddy.com', 'Bigender', 'O0PdcbXO'),
('Charla', 'Dansie', 'cdansiez@cmu.edu', 'Male', '1lvJxksgJ'),
('Donia', 'Dudliston', 'ddudliston10@nhs.uk', 'Male', 'yTYRNuhi'),
('Wilton', 'Duffit', 'wduffit11@nifty.com', 'Male', 'lzNxbeejJ'),
('Bone', 'Dalloway', 'bdalloway12@cargocollective.com', 'Non-binary', 'stW25SBreX'),
('Benny', 'Doding', 'bdoding13@prlog.org', 'Agender', 'MMur6a'),
('Franklin', 'Harrowsmith', 'fharrowsmith14@github.com', 'Genderfluid', 'n6SQVCi8e'),
('Selma', 'Starrs', 'sstarrs15@wordpress.org', 'Agender', '5IdLOei'),
('Wilbert', 'Borkin', 'wborkin16@usa.gov', 'Agender', 'PKhPBHIyXZIF'),
('Rosemonde', 'Spinnace', 'rspinnace17@taobao.com', 'Female', '2bPiMelsppR3'),
('Vidovic', 'Daltrey', 'vdaltrey18@unesco.org', 'Polygender', 'd2NKzoSC'),
('Spense', 'Kopfer', 'skopfer19@acquirethisname.com', 'Agender', 'upi8wxgYWvw'),
('Dilly', 'Spencer', 'dspencer1a@bbb.org', 'Bigender', 'RGyxb6K'),
('Lucina', 'Soonhouse', 'lsoonhouse1b@bloglovin.com', 'Bigender', '7FjRUKhyEU'),
('Kania', 'Smithers', 'ksmithers1c@arstechnica.com', 'Genderqueer', 'wal4rFnrF'),
('Byrann', 'Tevelov', 'btevelov1d@arstechnica.com', 'Genderfluid', 'UvBPQj'),
('Davon', 'Wheatcroft', 'dwheatcroft1e@globo.com', 'Male', 'U2Ss4G'),
('Sylas', 'Galfour', 'sgalfour1f@sourceforge.net', 'Polygender', 'hEQadOL'),
('Heinrik', 'Dresser', 'hdresser1g@japanpost.jp', 'Female', 'PQURYMIZ2r'),
('Miguela', 'Stiller', 'mstiller1h@sourceforge.net', 'Female', 'm4FntPNzcu4S'),
('Eve', 'Tortoise', 'etortoise1i@ft.com', 'Genderqueer', 'ZqqlBThF'),
('Glenda', 'Hunnicutt', 'ghunnicutt1j@mysql.com', 'Bigender', 'R1i7zXJle'),
('Vera', 'Cheetham', 'vcheetham1k@theglobeandmail.com', 'Female', 'CItCBM'),
('Sofia', 'De Bellis', 'sdebellis1l@cdc.gov', 'Agender', 'CVn149SbC9k'),
('Chen', 'Eisak', 'ceisak1m@moonfruit.com', 'Agender', 'UstnxpL'),
('Eadith', 'Lippini', 'elippini1n@uol.com.br', 'Genderqueer', 'XZOiMCW'),
('Terri', 'Mollett', 'tmollett1o@netlog.com', 'Non-binary', '8QCm8LaJ1i'),
('Ingra', 'Tempest', 'itempest1p@163.com', 'Genderfluid', '7AD354Ca0O'),
('Andy', 'Pavlik', 'apavlik1q@canalblog.com', 'Non-binary', 'io4cb5Opf5'),
('Benton', 'Davidy', 'bdavidy1r@comsenz.com', 'Female', 'Z3P8ZT'),
('Debbi', 'Prettjohn', 'dprettjohn1s@tinyurl.com', 'Agender', 'hC58VQ5YT'),
('Darnall', 'Buckthorp', 'dbuckthorp1t@g.co', 'Genderqueer', 'yKTWgp0jLut'),
('Ilaire', 'Haycox', 'ihaycox1u@usgs.gov', 'Non-binary', 'AiYzByplCW'),
('Patrizia', 'Tutin', 'ptutin1v@amazon.de', 'Female', 'P7z3LOn'),
('Holden', 'Samme', 'hsamme1w@house.gov', 'Non-binary', 'KnWjx86lM'),
('Adelind', 'Lark', 'alark1x@youtube.com', 'Genderqueer', 'L2nRUTigfz'),
('Salomone', 'Alebrooke', 'salebrooke1y@china.com.cn', 'Agender', 'kVBdxDrXb'),
('Lianne', 'Ablitt', 'lablitt1z@google.fr', 'Male', 'BgeDaZ8b'),
('Aimee', 'Manville', 'amanville20@europa.eu', 'Non-binary', 'sKkm85SH'),
('Anatol', 'Jeskin', 'ajeskin21@ihg.com', 'Female', 'a3jjwNh'),
('Harrison', 'Pawle', 'hpawle22@tumblr.com', 'Bigender', 'p8eppAa'),
('Sadella', 'Magill', 'smagill23@washington.edu', 'Polygender', 'PcMKDxfzEc'),
('Abner', 'Ormesher', 'aormesher24@dailymail.co.uk', 'Bigender', 'Ml5hdbJ98A'),
('Rhona', 'Muggleston', 'rmuggleston25@oracle.com', 'Female', 'KlHjRLrvO'),
('Windham', 'McMichan', 'wmcmichan26@hubpages.com', 'Female', 'NvyhSRZtv'),
('Gabbie', 'Boldero', 'gboldero27@freewebs.com', 'Male', 'b0mAh3NgNKx'),
('Ramonda', 'Livings', 'rlivings28@flickr.com', 'Genderqueer', 'EsQxwGMu9ACF'),
('Kasper', 'Evert', 'kevert29@google.ru', 'Bigender', 'lU9obz742F'),
('Winn', 'Shapland', 'wshapland2a@springer.com', 'Non-binary', 'r8n2clZskb8D'),
('Jerry', 'Allman', 'jallman2b@51.la', 'Female', 'gxk93i6R'),
('Lauri', 'Tureville', 'ltureville2c@census.gov', 'Polygender', '2uIpjY'),
('Ketty', 'Le Gassick', 'klegassick2d@sun.com', 'Non-binary', 'dFP2mlPRy'),
('Abbot', 'Stave', 'astave2e@discovery.com', 'Male', 'j7jW69'),
('Rica', 'Liddyard', 'rliddyard2f@joomla.org', 'Bigender', 'jg4tCSk'),
('Casie', 'Manilove', 'cmanilove2g@google.com.au', 'Non-binary', '9jr74j'),
('Oswell', 'Mathie', 'omathie2h@mail.ru', 'Polygender', 'vQa9ev'),
('Daloris', 'Garretson', 'dgarretson2i@globo.com', 'Genderfluid', 'obQCvWCeZsMl'),
('Lynnelle', 'Penright', 'lpenright2j@mozilla.com', 'Female', '1iCZdje9tDc'),
('Hy', 'Moden', 'hmoden2k@angelfire.com', 'Male', '6aWq3J'),
('Mel', 'Lambe', 'mlambe2l@soundcloud.com', 'Male', '7VjW02pbtOv3'),
('Sophia', 'Brigshaw', 'sbrigshaw2m@aboutads.info', 'Female', 'a5XHqEnGB'),
('Alayne', 'Petruskevich', 'apetruskevich2n@addtoany.com', 'Bigender', 'EGKpHKOR'),
('Bobby', 'Surcombe', 'bsurcombe2o@sciencedirect.com', 'Bigender', 'HBVLg5'),
('Kippie', 'Corn', 'kcorn2p@uol.com.br', 'Genderfluid', 'ijONiWlEmvqo'),
('Brion', 'Ohlsen', 'bohlsen2q@angelfire.com', 'Agender', 'x3LHrwSs'),
( 'Clio', 'Huniwall', 'chuniwall2r@webeden.co.uk', 'Genderfluid', 'XmGO0W8jBQ6'),
('Hebe', 'Luz', 'hlss@a.recife.ifpe.edu.br', 'undefined' , 'hebe123' );

-- {
--     id: "@id",
--     token: "@token",
--     institution: "@institution",
--     preferences: [
--         {description: "@description"},
--         {description: "@description"},
--     ]
-- }