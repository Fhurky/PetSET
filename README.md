-------------------------------------KULLANICILAR TABLOSU----------------------
CREATE SEQUENCE user_id_seq START 1;

CREATE OR REPLACE TABLE kullanicilar (
    user_id INTEGER DEFAULT nextval('user_id_seq') PRIMARY KEY,
    fname VARCHAR(255),
    lname VARCHAR(255),
    user_name VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(20)
);
-------------------------------------KULLANICILAR TABLOSU----------------------

-------------------------------------HAYVANLAR TABLOSU----------------------
CREATE OR REPLACE TABLE hayvanlar (
    hayvan_id SERIAL PRIMARY KEY,
    sahip_id INTEGER REFERENCES kullanicilar(user_id) ON DELETE CASCADE,
    hayvan_ismi VARCHAR(255) NOT NULL,
    hayvan_türü VARCHAR(50),
    cinsiyet VARCHAR(10),
    sağlık_durumu VARCHAR(20),
    aşı_durumu VARCHAR(20),
    hayvan_yaşı INTEGER,
);
alter table hayvanlar add constraint yaş_ck check (hayvan_yaşı > 0)
-------------------------------------HAYVANLAR TABLOSU----------------------


-------------------------------------İLANLAR TABLOSU----------------------
CREATE OR REPLACE TABLE ilanlar (
    ilan_id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES kullanicilar(user_id),
    hayvan_id INTEGER REFERENCES hayvanlar(hayvan_id)
);
-------------------------------------İLANLAR TABLOSU----------------------

-------------------------------------ÜRÜNLER TABLOSU----------------------
CREATE OR REPLACE TABLE ürünler(
    ürün_id SERIAL PRIMARY KEY,
    isim VARCHAR(100),
    fiyat INTEGER DEFAULT 50
);
-------------------------------------ÜRÜNLER TABLOSU----------------------


-------------------------------------BAŞVURULAN TABLOSU----------------------
CREATE OR REPLACE TABLE basvurulan (
    basvuran_id INTEGER REFERENCES kullanicilar(user_id),
    ilan_id INTEGER REFERENCES ilanlarim(ilan_id),
    PRIMARY KEY (basvuran_id, ilan_id)
);
-------------------------------------BAŞVURULAN TABLOSU----------------------


-------------------------------------ÜRÜN SAHİPLİK TABLOSU----------------------
CREATE OR REPLACE TABLE ürün_sahiplik (
    ürün_id REFERENCES ürünler(ürün_id),
    user_id INTEGER REFERENCES kullanicilar(user_id) ON DELETE CASCADE
);
-------------------------------------ÜRÜN SAHİPLİK TABLOSU----------------------


-------------------------------------CREATE VIEW--------------------
create view hayvan as
select hayvan_id, hayvan_ismi, hayvan_türü, sahip_id
from hayvanlar
-------------------------------------CREATE VIEW----------------------


-------------------------------------LOGIN FONKSİYONU----------------------
CREATE OR REPLACE FUNCTION login (
    username VARCHAR(255),
    userpassword VARCHAR(255)
) RETURNS INTEGER AS $$
DECLARE
    user_id_result INTEGER;
BEGIN
    -- Kullanıcı adı ve şifre kontrolü
    SELECT user_id INTO user_id_result
    FROM kullanicilar
    WHERE user_name = username AND user_password = userpassword;

    -- Eğer giriş bilgileri doğruysa user_id'yi döndür, aksi halde NULL döndür
    RETURN COALESCE(user_id_result, NULL);
END;
$$ LANGUAGE plpgsql;
-------------------------------------LOGIN FONKSİYONU----------------------

----------------------------------UPDATE TRIGGER-------------------
CREATE OR REPLACE FUNCTION update_trigger()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.user_name = '' OR NEW.user_password = '' THEN
        RAISE EXCEPTION 'user_name and password cannot be NULL';
		RETURN OLD;
	ELSE 
	    RETURN NEW;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_update_trigger
BEFORE UPDATE ON kullanicilar
FOR EACH ROW EXECUTE FUNCTION update_trigger();
----------------------------------UPDATE TRIGGER-------------------

----------------------------------INSERT TRIGGER-------------------
CREATE OR REPLACE FUNCTION insertTrigger() RETURNS TRIGGER AS $$
BEGIN
    IF (new.hayvan_ismi is null or new.hayvan_türü is null) THEN
        RAISE EXCEPTION 'Hayvan türünü ve ismini girmek zorunludur';
        RETURN null;
    ELSE
        RETURN new;
    END IF;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER t_insert
BEFORE INSERT
ON hayvanlar
FOR EACH ROW EXECUTE PROCEDURE insertTrigger();
----------------------------------INSERT TRIGGER-------------------



----------------------------------LISTING FONKSIYONU-------------------
CREATE TYPE listing_record AS (
    ilan_id INT,
    user_name VARCHAR,
    hayvan_ismi VARCHAR,
    hayvan_turu VARCHAR,
    cinsiyet VARCHAR,
    hayvan_yaşi INT,
    sağlık_durumu VARCHAR,
    aşı_durumu VARCHAR,
    address VARCHAR,
    phone_number VARCHAR
);

CREATE OR REPLACE FUNCTION listing(
    p_address VARCHAR, 
    p_animal_type VARCHAR
)
RETURNS SETOF listing_record AS $$
DECLARE
    tmp_record listing_record;
    main_cursor CURSOR FOR
        SELECT i.ilan_id, k.user_name, h.hayvan_ismi, h.hayvan_türü, h.cinsiyet, h.hayvan_yaşı, h.sağlık_durumu, h.aşı_durumu, k.address, k.phone_number
        FROM ilanlar i
        JOIN kullanicilar k ON i.user_id = k.user_id
        JOIN hayvanlar h ON i.hayvan_id = h.hayvan_id
        WHERE p_animal_type IS NULL OR h.hayvan_türü = p_animal_type
        INTERSECT
        SELECT i.ilan_id, k.user_name, h.hayvan_ismi, h.hayvan_türü, h.cinsiyet, h.hayvan_yaşı, h.sağlık_durumu, h.aşı_durumu, k.address, k.phone_number
        FROM ilanlar i
        JOIN kullanicilar k ON i.user_id = k.user_id
        JOIN hayvanlar h ON i.hayvan_id = h.hayvan_id
        WHERE p_address IS NULL OR k.address = p_address;
BEGIN
    OPEN main_cursor;
    LOOP
        FETCH main_cursor INTO tmp_record;
        EXIT WHEN NOT FOUND;

        RETURN NEXT tmp_record;
    END LOOP;
    CLOSE main_cursor;
    RETURN;
END;
$$ LANGUAGE plpgsql;
----------------------------------LISTING FONKSIYONU-------------------

----------------------------------AGGREGATE FONKSIYON------------------
CREATE OR REPLACE FUNCTION itemCount(minn INTEGER)
RETURNS TABLE (
    user_id INT,
    ürün_id INT,
    ürün_name VARCHAR,
    owned_count INT
) AS $$
BEGIN
    RETURN QUERY
    SELECT k.user_id, us.ürün_id, u.isim, COUNT(us.ürün_id) AS owned_count
    FROM kullanicilar k
    JOIN ürün_sahiplik us ON k.user_id = us.user_id
    JOIN ürünler u ON us.ürün_id = u.ürün_id
    GROUP BY k.user_id, us.ürün_id, u.isim
    HAVING COUNT(us.ürün_id) > minn;
END;
$$ LANGUAGE plpgsql;
----------------------------------AGGREGATE FONKSIYON------------------












