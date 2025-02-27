CREATE TABLE company_profile (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    registration_type VARCHAR(100),
    gstin VARCHAR(15) UNIQUE,
    pan_no VARCHAR(10) UNIQUE,
    industry VARCHAR(100),
    sector VARCHAR(100),
    msme_registration_number VARCHAR(50) UNIQUE,
    incorporation_date DATE,
    address TEXT,
    city VARCHAR(100),
    state VARCHAR(100),
    zip VARCHAR(20),
    country VARCHAR(100),
    inserted_or_updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.inserted_or_updated_date = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER set_timestamp
BEFORE UPDATE ON company_profile
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();



-- Create company_profile table
CREATE TABLE public.company_profile (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    registration_type VARCHAR(128),
    gstin VARCHAR(32),
    pan_no VARCHAR(32),
    industry VARCHAR(128),
    sector VARCHAR(128),
    msme_registration_number VARCHAR(64),
    incorporation_date DATE,
    address TEXT,
    city VARCHAR(128),
    state VARCHAR(128),
    zip VARCHAR(16),
    country VARCHAR(128),
    inserted_or_updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create credit_requirement table with correct field types
CREATE TABLE public.credit_requirement (
    id BIGSERIAL PRIMARY KEY,
    company_id BIGINT NOT NULL,
    load_format VARCHAR(128),
    requirement_description TEXT,
    end_use_of_fund TEXT,
    date_of_fund_requirement DATE,
    loan_amount int,
    loan_duration INT,
    collateral_type VARCHAR(128),
    purchase_date DATE,
    owned_by VARCHAR(128),
    purchase_value int,
    market_value int,
    description TEXT,
    FOREIGN KEY (company_id) REFERENCES public.company_profile(id) ON DELETE CASCADE
);



-- 📌 Create `company_promoter` Table
CREATE TABLE company_promoter (
    id BIGSERIAL PRIMARY KEY,
    company_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    dob DATE NOT NULL,
    aadhar_number VARCHAR(12) UNIQUE NOT NULL,
    designation VARCHAR(100),
    shareholding INTEGER,
    age INTEGER NOT NULL,
    pan VARCHAR(10) UNIQUE NOT NULL,
    address TEXT,
    years_in_address INTEGER,
    din VARCHAR(20),
    FOREIGN KEY (company_id) REFERENCES company_profile (id) ON DELETE CASCADE
);

-- 📌 Create `social_reference` Table (One promoter can have multiple social references)
CREATE TABLE social_reference (
    id BIGSERIAL PRIMARY KEY,
    promoter_id BIGINT NOT NULL,
    social_network_name VARCHAR(100) NOT NULL,
    url TEXT NOT NULL,
    FOREIGN KEY (promoter_id) REFERENCES company_promoter (id) ON DELETE CASCADE
);

-- 📌 Create `executive_association` Table (One promoter can have multiple associations)
CREATE TABLE executive_association (
    id BIGSERIAL PRIMARY KEY,
    promoter_id BIGINT NOT NULL,
    association_name VARCHAR(100) NOT NULL,
    membership_number VARCHAR(50) UNIQUE NOT NULL,
    date_of_joining DATE NOT NULL,
    FOREIGN KEY (promoter_id) REFERENCES company_promoter (id) ON DELETE CASCADE
);



INSERT INTO company_profile ( name, registration_type, gstin, pan_no, industry, sector,
    msme_registration_number, incorporation_date, address, city, state, zip, country, inserted_or_updated_date)
VALUES
( 'Tech Innovators Pvt Ltd', 'Private Limited', '29ABCDE1234F1Z5', 'ABCDE1234F',
 'IT Services', 'Software', 'MSME123456789', '2015-06-15',
 '123 Tech Park, Bangalore', 'Bangalore', 'Karnataka', '560001', 'India', NOW());


 INSERT INTO company_promoter ( company_id, name, surname, dob, aadhar_number,
    designation, shareholding, age, pan, address, years_in_address, din)
VALUES
( 4, 'Amit', 'Sharma', '1980-05-20', '123412341', 'CEO', 40, 43, 'AMITS12',
 '56 Residenc, Bangalore', 10, 'DIN123456'),
( 3, 'Rajesh', 'Verma', '1985-08-15', '432143214', 'CFO', 30, 38, 'RAJESH5',
 '78 Green, Delhi', 5, 'DIN876543');


 INSERT INTO social_reference (id, promoter_id, social_network_name, url)
VALUES
(1, 1, 'LinkedIn', 'https://www.linkedin.com/in/amitsharma'),
(2, 1, 'Twitter', 'https://twitter.com/amitsharma'),
(3, 2, 'Facebook', 'https://www.facebook.com/rajeshverma'),
(4, 2, 'LinkedIn', 'https://www.linkedin.com/in/rajeshverma');

INSERT INTO executive_association (id, promoter_id, association_name, membership_number, date_of_joining)
VALUES
(1, 1, 'CII - Confederation of Indian Industry', 'CII12345', '2018-01-10'),
(2, 1, 'NASSCOM', 'NAS67890', '2019-06-20'),
(3, 2, 'Institute of Chartered Accountants of India', 'ICAI54321', '2015-11-05');



CREATE TABLE existing_loans (
    id bigserial PRIMARY KEY,
    company_id BIGINT NOT NULL,
    lending_institute VARCHAR(255) NOT NULL,
    loan_amount BIGINT NOT NULL,
    loan_type VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    emi BIGINT NOT NULL,
    tenure_months INT NOT NULL,
    source VARCHAR(255) NOT NULL,
    FOREIGN KEY (company_id) REFERENCES company_profile(id) ON DELETE CASCADE
);






























